package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IReservationRepository;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ReservationRepository implements IReservationRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.reservation";
    private static final String SOURCE_TABLE_NAME_ALIAS =  " AS r";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"r.id", "r.reservation_date", "r.contact_time", "r.contact_phone",
                    "h.id", "h.guests_number", "h.hall_name", "h.hall_description"};
    private static final String JOINING_TABLE_NAME = "epam_cafe.hall AS h";
    private static final String JOIN_FOREIGN_KEY_NAME = "fk_hall_id";
    private static final String FOREIGN_TABLE_KEY_NAME = "h.id";
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{"fk_hall_id", "reservation_date", "contact_time", "contact_phone"};
    private static final String[] UPDATING_COLUMN_NAMES =
            new String[]{"id", "fk_hall_id", "reservation_date", "contact_time", "contact_phone"};
    private static final String ID_COLUMN_NAME = "r.id";
    private static final String HALL_ID_COLUMN_NAME = "h.id";
    private static final String PHONE_COLUMN_NAME = "r.contact_phone";

    @Override
    public List<Reservation> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .build(connection)){
                return getReservations(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Reservation> getList(int hallId) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, HALL_ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(hallId))){
                return getReservations(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Reservation> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getReservation(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Reservation> find(String clientPhone) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, PHONE_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(clientPhone))){
                return getReservation(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    private List<Reservation> getReservations(PreparedStatement preparedStatement) throws SQLException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return List.of();
            } else{
                ArrayList<Reservation> list = new ArrayList<>();
                do{
                    list.add(new Reservation(
                            resultSet.getInt(1),
                            new Hall(
                                    resultSet.getInt(5),
                                    resultSet.getInt(6),
                                    resultSet.getString(7),
                                    resultSet.getString(8)
                            ),
                            resultSet.getDate(2),
                            resultSet.getTime(3),
                            resultSet.getString(4)));
                } while (resultSet.next());
                return List.copyOf(list);
            }
        }
    }


    @NotNull
    private Optional<Reservation> getReservation(PreparedStatement preparedStatement) throws SQLException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new Reservation(
                        resultSet.getInt(1),
                        new Hall(
                                resultSet.getInt(5),
                                resultSet.getInt(6),
                                resultSet.getString(7),
                                resultSet.getString(8)
                        ),
                        resultSet.getDate(2),
                        resultSet.getTime(3),
                        resultSet.getString(4)));
            }
        }
    }

    @Override
    public Optional<Reservation> save(Reservation reservation) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .build(connection,
                            Optional.of(reservation.getReservedHall().getId()),
                            Optional.of(reservation.getReservationDate()),
                            Optional.of(reservation.getContactTime()),
                            Optional.of(reservation.getContactPhone()))){
                    preparedStatement.execute();
                    return getCreated();
                }
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }

    private Optional<Reservation> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .whereMaxId(SOURCE_TABLE_NAME, ID_COLUMN_NAME)
                    .build(connection)){
                return getReservation(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Reservation> update(Reservation reservation) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, UPDATING_COLUMN_NAMES)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(reservation.getId()),
                            Optional.of(reservation.getReservedHall().getId()),
                            Optional.of(reservation.getReservationDate()),
                            Optional.of(reservation.getContactTime()),
                            Optional.of(reservation.getContactPhone()),
                            Optional.of(reservation.getId()))){
                preparedStatement.execute();
                return Optional.of(reservation);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return preparedStatement.execute();
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }
}
