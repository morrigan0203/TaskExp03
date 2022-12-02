package com.jexp3;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainHomeWork {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
    public static void main(String[] args) {
        boolean addPerson = true;
        while (addPerson) {
            try {
                String[] data = getData();
                Person person = getPerson(data);
                writeToFile(person);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want add more person? Please, type y or yes, otherwise it will no.");
            String yesNo = sc.nextLine();
            addPerson = "y".equalsIgnoreCase(yesNo) || "yes".equalsIgnoreCase(yesNo);
        }
    }

    private static void writeToFile(Person person) {
        try(FileWriter fr = new FileWriter(person.surName+".txt", true)) {
            fr.append(person.toString());
            fr.append("\n");
        } catch (IOException e) {
            throw new RuntimeException("Some problem write to file.", e);
        }
    }

    public static Person getPerson(String[] data) {
        String[] fio = new String[3];
        int fioIndex = 0;
        Date dateOfBirth = null;
        Integer phone = null;
        Character sex = null;

        for (int i = 0; i < data.length; i++) {
            if (isDate(data[i])) {
                if (dateOfBirth != null) {
                    throw new RuntimeException("Two dates in data.");
                }
                try {
                    dateOfBirth = sdf.parse(data[i]);
                } catch (ParseException e) {
                    throw new RuntimeException("Something wrong with date " + data[i], e);
                }
            } else if (isNumber(data[i])) {
                if (phone != null) {
                    throw new RuntimeException("Two phone numbers in data.");
                }
                try {
                    phone = Integer.parseInt(data[i]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Something wrong with number " + data[i], e);
                }
            } else if (isSex(data[i])) {
                if (sex != null) {
                    throw new RuntimeException("Two sex data in data.");
                }
                sex = data[i].charAt(0);
            } else {
                if (fio[fioIndex] != null) {
                    throw new RuntimeException("String names are very much.");
                }
                fio[fioIndex++] = data[i];
            }
        }
        return new Person(fio[0], fio[1], fio[2], dateOfBirth, phone, sex);
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isDate(String str) {
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private static boolean isSex(String str) {
        return str.equalsIgnoreCase("f") || str.equalsIgnoreCase("m");
    }

    private static String[] getData() {
        String[] res = null;
        boolean stop = false;
        while(!stop) {
            System.out.println("Please input data about person: FIO, date of birth (format dd.mm.yyyy), phone number (only numbers) and sex of person (f or m)");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            res = line.split(" ");
            if (res.length < 6) {
                System.out.println("Not enough input data. Please try again.");
            } else if (res.length > 6) {
                System.out.println("Input data is more then need. Please try again.");
            }
            stop = true;
        }
        return res;
    }
}
