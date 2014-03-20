package ru.kirkazan.itis2014spring.pci;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
    public void test()
    {
        patientController.search("СЭР82");

        Mockito.verify(patientService).searchByFioAndBd("C","Э","Р",82);
    }

    @Test
    public void test2() {

        patientController.search("Садыков Эдуард Рустемович");

        Mockito.verify(patientService).searchByName("Садыков", "Эдуард", "Рустемович");

    }
}
