package by.epam.mtlcwtchr.ecafe.service.authentication;

import by.epam.mtlcwtchr.ecafe.entity.User;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.service.exception.UserAuthenticationServiceException;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

public interface IAuthenticationService {

    @CheckedArguments
    @ExceptionableBeingLogged
    User signIn(String username, String password) throws UserAuthenticationServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged
    User signUp(String username, String password, String email, String phone) throws UserAuthenticationServiceException;


}
