import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.dao.repository.IUserRepository;
import by.epam.mtlcwtchr.ecafe.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {

    private final User savingUser =
            new User("someUsername", "somePassword", "someEmail", "somePhone");

    private static final IUserRepository USER_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository();

    @Test
    public void UserRepositoryTest_Save_User_Matching_Criteria() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingUser);
        Assert.assertTrue(savedUser.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test(expected = DAOException.class)
    public void UserRepositoryTest_Save_User_Duplicate_Data() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingUser);
        try{
            USER_REPOSITORY.save(savingUser);
        } finally {
            USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
        }
    }


    @Test
    public void UserRepositoryTest_Find_Ex_Id() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingUser);
        final Optional<User> foundUser =
                USER_REPOSITORY.find(savedUser.orElseThrow().getId());
        Assert.assertTrue(foundUser.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test
    public void UserRepositoryTest_Find_Ex_Uname() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingUser);
        final Optional<User> foundUser =
                USER_REPOSITORY.find(savedUser.orElseThrow().getUsername());
        Assert.assertTrue(foundUser.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test
    public void UserRepositoryTest_Find_Not_Ex_Id() throws DAOException {
        final Optional<User> notFound =
                USER_REPOSITORY.find(-1);
        Assert.assertFalse(notFound.isPresent());
    }

    @Test
    public void UserRepositoryTest_Find_Not_Ex_Uname() throws DAOException {
        final Optional<User> notFound =
                USER_REPOSITORY.find("invalidUsername");
        Assert.assertFalse(notFound.isPresent());
    }

}
