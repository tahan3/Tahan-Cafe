package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import java.util.List;
import java.util.Optional;

public interface IMealRepository extends IEntityRepository<Meal> {

    List<Meal> getList() throws DAOException;
    @ExceptionableBeingLogged("Data access object")
    List<Meal> getList(int categoryId) throws DAOException;
    Optional<Meal> find(int id) throws DAOException;
    Optional<Meal> find(String name) throws DAOException;
    Optional<Meal> save(Meal meal) throws DAOException;
    Optional<Meal> update(Meal meal) throws DAOException;
    boolean delete(int id) throws DAOException;

}
