package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ClientOrderCommand extends Command {

    public ClientOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            getRequest().setAttribute("minTime",
                    timeFormat.format(ReservationConfig.INSTANCE.getCafeWorkDayBegin()));
            getRequest().setAttribute("maxTime",
                    timeFormat.format(ReservationConfig.INSTANCE.getCafeWorkDayEnd()));
            getRequest().getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

}
