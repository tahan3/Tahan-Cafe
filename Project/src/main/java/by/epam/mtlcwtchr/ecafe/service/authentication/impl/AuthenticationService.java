package by.epam.mtlcwtchr.ecafe.service.authentication.impl;

import by.epam.mtlcwtchr.ecafe.entity.User;
import by.epam.mtlcwtchr.ecafe.service.authentication.IAuthenticationService;
import by.epam.mtlcwtchr.ecafe.service.authentication.hash.PasswordHashService;
import by.epam.mtlcwtchr.ecafe.service.authentication.validation.AuthenticationValidationService;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.exception.UserAuthenticationServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.IEntityServiceFactory;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import java.util.Optional;

public class AuthenticationService implements IAuthenticationService {

    IEntityServiceFactory entityServiceFactory;

    private AuthenticationService(IEntityServiceFactory entityServiceFactory){
        this.entityServiceFactory = entityServiceFactory;
    }

    public static IAuthenticationService getInstance(){
        return UserAuthenticationServiceInstanceHandler.USER_AUTHENTICATION_SERVICE_INSTANCE;
    }

    @Override
    public User signIn(String username, String password) throws UserAuthenticationServiceException {
        try {
            if (!AuthenticationValidationService.getInstance().signInDataIsValid(username, password)) {
                throw new UserAuthenticationServiceException("Invalid data patterns");
            }
            final Optional<User> foundUser = entityServiceFactory.getUserService().find(username);
            if (foundUser.isEmpty()) {
                throw new UserAuthenticationServiceException("Invalid username provided");
            } else if (Integer.parseInt(foundUser.get().getPassword())!=(PasswordHashService.hash(password))) {
                throw new UserAuthenticationServiceException("Invalid password provided");
            } else {
                return foundUser.get();
            }
        } catch (ServiceException ex){
            throw new UserAuthenticationServiceException(ex);
        }
    }

    @Override
    public User signUp(String username, String password, String email, String phone) throws UserAuthenticationServiceException {
        try {
            if (!AuthenticationValidationService.getInstance().signUpDataIsValid(username, password, email, phone)) {
                throw new UserAuthenticationServiceException("Invalid data patterns");
            }
            final Optional<User> savedUser = entityServiceFactory.getUserService().save(new User(username, String.valueOf(PasswordHashService.hash(password)), email, phone));
            if(savedUser.isEmpty()){
                throw new UserAuthenticationServiceException("New user has not been created");
            }
            return savedUser.get();
        } catch (ServiceException ex){
            throw new UserAuthenticationServiceException(ex);
        }
    }

    private static class UserAuthenticationServiceInstanceHandler{

        private static final AuthenticationService USER_AUTHENTICATION_SERVICE_INSTANCE = new AuthenticationService(EntityServiceFactory.getInstance());

    }

}
