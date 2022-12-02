package com.jexp3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
    String surName;
    String firstName;
    String midName;
    Date dateOfBirth;
    int phone;
    char sex;

    public Person(String surName, String firstName, String midName, Date dateOfBirth, int phone, char sex) {
        this.surName = surName;
        this.firstName = firstName;
        this.midName = midName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.sex = sex;
    }

    public String toString() {
        return surName + " " +
                firstName + " " +
                midName + " " +
                sdf.format(dateOfBirth) + " " +
                phone + " " +
                sex;
    }
}
