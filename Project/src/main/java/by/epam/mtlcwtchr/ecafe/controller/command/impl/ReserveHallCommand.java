package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ReserveHallCommand extends Command {

    public ReserveHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if (Objects.nonNull(key) && !key.isEmpty() && !key.isBlank() && key.matches("\\d++")) {
                EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(key)).ifPresent(hall -> {
                    Reservation reservation = new Reservation();
                    reservation.setReservedHall(hall);
                    try {
                        if (!setReservationDate(reservation) || !setContactTime(reservation)) {
                            return;
                        }
                        if (!setContactPhone(reservation)) {
                            WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                            return;
                        }
                        EntityServiceFactory.getInstance().getReservationService().save(reservation).ifPresent(res ->
                                ((HttpServletRequest) getRequest()).getSession().setAttribute("reservation", res));
                        ((HttpServletResponse) getResponse()).sendRedirect( getRequest().getServletContext().getContextPath() + "/halls?status=success");
                    } catch (ServiceException | ParseException | IOException ex) {
                        StaticDataHandler.INSTANCE.getLOGGER().error(ex);
                    }
                });
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    private boolean setContactPhone(Reservation reservation) {
        final String contactPhone = getRequest().getParameter("contactPhone");
        if (Objects.nonNull(contactPhone) && !contactPhone.isEmpty() && !contactPhone.isBlank()) {
            reservation.setContactPhone(contactPhone);
            return true;
        } else {
            return false;
        }
    }

    private boolean setContactTime(Reservation reservation) throws ParseException, IOException {
        final String contactTime = getRequest().getParameter("contactTime");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        if(Objects.nonNull(contactTime) &&
                (timeFormat.parse(contactTime).before(ReservationConfig.INSTANCE.getCafeWorkDayEnd())) &&
                (timeFormat.parse(contactTime)).after(ReservationConfig.INSTANCE.getCafeWorkDayBegin())) {
            reservation.setContactTime(timeFormat.parse(contactTime));
            return true;
        } else {
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?key=" + reservation.getReservedHall().getId() + "&status=timeError");
            return false;
        }
    }

    private boolean setReservationDate(Reservation reservation) throws ParseException, ServiceException, IOException {
        if (Objects.nonNull(getRequest().getParameter("reservationDate"))) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            final Date reservationDate = dateFormat.parse(getRequest().getParameter("reservationDate"));
            if (EntityServiceFactory.getInstance().getReservationService().getList()
                    .stream()
                    .anyMatch(res ->  res.getReservationDate().equals(reservationDate))) {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?key=" + reservation.getReservedHall().getId() + "&status=dateError");
                return false;
            } else {
                reservation.setReservationDate(reservationDate);
                return true;
            }
        } else {
            return false;
        }
    }


}
