package ru.kirkazan.itis2014spring.pci;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ser
 * @since 19.02.14 22:27
 */
public class HibernateTest
{

    private Logger logger = LoggerFactory.getLogger(HibernateTest.class);

    SessionFactory sessionFactory;
    Session session;

    @Before
    public void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
    }

    @After
    public void destroy() {
        logger.info("Destroying...");
        if (session != null) {
            session.close();
            logger.info("session closed");
        }
        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("factory closed");
        }
        logger.info("destroyed!");
    }

    @Test
    public void empty() {
        //only init interested
    }

}
