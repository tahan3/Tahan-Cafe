package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class AdminReservationCommand extends AdminCommand {

    public AdminReservationCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            final String hallKey = getRequest().getParameter("hkey");
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            if (Objects.nonNull(hallKey) && !hallKey.isEmpty() && !hallKey.isBlank() && (hallKey.matches("\\d++") || hallKey.equals("all"))) {
                session.removeAttribute("hallId");
                session.setAttribute("hallId", hallKey);
                session.setAttribute("reservations", hallKey.equals ("all") ?
                            EntityServiceFactory.getInstance().getReservationService().getList() :
                            EntityServiceFactory.getInstance().getReservationService().getList(Integer.parseInt(hallKey)));
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminreservation.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
