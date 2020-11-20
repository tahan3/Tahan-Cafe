package by.epam.mtlcwtchr.ecafe.service.impl;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.repository.IHallRepository;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.service.IHallService;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class HallService extends IHallService {

    IHallRepository hallRepository;

    public HallService(IHallRepository hallRepository){
        this.hallRepository = hallRepository;
    }

    @Override
    public List<Hall> getList() throws ServiceException {
        try{
            return hallRepository.getList();
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Hall> find(int id) throws ServiceException {
        try{
            return hallRepository.find(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Hall> find(String id) throws ServiceException {
        try{
            return hallRepository.find(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Hall> update(Hall hall) throws ServiceException {
        try{
            return hallRepository.update(hall);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Hall> save(Hall hall) throws ServiceException {
        try{
            return hallRepository.save(hall);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try{
            return hallRepository.delete(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}
