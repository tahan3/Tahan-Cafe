package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ChangeProfileCommand extends Command {

    public ChangeProfileCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Client actor = (Client)((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            final String name = getRequest().getParameter("name");
            if (Objects.nonNull(name) && !name.isEmpty() && !name.isBlank()) {
                actor.setName(name);
            }
            final String phone = getRequest().getParameter("phone");
            if (Objects.nonNull(phone) && !phone.isEmpty() && !phone.isBlank()) {
                actor.getUser().setPhone(phone);
            }
            final String email = getRequest().getParameter("email");
            if (Objects.nonNull(email) && !email.isEmpty() && !email.isBlank()) {
                actor.getUser().setEmail(email);
            }
            final String password = getRequest().getParameter("password");
            if (Objects.nonNull(password) && !password.isEmpty() && !password.isBlank()) {
                actor.getUser().setEmail(password);
            }
            EntityServiceFactory.getInstance().getClientService().update(actor);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
