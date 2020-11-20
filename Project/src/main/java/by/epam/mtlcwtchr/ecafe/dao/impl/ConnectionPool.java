package by.epam.mtlcwtchr.ecafe.dao.impl;

import by.epam.mtlcwtchr.ecafe.config.DAOConfiguration;
import by.epam.mtlcwtchr.ecafe.dao.IConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOConnectionPoolRisingException;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author St.Anislav
 * @version 1.1
 * Connection pool for database opened connections
 * <b>availableConnections</b> - all available for clients connections
 * <b>involvedConnections</b> - involved by clients ones
 */
public enum ConnectionPool implements IConnectionPool {

    CONNECTION_POOL_INSTANCE;

    private final List<Connection> availableConnections = new CopyOnWriteArrayList<>();
    private final Set<Connection> involvedConnections = new CopyOnWriteArraySet<>();

    ConnectionPool(){
        try {
            raiseConnections(DAOConfiguration.INSTANCE.getInitPoolCapacity());
        } catch (DAOException ignored){
        }
    }

    /**
     *
     * @return any available connection
     * @throws DAOException if raiseConnections throws one
     */
    public Connection retrieveConnection() throws DAOException{
        Connection connection = null;
        if(!availableConnections.isEmpty()){
            connection = availableConnections.get(0);
            if(Objects.nonNull(connection)){
                availableConnections.remove(0);
                involvedConnections.add(connection);
            }
        } else if(availableConnections.size()+involvedConnections.size() < DAOConfiguration.INSTANCE.getMaxPoolSize()){
                raiseConnections(DAOConfiguration.INSTANCE.getPoolIncreaseStep());
        }
        return connection;
    }

    /**
     *
     * @param connection to be released (close)
     * @return is operation succeed
     */
    public boolean releaseConnection(Connection connection) {
        if(Objects.nonNull(connection)&&involvedConnections.contains(connection)) {
            try {
                involvedConnections.remove(connection);
                availableConnections.add(connection);
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param connection to check
     * @return is connection involved
     */
    public boolean isInvolved(Connection connection){
        return involvedConnections.contains(connection);
    }

    /**
     *
     * @param initialCapacity is amount of initially rising connections
     * @throws DAOConnectionPoolRisingException if it's an exception while establishing connection
     */
    @ExceptionableBeingLogged("Data access object")
    private void raiseConnections(int initialCapacity) throws DAOConnectionPoolRisingException {
        for(int i=0; i<initialCapacity; ++i){
            try{
                availableConnections.add(new Connection$Proxy(DriverManager.getConnection(
                        DAOConfiguration.INSTANCE.getDbUrl(),
                        DAOConfiguration.INSTANCE.getDbUser(),
                        DAOConfiguration.INSTANCE.getDbPassword())));
            } catch (SQLException ex){
                throw new DAOConnectionPoolRisingException(ex);
            }
        }
    }

    /**
     * method to close all established connections
     */
    public void shutdown() {
        involvedConnections.forEach(e-> {
            try {
                e.close();
            } catch (SQLException ignored) {

            }
        });
       availableConnections.forEach(e-> {
           try {
               ((Connection$Proxy) e).shutdown();
           } catch (SQLException ignored) {

           }
       });

    }

}
