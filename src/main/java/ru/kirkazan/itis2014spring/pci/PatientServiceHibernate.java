package ru.kirkazan.itis2014spring.pci;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Calendar;
import java.util.List;

/**
 * @author esadykov
 * @since 20.03.14 14:15
 */
public class PatientServiceHibernate implements PatientService
{

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
    public List<Patient> searchByFioAndBd(char surname, char name, char patrName, int year)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient p" +
                " where p.surname like :surname and p.name = :name and p.father = :patrName" +
                " and p.dateOfBirth > :date");
        query.setCharacter("surname", surname);
        query.setCharacter("name", name);
        query.setCharacter("patrName", patrName);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, (-1) * year);
        query.setCalendarDate("date", calendar);
        List<Patient> list = (List<Patient>) query.list();
        session.close();
        return list;
    }
}
