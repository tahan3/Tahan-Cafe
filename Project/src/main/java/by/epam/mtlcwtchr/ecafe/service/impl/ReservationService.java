package by.epam.mtlcwtchr.ecafe.service.impl;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.repository.IReservationRepository;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import by.epam.mtlcwtchr.ecafe.service.IReservationService;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ReservationService extends IReservationService {

    IReservationRepository reservationRepository;

    public ReservationService(IReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getList() throws ServiceException {
        try {
            return reservationRepository.getList();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Reservation> getList(int hallId) throws ServiceException {
        try {
            return reservationRepository.getList(hallId);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Reservation> find(int id) throws ServiceException {
        try {
            return reservationRepository.find(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Reservation> find(String clientPhone) throws ServiceException {
        try {
            return reservationRepository.find(clientPhone);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Reservation> save(Reservation reservation) throws ServiceException {
        try {
            return reservationRepository.save(reservation);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Reservation> update(Reservation reservation) throws ServiceException {
        try {
            return reservationRepository.update(reservation);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return reservationRepository.delete(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
