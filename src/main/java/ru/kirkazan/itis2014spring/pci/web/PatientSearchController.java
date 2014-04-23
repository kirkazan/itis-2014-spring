package ru.kirkazan.itis2014spring.pci.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kirkazan.itis2014spring.pci.service.PatientInfo;
import ru.kirkazan.itis2014spring.pci.service.PatientService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

@Controller
public class PatientSearchController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("patient")
    @ResponseBody
    public List<PatientInfo> search(@RequestParam String s) {

        CheckResultFIOBD res = new CheckResultFIOBD();
        res = res.match(s);
        if (res.match)
            return patientService.searchByFioAndBd(res.surname, res.name, res.patrName, res.year);

        CheckResultName res1 = new CheckResultName();
        res1 = res1.match(s);
        if (res1.match)
            return patientService.searchByName(res1.surname, res1.name, res1.patrName);

        CheckResultContactOrDocument res2 = new CheckResultContactOrDocument();
        res2 = res2.match(s);
        if (res2.match) {
            List<PatientInfo> result = new ArrayList<>();
            List<PatientInfo> arrayList1 = patientService.searchByContact(res2.contact);
            List<PatientInfo> arrayList2 = patientService.searchByDocument(res2.series, res2.number);
            result.addAll(arrayList1);
            result.addAll(arrayList2);

            return result;
        }

        return Collections.emptyList();
    }

    static class CheckResultFIOBD {
        boolean match;
        String surname;
        String name;
        String patrName;
        int year;

        CheckResultFIOBD() {
        }

        CheckResultFIOBD(boolean match, String surname, String name, String patrName, int year) {
            this.match = match;
            this.surname = surname;
            this.name = name;
            this.patrName = patrName;
            this.year = year;

        }

        public CheckResultFIOBD match(String searchString) {
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
        boolean match;
        String surname;
        String name;
        String patrName;

        CheckResultName() {
        }

        CheckResultName(boolean match, String surname, String name, String patrName) {
            this.match = match;
            this.surname = surname;
            this.name = name;
            this.patrName = patrName;
        }

        public CheckResultName match(String searchString) {

            ArrayList<String> array = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(searchString, " ", false);
            int j = 0;
            while (stringTokenizer.hasMoreElements()) {
                array.add(j, stringTokenizer.nextToken());
                j++;
            }
            if (j == 3) {
                match = true;
                surname = array.get(0);
                name = array.get(1);
                patrName = array.get(2);
            }
            return new CheckResultName(match, surname, name, patrName);
        }

    }

    static class CheckResultContactOrDocument {
        boolean match;
        String contact;
        String series;
        String number;

        CheckResultContactOrDocument() {
        }

        CheckResultContactOrDocument(boolean match, String contact) {
            this.match = match;
            this.contact = contact;
        }

        CheckResultContactOrDocument(boolean match, String series, String number) {
            this.match = match;
            this.series = series;
            this.number = number;
        }

        public CheckResultContactOrDocument match(String searchString) {
            ArrayList<String> array = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(searchString, " ", false);
            int j = 0;
            while (stringTokenizer.hasMoreElements()) {
                array.add(j, stringTokenizer.nextToken());
                j++;
            }
            if (j == 1) {
                contact = searchString;
                return new CheckResultContactOrDocument(true, contact);
            } else {
                series = array.get(0);
                number = array.get(1);
                return new CheckResultContactOrDocument(true, series, number);
            }

        }
    }
}







