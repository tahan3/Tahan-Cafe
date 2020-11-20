import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.dao.repository.IHallRepository;
import by.epam.mtlcwtchr.ecafe.dao.repository.IReservationRepository;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import by.epam.mtlcwtchr.ecafe.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

public class ReservationRepositoryTest {

    private static final Reservation SAVING_RESERVATION = new Reservation(
            new Hall(1, "someName", "someDescription"),
            new Date(), new Date(), "somePhone");

    private static final IHallRepository HALL_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getHallRepository();
    private static final IReservationRepository RESERVATION_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getReservationRepository();

    @Test
    public void ReservationRepositoryTest_Save_Reservation_Matching_Criteria() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_RESERVATION.getReservedHall());
        SAVING_RESERVATION.setReservedHall(savedHall.orElseThrow());
        final Optional<Reservation> savedReservation =
                RESERVATION_REPOSITORY.save(SAVING_RESERVATION);
        Assert.assertTrue(savedReservation.isPresent());
        HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
    }

    @Test(expected = DAOException.class)
    public void ReservationRepositoryTest_Save_Reservation_Duplicate_Data() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_RESERVATION.getReservedHall());
        SAVING_RESERVATION.setReservedHall(savedHall.orElseThrow());
        RESERVATION_REPOSITORY.save(SAVING_RESERVATION);
        try {
            RESERVATION_REPOSITORY.save(SAVING_RESERVATION);
        } finally {
            HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
        }
    }

    @Test
    public void ReservationRepositoryTest_Find_Reservation_Ex_Id() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_RESERVATION.getReservedHall());
        SAVING_RESERVATION.setReservedHall(savedHall.orElseThrow());
        final Optional<Reservation> savedReservation =
                RESERVATION_REPOSITORY.save(SAVING_RESERVATION);
        final Optional<Reservation> foundReservation =
                RESERVATION_REPOSITORY.find(savedReservation.orElseThrow().getId());
        Assert.assertTrue(foundReservation.isPresent());
        HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
    }

    @Test
    public void ReservationRepositoryTest_Find_Reservation_Not_Ex_Id() throws DAOException {
        final Optional<Reservation> notFound =
                RESERVATION_REPOSITORY.find(-1);
        Assert.assertFalse(notFound.isPresent());
    }

    @Test
    public void ReservationRepositoryTest_Find_Reservation_Ex_contactPhone() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_RESERVATION.getReservedHall());
        SAVING_RESERVATION.setReservedHall(savedHall.orElseThrow());
        final Optional<Reservation> savedReservation =
                RESERVATION_REPOSITORY.save(SAVING_RESERVATION);
        final Optional<Reservation> foundReservation =
                RESERVATION_REPOSITORY.find(savedReservation.orElseThrow().getContactPhone());
        Assert.assertTrue(foundReservation.isPresent());
        HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
    }

    @Test
    public void ReservationRepositoryTest_Find_Reservation_Not_Ex_contactPhone() throws DAOException {
        final Optional<Reservation> foundReservation =
                RESERVATION_REPOSITORY.find("invalidPhone");
        Assert.assertFalse(foundReservation.isPresent());
    }

}
