package ru.kirkazan.itis2014spring.pci;

import java.util.List;

/**
 * @author esadykov
 * @since 06.03.14 19:41
 */
public interface PatientService
{
    List<Patient> searchByName(String surname, String name, String patrName);

    List<Patient> searchByFioAndBd (String surname, String name, String patrName, int year);
}
