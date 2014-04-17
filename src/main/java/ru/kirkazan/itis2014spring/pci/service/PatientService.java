package ru.kirkazan.itis2014spring.pci.service;

import ru.kirkazan.itis2014spring.pci.dao.Patient;

import java.util.List;

/**
 * @author esadykov
 * @since 06.03.14 19:41
 */
public interface PatientService
{
    List<PatientInfo> searchByName(String surname, String name, String patrName);

    List<PatientInfo> searchByFioAndBd (String surname, String name, String patrName, int year);

    List<PatientInfo> searchByContact (String contact);

    List<PatientInfo> searchByDocument (String series, String number);

}
