package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends IEntityRepository<Order> {

    List<Order> getList() throws DAOException;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    List<Order> getList(int clientId) throws DAOException;
    Optional<Order> find(int id) throws DAOException;
    Optional<Order> find(String clientName) throws DAOException;
    Optional<Order> save(Order entity) throws DAOException;
    Optional<Order> update(Order entity) throws DAOException;
    boolean delete(int id) throws DAOException;

}
