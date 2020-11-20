package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SaveIngredientCommand extends AdminCommand {

    public SaveIngredientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            Ingredient ingredient = new Ingredient();
            final String ingredientName = getRequest().getParameter("ingredientName");
            if (Objects.nonNull(ingredientName) && !ingredientName.isEmpty() && !ingredientName.isBlank()) {
                ingredient.setName(ingredientName);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            final String ingredientPictureUrl = getRequest().getParameter("ingredientPictureUrl");
            if (Objects.nonNull(ingredientPictureUrl) && !ingredientPictureUrl.isEmpty() && !ingredientPictureUrl.isBlank()) {
                ingredient.setPictureUrl(ingredientPictureUrl);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            EntityServiceFactory.getInstance().getMealIngredientService().save(ingredient);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_ingredients");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
