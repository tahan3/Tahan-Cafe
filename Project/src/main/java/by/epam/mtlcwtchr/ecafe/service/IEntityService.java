package by.epam.mtlcwtchr.ecafe.service;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {

    /**
     * @return list of retrieved entities filled of its relations
     * @throws ServiceException if DAOException was caught
     */
    @ExceptionableBeingLogged("Service")
    List<T> getList() throws ServiceException;
    /**
     * @return entity matches unique key filled of its relations
     * @throws ServiceException if DAOException was caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged
    Optional<T> findAny(Object key) throws ServiceException;
    /**
     * @return updated entity
     * @param entity to be updated in database
     * @throws ServiceException if DAOException was caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> update(T entity) throws ServiceException;
    /**
     * @return saved entity
     * @param entity to be saved
     * @throws ServiceException if DAOException was caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> save(T entity) throws ServiceException;
    /**
     * @return result of EntityRepository.delete
     * @param id is a unique number of entity to be deleted
     * @throws ServiceException if DAOException was caught
     */
    @ExceptionableBeingLogged("Service")
    boolean delete(int id) throws ServiceException;

}
