package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface IMealIngredientRepository extends IEntityRepository<Ingredient> {

    List<Ingredient> getList() throws DAOException;
    Optional<Ingredient> find(int id) throws DAOException;
    Optional<Ingredient> find(String name) throws DAOException;
    Optional<Ingredient> save(Ingredient ingredient) throws DAOException;
    Optional<Ingredient> update(Ingredient ingredient) throws DAOException;
    boolean delete(int id) throws DAOException;

}
