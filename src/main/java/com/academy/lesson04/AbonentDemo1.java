package com.academy.lesson04;
//Программа реализована внутренними методами

public class AbonentDemo1  {
    public static void main(String[] args) {
        String abonentRawString = "  heLen iVanova 35 f 0501234567";

        // Parse abonent
        // из исходной строки извлекаем данные об абоненте,
        // используя вспомогательные методы parseXXX
        String firstName = parseFirstName(abonentRawString);
        String lastName = parseLastName(abonentRawString);
        int age = parseAge(abonentRawString);
        char gender = parseGender(abonentRawString);
        String phoneNumber = parsePhoneNumber(abonentRawString);

        // LOG
        // Выводим данные абонента
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);

        // Test
        // Проверяем, что данные получены корректно
        assert firstName.equals("Helen");
        assert lastName.equals("Ivanova");
        assert age == 35;
        assert gender == 'f';
        assert phoneNumber.equals("0501234567");
    }

    // Извлекаем номер телефона как строку
    private static String parsePhoneNumber(String abonentRawString) {
        String[] parts = abonentRawString.trim().split(" ");
        String phoneNumber = parts[4];
        return phoneNumber;
    }

    // Извлекаем пол как символ
    private static char parseGender(String abonentRawString) {
        String[] resultString = abonentRawString.trim().split(" ");
        char gender=resultString[3].charAt(0);
        return gender;
    }

    // Извлекаем возраст как число
    private static int parseAge(String abonentRawString) {
        String noSpaces = abonentRawString.trim();
        String[] parts = noSpaces.split(" ");
        String age = parts[2];
        return Integer.parseInt(age);
    }


    // Извлекаем фамилию  как строку
    private static String parseLastName(String abonentRawString) {
        String noSpaces = abonentRawString.trim(); //вернули точно такую же строку, но без начальных и конечных пробелов!!!
        String [] parts = noSpaces.split(" "); // разбили нашу строку по пробелам на отдельные части
        String rawLastName = parts[1]; // iVanova
        // 1 способ 'iVanova' => 'Ivanova'
//        String lastName = rawLastName.substring(0, 1).toUpperCase() + rawLastName.substring(1).toLowerCase();
        String lastName = Character.toString(rawLastName.charAt(0)).toUpperCase() + rawLastName.substring(1).toLowerCase();
        return lastName; // вернули конкретный элемент из массива отдельных слов, которые получились из нашей строки
    }

    // Извлекаем имя как строку
    // (аналогично фамилии - для одинаковой логики нужно создать общий метод)
    private static String parseFirstName(String abonentRawString) {
        String noSpaces = abonentRawString.trim(); //вернули точно такую же строку, но без начальных и конечных пробелов!!!
        String [] parts = noSpaces.split(" "); // разбили нашу строку по пробелам на отдельные части
        String rawFirstName = parts[0]; // 'heLen'
        // 'heLen' => 'Helen'
        String firstName = Character.toString(rawFirstName.charAt(0)).toUpperCase() + rawFirstName.substring(1).toLowerCase();
        return firstName; // вернули конкретный элемент из массива отдельных слов, которые получились из нашей строки
    }
}