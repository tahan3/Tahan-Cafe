package by.epam.mtlcwtchr.ecafe.service.factory;

import by.epam.mtlcwtchr.ecafe.service.*;

public interface IEntityServiceFactory {

    IUserService getUserService();
    IClientService getClientService();
    IMealCategoryService getMealCategoryService();
    IMealService getMealService();
    IMealIngredientService getMealIngredientService();
    IOrderService getOrderService();
    IClientCommentService getClientCommentService();
    IReservationService getReservationService();
    IHallService getHallService();

}
