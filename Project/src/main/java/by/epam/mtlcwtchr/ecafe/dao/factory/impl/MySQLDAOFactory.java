package by.epam.mtlcwtchr.ecafe.dao.factory.impl;

import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.repository.*;
import by.epam.mtlcwtchr.ecafe.dao.repository.impl.*;

public class MySQLDAOFactory extends DAOFactory {

    public static MySQLDAOFactory getInstance(){
        return MySQLDAOFactoryInstanceHolder.MySQL_DAO_FACTORY_INSTANCE;
    }

    private MySQLDAOFactory(){}

    @Override
    public IClientRepository getClientRepository() {
        return new ClientRepository();
    }

    @Override
    public IMealCategoryRepository getMealCategoryRepository() {
        return new MealCategoryRepository();
    }

    @Override
    public IMealIngredientRepository getMealIngredientRepository() {
        return new MealIngredientRepository();
    }

    @Override
    public IMealRepository getMealRepository() {
        return new MealRepository();
    }

    @Override
    public IOrderCompositionRepository getOrderCompositionRepository() {
        return new OrderCompositionRepository();
    }

    @Override
    public IOrderRepository getOrderRepository() {
        return new OrderRepository();
    }

    @Override
    public IUserRepository getUserRepository() {
        return new UserRepository();
    }

    @Override
    public IMealCompositionRepository getMealCompositionRepository() {
        return new MealCompositionRepository();
    }

    @Override
    public IClientCommentRepository getClientCommentRepository() {
        return new ClientCommentRepository();
    }

    @Override
    public IReservationRepository getReservationRepository() {
        return new ReservationRepository();
    }

    @Override
    public IHallRepository getHallRepository() {
        return new HallRepository();
    }

    private static class MySQLDAOFactoryInstanceHolder{
        private static final MySQLDAOFactory MySQL_DAO_FACTORY_INSTANCE = new MySQLDAOFactory();
    }

}
