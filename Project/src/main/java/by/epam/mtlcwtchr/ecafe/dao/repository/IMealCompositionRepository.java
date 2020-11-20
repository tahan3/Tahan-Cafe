package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;

import java.util.Optional;

public interface IMealCompositionRepository extends IRelationRepository<Meal> {

    Optional<Meal> get(Meal meal) throws DAOException;
    Optional<Meal> update(Meal meal) throws DAOException;

}
