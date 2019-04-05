// Программа работает через Class Abonent
package com.academy.lesson10;

import com.academy.Abonent;
import com.academy.lesson08.ParseAbonentException;

public class AbonentDemo {
    public static void main(String[] args) throws ParseAbonentException {
        String abonentRawString = "  heLen iVanova 35 f 0501234567";
        // Parse abonent
        // Парсим абонента и результат записываем в поля класса

        // если строка пустая  - бросаем ParseAbonentException
        if (abonentRawString == null || abonentRawString.isEmpty())
            throw new ParseAbonentException("Вы ввели пустую строку");


        Abonent abonent = new Abonent();
        abonent.setFirstName(parseFirstName(abonentRawString));
        abonent.setLastName(parseLastName(abonentRawString));
        abonent.setAge(parseAge(abonentRawString));
        abonent.setGender(parseGender(abonentRawString));
        abonent.setPhoneNumber(parsePhoneNumber(abonentRawString));

        // LOG
        // Выводим данные абонента (теперь в одну строку)
        System.out.println("Abonent: " + abonent);

        // Test
        // Теперь проверка в одну строку
        Abonent expectedAbonent = new Abonent("Helen", "Ivanova", 35, 'f', "0501234567");
        assert abonent.equals(expectedAbonent);
    }

    // Извлекаем номер телефона как строку
    public static String parsePhoneNumber(String abonentRawString) throws ParseAbonentException {
        if (abonentRawString == null || abonentRawString.isEmpty() || abonentRawString.matches(" "))
            throw new ParseAbonentException();
        String [] str = trimmingNoSpaces(abonentRawString);

        if (str.length <4) //проверка на существование данных для Phone
            throw new ParseAbonentException("для поля [PhoneNumber] нет данных");

        if (str[4] == null || str[4].isEmpty() || str[4].matches(" "))
            throw new ParseAbonentException("поле [PhoneNumber] - пустое");


        String phoneNumber = trimmingNoSpaces(abonentRawString)[4];

        return phoneNumber;
    }

    // Извлекаем пол как символ
    public static char parseGender(String abonentRawString) throws ParseAbonentException {

        if (abonentRawString == null || abonentRawString.isEmpty() || abonentRawString.matches(" "))
            throw new ParseAbonentException();


            String [] str = trimmingNoSpaces(abonentRawString);

        if (str.length <3) //проверка на существование данных для Gender
            throw new ParseAbonentException("для поля [Gender] нет данных");

        if (str[3] == null || str[3].isEmpty() || str[3].matches(" "))
            throw new ParseAbonentException("поле [Gender] - пустое");


        char gender=trimmingNoSpaces(abonentRawString)[3].charAt(0);

        //нужна проверка на то, что это не должна быть цифра

        return gender;
    }

    // Извлекаем возраст как число
    public static int parseAge(String abonentRawString) throws ParseAbonentException {

        // если строка пустая  - бросаем ParseAbonentException
        if (abonentRawString == null || abonentRawString.isEmpty())
            throw new ParseAbonentException();

        if (abonentRawString.matches(" ") || abonentRawString.length() <=2) //проверка на существование данных для Age
            throw new ParseAbonentException("У вас только один пробел во введенной строке - для поля Age нет данных");


        String age = trimmingNoSpaces(abonentRawString)[2];

       // если Age пустая или это не число  - бросаем ParseAbonentException
        if (age == null || age.isEmpty()) // проверка на пустое поле
            throw new ParseAbonentException("Поле [Возраст] не может быть пустым");

        if (!age.matches ("\\d+")) //  Проверка на цифры
            throw new ParseAbonentException("Поле [Возраст] должно содержать только цифры, а вы ввели: "+age);

        return Integer.parseInt(age);
    }


    // Извлекаем фамилию  как строку
    public static String parseLastName(String abonentRawString) throws ParseAbonentException{

        // если строка пустая  - бросаем ParseAbonentException
        if (abonentRawString == null || abonentRawString.isEmpty())
            throw new ParseAbonentException("У вас пустая строка для поля LastName");

        String [] str = trimmingNoSpaces(abonentRawString);

        if (str.length <2) //проверка на существование данных для Last Name
            throw new ParseAbonentException("для поля [LastName] нет данных");

        if (str[1] == null || str[1].isEmpty() || str[1].matches(" "))
            throw new ParseAbonentException("поле [LastName] - пустое");

        String rawLastName = trimmingNoSpaces(abonentRawString)[1]; // 'Ivanova'

        if (rawLastName == null || rawLastName.isEmpty() || rawLastName == " ")
            throw new ParseAbonentException("У вас пустая строка для поля LastName");


        // 1 способ 'iVanova' => 'Ivanova'
//        String lastName = rawLastName.substring(0, 1).toUpperCase() + rawLastName.substring(1).toLowerCase();
        String lastName = Character.toString(rawLastName.charAt(0)).toUpperCase() + rawLastName.substring(1).toLowerCase();

        if (lastName == null || lastName.isEmpty() || lastName == " ")
            throw new ParseAbonentException("У вас пустая строка для поля LastName");

        return lastName; // вернули конкретный элемент из массива отдельных слов, которые получились из нашей строки
    }

    // Извлекаем имя как строку
    public static String parseFirstName(String abonentRawString) throws ParseAbonentException {

        if (abonentRawString == null || abonentRawString.isEmpty())
            throw new ParseAbonentException("Вы ввели пустую строку");

        String rawFirstName = trimmingNoSpaces(abonentRawString)[0]; // 'heLen'
        if (rawFirstName == null || rawFirstName.isEmpty() || rawFirstName == " ")
            throw new ParseAbonentException("У вас пустая строка для поля FirstName");

        String firstName = Character.toString(rawFirstName.charAt(0)).toUpperCase() + rawFirstName.substring(1).toLowerCase();
        // если строка пустая  - бросаем ParseAbonentException
        if (firstName == null || firstName.isEmpty() || firstName == " ")
            throw new ParseAbonentException("У вас пустая строка для поля FirstName");

        return firstName; // вернули конкретный элемент из массива отдельных слов, которые получились из нашей строки
    }

    public static String [] trimmingNoSpaces(String abonentRawString) {
    return abonentRawString.trim().split(" "); //убираем начальные и конечные пробелы, а затем разделяем строку по пробелам

    }



}
