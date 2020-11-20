package by.epam.mtlcwtchr.ecafe.service.authentication.hash;

import by.epam.mtlcwtchr.ecafe.config.AuthenticationServiceConfiguration;

import java.util.Objects;

public final class PasswordHashService {

    private PasswordHashService(){}

    public static int hash(String value){
        int hashedValue = Objects.hash(value + AuthenticationServiceConfiguration.INSTANCE.getGlobalSalt());
        for(int i=0; i< AuthenticationServiceConfiguration.INSTANCE.getHashIterations(); ++i){
            hashedValue = Objects.hash(hashedValue, value + AuthenticationServiceConfiguration.INSTANCE.getGlobalSalt());
        }
        return hashedValue;
    }

    public static void main(String[] args) {
        System.out.println(hash("iregel2045A!"));
    }
}