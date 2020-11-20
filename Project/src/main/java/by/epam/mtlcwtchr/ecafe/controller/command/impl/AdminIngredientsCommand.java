package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminIngredientsCommand extends AdminCommand {

    public AdminIngredientsCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            getRequest().setAttribute("ingredients",
                    EntityServiceFactory.getInstance().getMealIngredientService().getList());
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminingredients.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
