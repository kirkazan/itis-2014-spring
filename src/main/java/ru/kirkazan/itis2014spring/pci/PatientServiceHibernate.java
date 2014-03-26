package ru.kirkazan.itis2014spring.pci;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.List;

/**
 * @author esadykov
 * @since 20.03.14 14:15
 */
public class PatientServiceHibernate implements PatientService
{
    private Logger logger = LoggerFactory.getLogger(PatientServiceHibernate.class);

    private final SessionFactory sessionFactory;

    public PatientServiceHibernate(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Patient> searchByName(String surname, String name, String patrName)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient p where p.surname = :surname and p.name = :name and p.father = :patrName");
        query.setString("surname", surname);
        query.setString("name", name);
        query.setString("patrName", patrName);
        List<Patient> list = (List<Patient>) query.list();
        session.close();
        return list;
    }

    @Override
    public List<Patient> searchByFioAndBd(String surname, String name, String patrName, int year)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient p"
               + " where p.surname like :surname and p.name like :name and p.father like :patrName"
               + " and p.dateOfBirth != :dateFrom and p.dateOfBirth != :dateTo"
        );
        query.setString("surname",  surname + "%");
        query.setString("name",  "" + name + '%');
        query.setString("patrName",  "" + patrName + '%');

        int realYear;
        if (year > 100)
            realYear = year;
        else if (Calendar.getInstance().get(Calendar.YEAR) <= year)
            realYear = 2000 + year;
        else
            realYear = 1900 + year;

        Calendar dateFrom = Calendar.getInstance();
        dateFrom.set(realYear, Calendar.JANUARY, 1);
        Calendar dateTo = Calendar.getInstance();
        dateTo.set(realYear, Calendar.DECEMBER, 31);

        query.setDate("dateFrom", dateFrom.getTime());
        query.setDate("dateTo", dateTo.getTime());

        List<Patient> list = (List<Patient>) query.list();
        session.close();
        return list;
    }

    @Override
    public List<Patient> searchByContact(String contact)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Patient> searchByDocument(String series, String number)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
