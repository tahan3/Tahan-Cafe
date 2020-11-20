package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IEntityRepository<T> {

    /**
     * @return list of entities to be retrieved
     * @throws DAOException if SQLException caught
     */
    @ExceptionableBeingLogged("Data access object")
    List<T> getList() throws DAOException;
    /**
     * @return entity matches id to be retrieved
     * @param id is unique number of entity in database
     * @throws DAOException if SQLException caught
     */
    @ExceptionableBeingLogged("Data access object")
    Optional<T> find(int id) throws DAOException;
    /**
     * @return entity matches name to be retrieved
     * @param name is unique name of entity in database
     * @throws DAOException if SQLException caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> find(String name) throws DAOException;
    /**
     * @return saved entity
     * @param entity is entity to be saved
     * @throws DAOException if SQLException caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> save(T entity) throws DAOException;
    /**
     * @return updated entity
     * @param entity is entity to be updated
     * @throws DAOException if SQLException caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> update(T entity) throws DAOException;
    /**
     * @return result of preparedStatement.execute
     * @param id is unique number of entity in database
     * @throws DAOException if SQLException caught
     */
    @ExceptionableBeingLogged("Data access object")
    boolean delete(int id) throws DAOException;

}
