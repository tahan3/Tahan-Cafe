package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.entity.User;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IEntityRepository<User> {

    List<User> getList() throws DAOException;
    Optional<User> find(int id) throws DAOException;
    Optional<User> find(String username) throws DAOException;
    Optional<User> save(User user) throws DAOException;
    Optional<User> update(User user) throws DAOException;
    boolean delete(int id) throws DAOException;

}
