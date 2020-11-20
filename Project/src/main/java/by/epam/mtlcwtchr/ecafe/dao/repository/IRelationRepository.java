package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.Optional;

public interface IRelationRepository<T> {

    /**
     * @param entity to be filled of entities related to
     * @return filled entity
     * @throws DAOException if SQLException was caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> get(T entity) throws DAOException;
    /**
     * @param entity to be updated with all its relations
     * @return updated entity
     * @throws DAOException if SQLException was caught
     */
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> update(T entity) throws DAOException;

}
