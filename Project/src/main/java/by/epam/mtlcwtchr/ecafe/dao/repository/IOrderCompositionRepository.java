package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;

import java.util.Optional;

public interface IOrderCompositionRepository extends IRelationRepository<Order> {

    Optional<Order> get(Order order) throws DAOException;
    Optional<Order> update(Order order) throws DAOException;

}
