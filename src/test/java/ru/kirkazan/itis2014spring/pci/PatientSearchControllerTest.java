package ru.kirkazan.itis2014spring.pci;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kirkazan.itis2014spring.pci.service.PatientService;
import ru.kirkazan.itis2014spring.pci.web.PatientSearchController;

/**
 * Created by Татьяна on 06.03.14.
 */
@RunWith(MockitoJUnitRunner.class)
public class PatientSearchControllerTest
{
    @InjectMocks
    private PatientSearchController patientController = new PatientSearchController();

    @Mock
    PatientService patientService;

    @Test
    public void testByFioAndBd()
    {
       patientController.search("СЭР82");

        Mockito.verify(patientService).searchByFioAndBd("С","Э","Р",82);
    }

    @Test
    public void testByName() {

        patientController.search("Садыков Эдуард Рустемович");
        Mockito.verify(patientService).searchByName("Садыков","Эдуард","Рустемович");
    }

    @Test
    public void testByDocs() {

        patientController.search("9207 223344");
        Mockito.verify(patientService).searchByDocument("9207","223344");
    }

    @Test
    public void testByContacts() {

        patientController.search("89172777666");
        Mockito.verify(patientService).searchByContact("89172777666");
    }
}
