package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Meal;

import java.util.List;
import java.util.Optional;

public interface IHallRepository extends IEntityRepository<Hall> {

    List<Hall> getList() throws DAOException;
    Optional<Hall> find(int id) throws DAOException;
    Optional<Hall> find(String name) throws DAOException;
    Optional<Hall> save(Hall hall) throws DAOException;
    Optional<Hall> update(Hall hall) throws DAOException;
    boolean delete(int id) throws DAOException;

}
