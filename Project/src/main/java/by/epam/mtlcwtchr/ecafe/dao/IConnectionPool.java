package by.epam.mtlcwtchr.ecafe.dao;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;

import java.sql.Connection;

public interface IConnectionPool {

    Connection retrieveConnection() throws DAOException;
    boolean releaseConnection(Connection connection) ;

}
