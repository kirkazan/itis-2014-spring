package ru.kirkazan.itis2014spring.pci;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author ser
 * @since 20.02.14 0:33
 */
public class PersistanceTest
{
    private Logger logger = LoggerFactory.getLogger(PersistanceTest.class);

    EntityManagerFactory factory;
    EntityManager manager;

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("ru.kirkazan.itis2014spring.pci");
        manager = factory.createEntityManager();
    }

    @After
    public void destroy() {
        logger.info("Destroying...");
        if (manager != null) {
            manager.close();
            logger.info("manager closed");
        }
        if (factory != null) {
            factory.close();
            logger.info("factory closed");
        }
        logger.info("destroyed!");
    }

    @Test
    public void empty() {

       //only init interested

    }
}
