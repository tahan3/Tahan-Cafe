package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class ReservationCommand extends Command {

    public ReservationCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if (Objects.nonNull(key) && !key.isEmpty() && !key.isBlank() && key.matches("\\d++")) {
                final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
                EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(key)).ifPresent(hall -> {
                    session.setAttribute("hall", hall);
                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    session.setAttribute("minDate",
                            dateFormat.format(new Date()));
                    session.setAttribute("maxDate",
                            dateFormat.format(
                                    Date.from(LocalDateTime.now().plusDays(ReservationConfig.INSTANCE.getMaxDaysForwardCanBeReserved()).atZone(ZoneId.systemDefault()).toInstant())));
                    final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    session.setAttribute("minTime",
                            timeFormat.format(ReservationConfig.INSTANCE.getCafeWorkDayBegin()));
                    session.setAttribute("maxTime",
                            timeFormat.format(ReservationConfig.INSTANCE.getCafeWorkDayEnd()));
                });
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/reservation.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
