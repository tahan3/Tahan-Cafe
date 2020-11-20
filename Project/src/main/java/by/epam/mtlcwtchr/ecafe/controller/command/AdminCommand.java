package by.epam.mtlcwtchr.ecafe.controller.command;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

    public abstract class AdminCommand extends Command {

    public AdminCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        executeIfValid();
    }

    @Override
    public void executePost() throws ControllerException {
        executeIfValid();
    }


    private void executeIfValid() throws ControllerException {
        if (Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) &&
                ((Actor) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()) {
            executeValidated();
        } else {
            try {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            } catch (IOException ex) {
                throw  new ControllerException(ex);
            }
        }
    }

    public abstract void executeValidated() throws ControllerException;

}
