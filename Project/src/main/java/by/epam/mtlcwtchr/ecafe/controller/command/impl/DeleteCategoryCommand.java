package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.MenuConfig;
import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DeleteCategoryCommand extends AdminCommand {

    public DeleteCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final String deleteKey = getRequest().getParameter("dkey");
            if (Objects.nonNull(deleteKey) && !deleteKey.isBlank() && !deleteKey.isEmpty() && deleteKey.matches("\\d++")) {
                setDeletingCategoryMealsCategoryToUnset(deleteKey);
                EntityServiceFactory.getInstance().getMealCategoryService().delete(Integer.parseInt(deleteKey));
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_categories");
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    private void setDeletingCategoryMealsCategoryToUnset(String deleteKey) throws ServiceException {
        EntityServiceFactory.getInstance().getMealCategoryService().find(Integer.parseInt(deleteKey)).ifPresent(category -> {
            try {
                EntityServiceFactory.getInstance().getMealCategoryService().find(MenuConfig.INSTANCE.getUnsetCategoryId()).ifPresent(unsetCategory -> {
                    try {
                        EntityServiceFactory.getInstance().getMealService().getList(category.getId()).forEach(meal -> {
                            meal.setCategory(unsetCategory);
                            try {
                                EntityServiceFactory.getInstance().getMealService().update(meal);
                            } catch (ServiceException ex) {
                                StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Meal %s category hasn't been set to %s cause of %s", meal, unsetCategory, ex));
                            }
                        });
                    } catch (ServiceException ex){
                        StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Meals category hasn't been changed to %s cause of %s", unsetCategory, ex));
                    }
                });
            } catch (ServiceException ex) {
                StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Meals category hasn't been changed to defaults cause of %s", ex));
            }
        });
    }

}
