package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
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

public class UpdateHallCommand extends AdminCommand {

    public UpdateHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final String updateKey = getRequest().getParameter("ukey");
            if(Objects.nonNull(updateKey) && !updateKey.isEmpty() && !updateKey.isBlank() && updateKey.matches("\\d++")) {
                EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(updateKey)).ifPresent(this::update);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    private void update(Hall hall){
        try{
            if(setId(hall) && setGuestsNumber(hall) && setName(hall) && setDescription(hall)){
                EntityServiceFactory.getInstance().getHallService().update(hall);
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_halls");
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex){
            StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Hall %s hasn't been updated cause of %s", hall, ex));
        }
    }

    private boolean setDescription(Hall hall) {
        final String hallDescription = getRequest().getParameter("hallDescription");
        if (Objects.nonNull(hallDescription) && !hallDescription.isEmpty() && !hallDescription.isBlank()) {
            hall.setDescription(hallDescription);
            return true;
        } else {
            return false;
        }
    }

    private boolean setName(Hall hall) {
        final String hallName = getRequest().getParameter("hallName");
        if (Objects.nonNull(hallName) && !hallName.isEmpty() && !hallName.isBlank()) {
            hall.setName(hallName);
            return true;
        } else {
            return false;
        }
    }

    private boolean setGuestsNumber(Hall hall) {
        final String hallGuestsNumber = getRequest().getParameter("hallGuestsNumber");
        if (Objects.nonNull(hallGuestsNumber) && !hallGuestsNumber.isEmpty() && !hallGuestsNumber.isBlank()) {
            hall.setGuestsNumber(Integer.parseInt(hallGuestsNumber));
            return true;
        } else {
            return false;
        }
    }

    private boolean setId(Hall hall) {
        final String hallId = getRequest().getParameter("hallId");
        if (Objects.nonNull(hallId) && !hallId.isEmpty() && !hallId.isBlank() && hallId.matches("\\d++")) {
            hall.setId(Integer.parseInt(hallId));
            return true;
        } else {
            return false;
        }
    }

}
