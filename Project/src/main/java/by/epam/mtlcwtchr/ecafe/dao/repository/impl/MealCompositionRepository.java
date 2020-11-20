package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IMealCompositionRepository;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MealCompositionRepository implements IMealCompositionRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.meal_composition";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"i.id", "i.name", "i.pic_url", "ingredient_mass"};
    private static final String JOINING_TABLE_NAME = "epam_cafe.meal_ingredient  AS i";
    private static final String JOIN_FOREIGN_KEY_NAME = "fk_meal_ingredient_id";
    private static final String FOREIGN_TABLE_KEY_NAME = "i.id";
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{"fk_meal_id", "fk_meal_ingredient_id", "ingredient_mass"};
    private static final String ID_COLUMN_NAME = "fk_meal_id";

    @Override
    public Optional<Meal> get(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(meal.getId()))){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.first()) {
                        do {
                            meal.addIngredient(new Ingredient(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getInt(4)));
                        } while (resultSet.next());
                    }
                    return Optional.of(meal);
                }
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> update(Meal meal) throws DAOException {
        if(clear(meal))
            throw new DAOException("Meal " + meal + " can not be updated");
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .beginBatch(connection)
                    .addBatch(generateBatch(meal))
                    .endBatch()){
                    preparedStatement.executeBatch();
                    return Optional.of(meal);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private boolean clear(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete(SOURCE_TABLE_NAME)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(meal.getId()))){
                return preparedStatement.execute();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private ArrayList<Optional<?>[]> generateBatch(Meal meal){
        ArrayList<Optional<?>[]> optionalArrayList = new ArrayList<>();
        for (Ingredient ingredient : meal.getIngredients()) {
            optionalArrayList.add(new Optional[]{Optional.of(meal.getId()),Optional.of(ingredient.getId()), Optional.of(ingredient.getMass())});
        }
        return optionalArrayList;
    }

}
