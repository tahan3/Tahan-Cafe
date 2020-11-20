import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactory;
import by.epam.mtlcwtchr.ecafe.dao.factory.DAOFactoryType;
import by.epam.mtlcwtchr.ecafe.dao.repository.IClientRepository;
import by.epam.mtlcwtchr.ecafe.dao.repository.IUserRepository;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryTest {

    private final Client savingClient = new Client(
            new User("someUsername", "somePassword", "someEmail", "somePhone"),
            "someName");

    private static final IUserRepository USER_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository();
    private static final IClientRepository CLIENT_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository();

    @Test
    public void ClientRepositoryTest_Save_Client_Matching_Criteria() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingClient.getUser());
        savingClient.setUser(savedUser.orElseThrow());
        final Optional<Client> savedClient =
                CLIENT_REPOSITORY.save(savingClient);
        Assert.assertTrue(savedClient.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test(expected = DAOException.class)
    public void ClientRepositoryTest_Save_Client_Duplicate_Data() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingClient.getUser());
        savingClient.setUser(savedUser.orElseThrow());
        CLIENT_REPOSITORY.save(savingClient);
        try {
            CLIENT_REPOSITORY.save(savingClient);
        } finally {
            USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
        }
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Ex_Id() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingClient.getUser());
        savingClient.setUser(savedUser.orElseThrow());
        final Optional<Client> savedClient =
                CLIENT_REPOSITORY.save(savingClient);
        final Optional<Client> foundClient =
                CLIENT_REPOSITORY.find(savedClient.orElseThrow().getId());
        Assert.assertTrue(foundClient.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Not_Ex_Id() throws DAOException {
        final Optional<Client> foundClient =
                CLIENT_REPOSITORY.find(-1);
        Assert.assertFalse(foundClient.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Ex_Name() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingClient.getUser());
        savingClient.setUser(savedUser.orElseThrow());
        final Optional<Client> savedClient =
                CLIENT_REPOSITORY.save(savingClient);
        final Optional<Client> foundClient =
                CLIENT_REPOSITORY.find(savedClient.orElseThrow().getName());
        Assert.assertTrue(foundClient.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Not_Ex_Name() throws DAOException {
        final Optional<Client> notFound =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find("invalidName");
        Assert.assertFalse(notFound.isPresent());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Ex_Uname() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(savingClient.getUser());
        savingClient.setUser(savedUser.orElseThrow());
        final Optional<Client> savedClient =
                CLIENT_REPOSITORY.save(savingClient);
        final Optional<Client> foundClient =
                CLIENT_REPOSITORY.find(savedClient.orElseThrow().getUser());
        Assert.assertTrue(foundClient.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test
    public void ClientRepositoryTest_Find_Client_Not_Ex_Uname() throws DAOException {
        final Optional<Client> notFound =
                DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository().find(new User("invalidUname", "invalidPassword"));
        Assert.assertFalse(notFound.isPresent());
    }

}
