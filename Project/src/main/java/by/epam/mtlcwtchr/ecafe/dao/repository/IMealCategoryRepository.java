package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface IMealCategoryRepository extends IEntityRepository<Category> {

    List<Category> getList() throws DAOException;
    Optional<Category> find(int id) throws DAOException;
    Optional<Category> find(String name) throws DAOException;
    Optional<Category> save(Category category) throws DAOException;
    Optional<Category> update(Category category) throws DAOException;
    boolean delete(int id) throws DAOException;

}
