package ru.kirkazan.itis2014spring.pci;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Татьяна on 06.03.14.
 */
public class PatientSearchController {

    private PatientService patientService;

    public List<Patient> search(String searchString)
    {

        CheckResultFIOBD res = CheckResultFIOBD.match(searchString);
        if (res.match)
            return patientService.searchByFioAndBd(res.surname, res.name, res.patrName, res.year);

        CheckResultFIO res = CheckResultFIO.match(searchString);
        if (res.match)
            return patientService.searchByName(res.surname, res.name, res.patrName);

        return Collections.emptyList();
    }

    static class  CheckResultFIOBD
    {
        boolean match;
        String surname;
        String name;
        String patrName;
        int year;

        public static CheckResultFIOBD match (String searchString)
        {
            return new CheckResultFIOBD();
        }
    }
}







