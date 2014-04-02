package ru.kirkazan.itis2014spring.pci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Татьяна on 06.03.14.
 */
@Controller
public class PatientSearchController {

    @Autowired
    private PatientService patientService;

    public List<Patient> search(String searchString) {

        CheckResultFIOBD res = CheckResultFIOBD.match(searchString);
        if (res.match)
            return patientService.searchByFioAndBd(res.surname, res.name, res.patrName, res.year);

        CheckResultName res1 = CheckResultName.match(searchString);
        if (res1.match)
            return patientService.searchByName(res1.surname, res1.name, res1.patrName);

        CheckResultContact res2 = CheckResultContact.match(searchString);
        if (res2.match)
            return patientService.searchByContact(res2.contact);


        return Collections.emptyList();
    }

    static class CheckResultFIOBD {
        static boolean match;
        static String surname;
        static String name;
        static String patrName;
        static int year;

        CheckResultFIOBD(boolean match, String surname, String name, String patrName, int year) {
            this.match = match;
            this.surname = surname;
            this.name = name;
            this.patrName = patrName;
            this.year = year;

        }

        public static CheckResultFIOBD match(String searchString) {
            if (searchString.length() == 5) {

                char c1 = searchString.charAt(searchString.length() - 1);
                char c2 = searchString.charAt(searchString.length() - 2);

                if (c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9') {
                    String str = "";
                    str += String.valueOf(c2);
                    str += String.valueOf(c1);
                    Integer number = Integer.valueOf(str);
                    match = true;
                    surname = searchString.substring(0, 1);
                    name = searchString.substring(1, 2);
                    patrName = searchString.substring(2, 3);
                    year = number;
                }
            }
            return new CheckResultFIOBD(match, surname, name, patrName, year);
        }
    }

    static class CheckResultName {
        static boolean match;
        static String surname;
        static String name;
        static String patrName;

        CheckResultName(boolean match, String surname, String name, String patrName) {
            this.match = match;
            this.surname = surname;
            this.name = name;
            this.patrName = patrName;
        }

        public static CheckResultName match(String searchString) {

            ArrayList<String> array = new ArrayList();
            StringTokenizer stringTokenizer = new StringTokenizer(searchString, " ", false);
            int j = 0;
            while (stringTokenizer.hasMoreElements()) {
                array.add(j,stringTokenizer.nextToken());
                j++;
            }
            if(j==3)
               match=true;

            return new CheckResultName(match,array.get(0),array.get(1),array.get(2));
        }
    }

    static class CheckResultContact {
        static boolean match;
        String contact;


        CheckResultContact(boolean match, String contact) {
            this.match = match;
            this.contact = contact;
        }

        public static CheckResultContact match(String searchString) {
            char c1 = searchString.charAt(0);
            char c2 = searchString.charAt(1);
            // проверка, является ли телефоном
            if ((c1 == '+' && c2 == '7') || (c1 == '7' && c2 == '9') || (c1 == '8' && c2 == '9')) {
                match = true;
            }
            // проверка, является ли адресом почты
           for(int j=0; j<searchString.length();j++){
                if (searchString.charAt(j) == '@')
                    match = true;
           }
            return new CheckResultContact(match, searchString);
        }

    }

}







