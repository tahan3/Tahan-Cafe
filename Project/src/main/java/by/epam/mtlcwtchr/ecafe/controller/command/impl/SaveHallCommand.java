package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SaveHallCommand extends AdminCommand {

    public SaveHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            Hall hall = new Hall();
            final String hallId = getRequest().getParameter("hallId");
            if (Objects.nonNull(hallId) && !hallId.isEmpty() && !hallId.isBlank() && hallId.matches("\\d++")) {
                hall.setId(Integer.parseInt(hallId));
            }
            final String hallGuestsNumber = getRequest().getParameter("hallGuestsNumber");
            if (Objects.nonNull(hallGuestsNumber) && !hallGuestsNumber.isEmpty() && !hallGuestsNumber.isBlank()) {
                hall.setGuestsNumber(Integer.parseInt(hallGuestsNumber));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            final String hallName = getRequest().getParameter("hallName");
            if (Objects.nonNull(hallName) && !hallName.isEmpty() && !hallName.isBlank()) {
                hall.setName(hallName);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            final String hallDescription = getRequest().getParameter("hallDescription");
            if (Objects.nonNull(hallDescription) && !hallDescription.isEmpty() && !hallDescription.isBlank()) {
                hall.setDescription(hallDescription);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            EntityServiceFactory.getInstance().getHallService().save(hall);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_halls");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
