package com.academy.lesson03;
import com.academy.Abonent;

public class Parse1 {
    public static void main(String[] args) {
        String abonentRawString = " Helen iVanov2a 35 f  0501234567";
 //       String firstName = parseFirstName(abonentRawString);
//        String lastName = parseLastName(abonentRawString);
        int age = parseAge(abonentRawString);
//        char gender = parseGender(abonentRawString);
        String phoneNumber = parsePhoneNumber(abonentRawString);


        //инкапсулирование

        Abonent abonent = new Abonent(); //Описываем абонента класс которого уже создали отдельно
        abonent.setFirstName(parseFirstName(abonentRawString)); // не дает потому что private, а только через метод

        System.out.println("First Name:"+ abonent.getFirstName()); //мы получили из класса

        System.out.println("Abonent: " + abonent); //мы получили из класса и можем выводить целиком





        System.out.println("Ваш телефон:"+phoneNumber);

   //     TEST
        Abonent expectedAbonent= new Abonent("Helen", "Ivanova", 35, 'f', "0501234567");
        expectedAbonent.setLastName("Ivanova");
        assert abonent.equals(expectedAbonent); //


        //     assert abonent.getLastName().equals("Ivanova");

    }

    private static String parseFirstName(String abonentRawString) {
        return null;
    }

    private static int parseAge(String abonentRawString) {
        String noSpaces = abonentRawString.trim(); //удаляем все лишние пробелы
        String[] parts = noSpaces.split(" "); //разделяем строку на массив
        return Integer.parseInt(parts[2]);
    }


    // Методы:
    //

    private static String parsePhoneNumber(String abonentRawString) {
        String noSpaces = abonentRawString.trim(); //удаляем все лишние пробелы
        String[] parts = noSpaces.split(" "); //разделяем строку на массив

        return (parts[5]);
    }



}