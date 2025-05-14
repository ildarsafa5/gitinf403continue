package ru.itis.inf403;

import java.util.Date;

public class Person {
    private String gender;
    private Date birthdate;
    private String fromcity;

    public String getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object a) {
        if (gender.equals(((Person)a).getGender()) && birthdate.equals(((Person)a).getBirthdate())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (gender.hashCode()+birthdate.hashCode())*31;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFromcity() {
        return fromcity;
    }

    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
    }

    public String toString() {
        return birthdate.toString();
    }
}
