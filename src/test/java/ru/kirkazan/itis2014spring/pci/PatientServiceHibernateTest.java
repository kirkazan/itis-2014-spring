package ru.kirkazan.itis2014spring.pci;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author esadykov
 * @since 20.03.14 14:33
 */
public class PatientServiceHibernateTest
{
    @Test
    public void searchByName()
    {
        PatientService service = new PatientServiceHibernate(sessionFactory);

        assertEquals(1, service.searchByName("Садыков", "Эдуард", "Рустемович").size());

        assertEquals(0, service.searchByName("Нет", "Такого", "Человека").size());

    }

    @Test
    public void all()
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient");
        List<Patient> list = (List<Patient>) query.list();
        session.close();
        assertEquals(1, list.size());
    }


    SessionFactory sessionFactory;
    Session session;

    private Logger logger = LoggerFactory.getLogger(PatientServiceHibernateTest.class);

    @Before
    public void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        /*Patient[] listPat = new Patient[12];
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
            session.persist(listPat[i]);

        }*/
        Patient patient = new Patient();
        patient.setFather("Рустемович");
        patient.setName("Эдуард");
        patient.setSurname("Садыков");
        session.persist(patient);
        session.flush();
        session.getTransaction().commit();
        if (session != null) {
            session.close();
            logger.info("session closed");
        }
    }

    @After
    public void destroy() {
        logger.info("Destroying...");

        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("factory closed");
        }

        logger.info("destroyed!");
    }
}
