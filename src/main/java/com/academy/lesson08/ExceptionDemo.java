package com.academy.lesson08;

import static com.academy.lesson08.AbonentDemo2.parseAge;
import static com.academy.lesson08.AbonentDemo2.parseFirstName;
import static com.academy.lesson08.AbonentDemo2.parseGender;
import static com.academy.lesson08.AbonentDemo2.parseLastName;
import static com.academy.lesson08.AbonentDemo2.parsePhoneNumber;

public class ExceptionDemo {
    public static void main(String[] args) throws ParseAbonentException {
        // comment
        //String abonentRawString= "  heLen iVanova 35 f 0501234567";
        String abonentRawString= " "; //
        String name = null;

        try {//проверяем First Name на пустую строку
             name = parseFirstName (abonentRawString);
        } catch (ParseAbonentException exc) {// словили пустую строку
            System.out.println("Exception для поля [FirstName]= " + exc.getMessage());
            // System.exit(1);
        } catch (Exception exc) {
            System.out.println("Common error для [FirstName] " + exc);
            System.exit(1);
        }
        System.out.println("parseFirstName (abonentRawString) = "+name);


        //проверяем Last Name на пустую строку
        name = null;
        try {
            name = parseLastName (abonentRawString);
        } catch (ParseAbonentException exc) {// словили пустую строку
            //System.out.println("У вас пустая строка для поля LastName " + exc);
            System.out.println("Exception для поля [LastName] = " + exc.getMessage());
            //System.exit(1);
        } catch (Exception exc) {
            System.out.println("Common error для [LastName] " + exc);
            System.exit(1);
        }
        System.out.println("parseLastName (abonentRawString) = "+name);


        //проверяем AGE на пустую строку и на то, что оно должно быть цифрой
        int age=0;
        // если словили пустую строку или НЕ цифру
        try {

            age = parseAge(abonentRawString);
        } catch (ParseAbonentException exc) {// словили пустую строку или не цифру
            System.out.println("Exception для поля [Возраст]: " + exc.getMessage());
           // System.exit(1);
        } catch (Exception exc) {
            System.out.println("Common error " + exc);
            System.exit(1);
        }
        System.out.println("parseAge(abonentRawString) = "+ age);


        //проверяем Gender на пустую строку
        Character gender=null;

        // если словили пустую строку
        try {

            gender = parseGender(abonentRawString);
        } catch (ParseAbonentException exc) {// словили пустую строку
            System.out.println("Exception для поля [Gender]: " + exc.getMessage());
            // System.exit(1);
        } catch (Exception exc) {
            System.out.println("Common error " + exc);
            System.exit(1);
        }
        System.out.println("parseGender(abonentRawString) = " + gender);


        //проверяем Phone на пустую строку
        //abonentRawString="  heLen iVanova 35 F 1234567";

        String phoneNumber = null;

        // если словили пустую строку
        try {

            phoneNumber = parsePhoneNumber(abonentRawString);
        } catch (ParseAbonentException exc) {// словили пустую строку
            System.out.println("Exception для поля [phoneNumber]: " + exc.getMessage());
            System.exit(1);
        } catch (Exception exc) {
            System.out.println("Common error " + exc);
            System.exit(1);
        }
        System.out.println("parsePhoneNumber(abonentRawString) = " + phoneNumber);


    }
}
