package ru.kirkazan.itis2014spring.pci.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kirkazan.itis2014spring.pci.dao.Patient;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author esadykov
 * @since 20.03.14 14:15
 */
@Service
public class PatientServiceHibernate implements PatientService {
    private Logger logger = LoggerFactory.getLogger(PatientServiceHibernate.class);

    @Autowired
    private SessionFactory sessionFactory;

    public PatientServiceHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PatientServiceHibernate() {

    }

    @PostConstruct
    public void init() {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
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
            Patient patient = new Patient();
            int k = (int) Math.round(8 * Math.random());
            patient.setSurname(sur.get(k));
            int k1 = (int) Math.round(8 * Math.random());
            patient.setName(nam.get(k1));
            int k2 = (int) Math.round(8 * Math.random());
            patient.setFather(fath.get(k2));

            patient.setDateOfBirth(new Date());
            logger.info("patient: {}", patient);
            session.persist(patient);

        }
        session.flush();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<PatientInfo> searchByName(String surname, String name, String patrName) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient p where p.surname = :surname and p.name = :name and p.father = :patrName");
        query.setString("surname", surname);
        query.setString("name", name);
        query.setString("patrName", patrName);
        List<Patient> list = (List<Patient>) query.list();
        session.close();


        return convert(list);
    }

    private List<PatientInfo> convert (List<Patient> source) {
        List<PatientInfo> target = new LinkedList<>();
        for (Patient src : source)
        {
            PatientInfo trg = new PatientInfo();
            trg.setSurname(src.getSurname());
            trg.setName(src.getName());
            trg.setFather(src.getFather());
            target.add(trg);
        }
        return target;
    }

    @Override
    public List<PatientInfo> searchByFioAndBd(String surname, String name, String patrName, int year) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient p"
                + " where p.surname like :surname and p.name like :name and p.father like :patrName"
              //  + " and p.dateOfBirth >= :dateFrom and p.dateOfBirth <= :dateTo"
        );
        query.setString("surname", surname + "%");
        query.setString("name", "" + name + '%');
        query.setString("patrName", "" + patrName + '%');

        int realYear;
        if (year > 100)
            realYear = year;
        else if (Calendar.getInstance().get(Calendar.YEAR) % 100 >= year)
            realYear = 2000 + year;
        else
            realYear = 1900 + year;

        Calendar dateFrom = Calendar.getInstance();
        dateFrom.set(realYear, Calendar.JANUARY, 1, 0, 0, 0);
        Calendar dateTo = Calendar.getInstance();
        dateTo.set(realYear, Calendar.DECEMBER, 31, 0, 0, 0);

        //query.setDate("dateFrom", dateFrom.getTime());
        //query.setDate("dateTo", dateTo.getTime());

        List<Patient> list = (List<Patient>) query.list();
        session.close();
        return convert(list);
    }

    @Override
    public List<PatientInfo> searchByContact(String contact) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient p where p.contact = :contact");
        query.setString("contact", contact);
        List<Patient> list = (List<Patient>) query.list();
        session.close();
        return convert(list);
    }

    @Override
    public List<PatientInfo> searchByDocument(String series, String number) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select p from Patient p, Document d where p=d.patient and d.series = :series and d.number = :number");
        query.setString("series", series);
        query.setString("number", number);
        List<Patient> list = (List<Patient>) query.list();
        return convert(list);
    }
}
