package com.academy.lesson10;

import com.academy.Abonent;
import com.academy.lesson08.ParseAbonentException;

public class AbonentParser {
    // Извлекаем номер телефона как строку
    public static String parsePhoneNumber(String abonentRawString) {
        String[] parts = abonentRawString.trim().split(" ");
        String phoneNumber = parts[4];
        return phoneNumber;
    }

    // Извлекаем пол как символ
    public static char parseGender(String abonentRawString) {
        String[] resultString = abonentRawString.trim().split(" ");
        char gender=resultString[3].charAt(0);
        return gender;
    }

    // Извлекаем возраст как число
    public static int parseAge(String abonentRawString) {
        String noSpaces = abonentRawString.trim();
        String[] parts = noSpaces.split(" ");
        String age = parts[2];
        return Integer.parseInt(age);
    }


    // Извлекаем фамилию  как строку
    public static String parseLastName(String abonentRawString) {
        String noSpaces = abonentRawString.trim(); //вернули точно такую же строку, но без начальных и конечных пробелов!!!
        String [] parts = noSpaces.split(" "); // разбили нашу строку по пробелам на отдельные части
        String rawLastName = parts[1]; // iVanova
        // 1 способ 'iVanova' => 'Ivanova'
// String lastName = rawLastName.substring(0, 1).toUpperCase() + rawLastName.substring(1).toLowerCase();
        String lastName = Character.toString(rawLastName.charAt(0)).toUpperCase() + rawLastName.substring(1).toLowerCase();
        return lastName; // вернули конкретный элемент из массива отдельных слов, которые получились из нашей строки
    }

    // Извлекаем имя как строку
// (аналогично фамилии - для одинаковой логики нужно создать общий метод)
    public static String parseFirstName(String abonentRawString) throws ParseAbonentException {
        if (abonentRawString == null || abonentRawString.isEmpty()) {
            ParseAbonentException exc = new ParseAbonentException();
            throw exc;
        }

        // Если строка пустая => бросаем ParseAbonentException
        String noSpaces = abonentRawString.trim(); //вернули точно такую же строку, но без начальных и конечных пробелов!!!
        String [] parts = noSpaces.split(" "); // разбили нашу строку по пробелам на отдельные части
        String rawFirstName = parts[0]; // 'heLen'
        // 'heLen' => 'Helen'
        String firstName = Character.toString(rawFirstName.charAt(0)).toUpperCase() + rawFirstName.substring(1).toLowerCase();
        return firstName; // вернули конкретный элемент из массива отдельных слов, которые получились из нашей строки
    }

    public static Abonent parseAbonent (String abonentRawString) throws ParseAbonentException {
        String firstName = parseFirstName(abonentRawString);
        String lastName = parseLastName(abonentRawString);
        int age = parseAge(abonentRawString);
        char gender = parseGender(abonentRawString);
        String phoneNumber = parsePhoneNumber(abonentRawString);
        Abonent abonent = new Abonent(firstName, lastName, age, gender, phoneNumber);

        return abonent;
    }
}
