package by.epam.mtlcwtchr.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public enum AuthenticationServiceConfiguration {

    INSTANCE;

    private final String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$";
    private final String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private final String PHONE_PATTERN = "[0-9]{11}";
    private String globalSalt;
    private int hashIterations;

    AuthenticationServiceConfiguration(){
        loadProperties();
    }
    private void loadProperties() {
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("authenticationProperties.properties")){
            Properties properties = new Properties();
            properties.load(in);
            globalSalt = Objects.nonNull(properties.getProperty("globalSalt")) ?
                    properties.getProperty("globalSalt") : "defaultSalt";
            hashIterations = Objects.nonNull(properties.getProperty("hashIterations")) ?
                    Integer.parseInt(properties.getProperty("hashIterations")) : 1;
        } catch (IOException ignored){ }
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + '{' +
                "usernamePattern='" + USERNAME_PATTERN + '\'' +
                ", passwordPattern='" + PASSWORD_PATTERN + '\'' +
                ", emailPattern='" + EMAIL_PATTERN + '\'' +
                ", phonePattern='" + PHONE_PATTERN + '\'' +
                ", globalSalt='" + globalSalt + '\'' +
                ", hashIterations=" + hashIterations +
                "}";
    }

    public String getUSERNAME_PATTERN() {
        return USERNAME_PATTERN;
    }
    public String getPASSWORD_PATTERN() {
        return PASSWORD_PATTERN;
    }
    public String getEMAIL_PATTERN() {
        return EMAIL_PATTERN;
    }
    public String getPHONE_PATTERN() {
        return PHONE_PATTERN;
    }
    public String getGlobalSalt() {
        return globalSalt;
    }
    public int getHashIterations() {
        return hashIterations;
    }

}
