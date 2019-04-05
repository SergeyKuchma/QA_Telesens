package com.academy.lesson06;

import com.academy.Abonent;

public class AbonentTests {
    private static final String REGEX_FOR_PHONENUMBER = "^\\d{10}$";

    public static void main(String[] args) {
        // Здесь напишем тест
        Abonent abonent = readAbonent();// ?
        // Проверить 1 Имя и Фамилия в верхнем регистре
        String firstName = abonent.getFirstName();
        char firstLetter = firstName.charAt(0);
        assert Character.isUpperCase(firstLetter);

        String lastname = abonent.getLastName();
        //System.out.println("Lastname :" + lastname);
        firstLetter = lastname.charAt(0);
// if (Character.isUpperCase(firstLetter))
// System.out.println("First letter in upper case");
// else
// System.out.println("First letter in lower case");

        if (!Character.isUpperCase(firstLetter)) {
            System.out.println(
                    String.format("First letter in the last name '%s' in lower case", lastname));
            assert false;
        }

        // Проверить возраст > 1 < 130
        int age = abonent.getAge();
        assert age > 1 && age < 130;

        // Пол 'f' либо 'm'
        assert (abonent.getGender() == 'f') || (abonent.getGender() == 'm');

        // Телефонный номер состоит только из цифр

        String phoneNumber = abonent.getPhoneNumber();
        assert phoneNumber.matches(REGEX_FOR_PHONENUMBER);
    }

    private static Abonent readAbonent() {
        return new Abonent("Helen", "ivanova", 28, 'f', "0501234567");
    }
}