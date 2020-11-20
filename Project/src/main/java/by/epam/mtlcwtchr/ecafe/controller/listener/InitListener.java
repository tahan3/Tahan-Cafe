package by.epam.mtlcwtchr.ecafe.controller.listener;

import by.epam.mtlcwtchr.ecafe.config.DependenciesLoader;
import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Application initialization listener")
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StaticDataHandler.INSTANCE.getLOGGER().info(String.format("Loaded dependencies %s", DependenciesLoader.getInstance()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        StaticDataHandler.INSTANCE.getLOGGER().info(String.format("%s context destroyed", this));
        ConnectionPool.CONNECTION_POOL_INSTANCE.shutdown();
    }
}
