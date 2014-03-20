package ru.kirkazan.itis2014spring.pci;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Татьяна on 06.03.14.
 */
public class PatientSearchController {

    private PatientService patientService;

    List<Patient> search(String searchString) {

        if (searchString.length() == 5){

            char c1 = searchString.charAt(searchString.length()-1);
            char c2 = searchString.charAt(searchString.length()-2);

            if (c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9') {
                String str = "";
                str += String.valueOf(c2);
                str += String.valueOf(c1);
                Integer number = Integer.valueOf(str);
                return patientService.searchByFioAndBd(searchString.substring(0,1), searchString.substring(1,2), searchString.substring(2,3), number);
            }
        }
        if (searchString.length() > 5) {

            String [] array = new String[3];
            StringTokenizer stringTokenizer = new StringTokenizer(searchString, " ", false);
            int j = 0;
            while (stringTokenizer.hasMoreElements()) {
                array[j] = stringTokenizer.nextToken();
                j++;
            }

                int k = 0;
                return patientService.searchByName(array[k], array[k+1], array[k+2]);
            }
        return null;
    }
}







