package ru.kirkazan.itis2014spring.pci;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kirkazan.itis2014spring.pci.dao.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        manager.getTransaction().begin();
        Patient[] listPat = new Patient[12];
        List<String> sur = new ArrayList<String>();
        sur.add("Иванов");
        sur.add("Петров");
        sur.add("Сидоров");
        sur.add("Стрелки");
        sur.add("Кошкин");
        sur.add("Кошечкин");
        sur.add("Котяткин");
        sur.add("Иваненко");
        sur.add("Петров");
        List<String> nam = new ArrayList<String>();
        nam.add("Михаил");
        nam.add("Руслан");
        nam.add("Максим");
        nam.add("Антон");
        nam.add("Илья");
        nam.add("Павел");
        nam.add("Андрей");
        nam.add("Алексей");
        nam.add("Александр");
        List<String> fath = new ArrayList<String>();
        fath.add("Иванович");
        fath.add("Петрович");
        fath.add("Сидорович");
        fath.add("Михаилович");
        fath.add("Раилевич");
        fath.add("Рустемович");
        fath.add("Ильич");
        fath.add("Максимович");
        fath.add("Антонович");
        for (int i = 0; i < listPat.length; i++) {
            listPat[i] = new Patient();
            for (int j = 0; j < sur.size(); j++) {
                int k = (int) Math.round(8 * Math.random());
                listPat[i].setSurname(sur.get(k));
                int k1 = (int) Math.round(8 * Math.random());
                listPat[i].setName(nam.get(k1));
                int k2 = (int) Math.round(8 * Math.random());
                listPat[i].setFather(fath.get(k2));
            }
            listPat[i].setDateOfBirth(new Date());
            manager.persist(listPat[i]);

        }
        manager.flush();

    }

    @After
    public void destroy() {
        logger.info("Destroying...");
        if (manager != null) {
            manager.getTransaction().commit();
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
