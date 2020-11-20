package by.epam.mtlcwtchr.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public enum MenuConfig {

    INSTANCE;

    private int defaultMenuCategoryId;
    private int unsetCategoryId;

    MenuConfig(){
        loadProperties();
    }

    private void loadProperties() {
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("menuProperties.properties")){
            Properties properties = new Properties();
            properties.load(in);
            defaultMenuCategoryId = Objects.nonNull(properties.getProperty("defaultMenuCategoryId")) ?
                    Integer.parseInt(properties.getProperty("defaultMenuCategoryId")) : 1;
            unsetCategoryId = Objects.nonNull(properties.getProperty("unsetCategoryId")) ?
                    Integer.parseInt(properties.getProperty("unsetCategoryId")) : 1;
        } catch (IOException ex){
           StaticDataHandler.INSTANCE.getLOGGER().error(ex);
        }
    }

    public int getDefaultMenuCategoryId() {
        return defaultMenuCategoryId;
    }

    public int getUnsetCategoryId() {
        return unsetCategoryId;
    }
    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "defaultMenuCategoryId=" + defaultMenuCategoryId +
                '}';
    }


}
