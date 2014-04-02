package ru.kirkazan.itis2014spring.pci;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
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

        assertEquals(1, service.searchByName(surname, name, father).size());

        assertEquals(0, service.searchByName("Нет", "Такого", "Человека").size());

    }

    @Test
    public void searchByFioAndBd()
    {
        PatientService service = new PatientServiceHibernate(sessionFactory);

        assertEquals("should be found", 1, service.searchByFioAndBd(surname.substring(0,1), name.substring(0,1), father.substring(0,1), 1982).size());
        assertEquals("should be found", 1, service.searchByFioAndBd(surname.substring(0,1), name.substring(0,1), father.substring(0,1), 82).size());
        assertEquals("should be empty", 0, service.searchByFioAndBd("Н", "Е", "Т", 66).size());
    }


    @Test
    public void searchByContact() {

        PatientService service = new PatientServiceHibernate(sessionFactory);

        assertEquals(1, service.searchByContact(contact).size());
        assertEquals(0, service.searchByContact("88888888888").size());
    }

    @Test
    public void searchByDocument() {

        PatientService service = new PatientServiceHibernate(sessionFactory);
        assertEquals(1, service.searchByDocument(series, number).size());
        assertEquals(1, service.searchByDocument(null, number).size());
        assertEquals(1, service.searchByDocument("", number));
        assertEquals(0, service.searchByDocument("33", "44").size());

    }

    @Test
    public void all()
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient");
        List<Patient> list = (List<Patient>) query.list();
        session.close();
        assertEquals(1, list.size());
        logger.info(list.toString());
    }


    private static SessionFactory sessionFactory;

    private static Logger logger = LoggerFactory.getLogger(PatientServiceHibernateTest.class);

    static String surname = "Садыков";
    static String name = "Эдуард";
    static String father = "Рустемович";
    static String contact = "89172777666";
    static String series = "9207";
    static String number = "223344";


    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Patient patient = new Patient();
        patient.setFather(father);
        patient.setName(name);
        patient.setSurname(surname);
        patient.setContact(contact);
        Calendar bd = Calendar.getInstance();
        bd.set(1982, Calendar.APRIL, 7);
        patient.setDateOfBirth(bd.getTime());
        session.persist(patient);
        session.flush();
        session.getTransaction().commit();
        if (session != null) {
            session.close();
            logger.info("session closed");
        }
    }

    @AfterClass
    public static void destroy() {
        logger.info("Destroying...");

        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("factory closed");
        }

        logger.info("destroyed!");
    }
}
