package by.epam.mtlcwtchr.ecafe.dao.factory;

import by.epam.mtlcwtchr.ecafe.dao.factory.impl.MySQLDAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.repository.*;

public abstract class DAOFactory {

    public abstract IClientRepository getClientRepository();
    public abstract IMealCategoryRepository getMealCategoryRepository();
    public abstract IMealIngredientRepository getMealIngredientRepository();
    public abstract IMealRepository getMealRepository();
    public abstract IOrderCompositionRepository getOrderCompositionRepository();
    public abstract IOrderRepository getOrderRepository();
    public abstract IUserRepository getUserRepository();
    public abstract IMealCompositionRepository getMealCompositionRepository();
    public abstract IClientCommentRepository getClientCommentRepository();
    public abstract IReservationRepository getReservationRepository();
    public abstract IHallRepository getHallRepository();

    public static DAOFactory getDAOFactory(DAOFactoryType daoFactoryType){
        return switch (daoFactoryType) {
            case MySQLDAOFactory -> MySQLDAOFactory.getInstance();
            default -> throw new IllegalStateException("Unexpected value: " + daoFactoryType);
        };
    }

}
