package com.academy;

import java.util.Objects;

public class Abonent {
    private String firstName ;//= null;
    private String lastName ;//= null;
    private int age;
    private char gender;
    private String phoneNumber;//= null;

    public Abonent() {
    }

    public Abonent(String firstName, String lastName, int age, char gender, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Abonent{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abonent abonent = (Abonent) o;
        return age == abonent.age &&
                gender == abonent.gender &&
                Objects.equals(firstName, abonent.firstName) &&
                Objects.equals(lastName, abonent.lastName) &&
                Objects.equals(phoneNumber, abonent.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, gender, phoneNumber);
    }
}
