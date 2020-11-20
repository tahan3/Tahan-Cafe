import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.dao.repository.IHallRepository;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class HallRepositoryTest {

    private final Hall SAVING_HALL =
            new Hall(1, "someName", "someDescription");

    private static final IHallRepository HALL_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getHallRepository();

    @Test
    public void HallRepositoryTest_Save_Hall_Matching_Criteria() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_HALL);
        Assert.assertTrue(savedHall.isPresent());
        HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
    }

    @Test(expected = DAOException.class)
    public void HallRepositoryTest_Save_Hall_Duplicate_Data() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_HALL);
        try{
            HALL_REPOSITORY.save(SAVING_HALL);
        } finally {
            HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
        }
    }


    @Test
    public void HallRepositoryTest_Find_Ex_Id() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_HALL);
        final Optional<Hall> foundHall =
                HALL_REPOSITORY.find(savedHall.orElseThrow().getId());
        Assert.assertTrue(foundHall.isPresent());
        HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
    }

    @Test
    public void HallRepositoryTest_Find_Ex_Name() throws DAOException {
        final Optional<Hall> savedHall =
                HALL_REPOSITORY.save(SAVING_HALL);
        final Optional<Hall> foundHall =
                HALL_REPOSITORY.find(savedHall.orElseThrow().getName());
        Assert.assertTrue(foundHall.isPresent());
        HALL_REPOSITORY.delete(savedHall.orElseThrow().getId());
    }

    @Test
    public void HallRepositoryTest_Find_Not_Ex_Id() throws DAOException {
        final Optional<Hall> notFound =
                HALL_REPOSITORY.find(-1);
        Assert.assertFalse(notFound.isPresent());
    }

    @Test
    public void HallRepositoryTest_Find_Not_Ex_Name() throws DAOException {
        final Optional<Hall> notFound =
                HALL_REPOSITORY.find("invalidName");
        Assert.assertFalse(notFound.isPresent());
    }

}
