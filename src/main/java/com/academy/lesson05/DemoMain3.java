package com.academy.lesson05;

import com.academy.lesson05.ex.ExtendedTime;

public class DemoMain3 {

    public static void main(String[] args) {
        System.out.println("lesson 05");
        System.out.println("Demo ExtendedTime");
        ExtendedTime dateExtendedTime = new ExtendedTime(11, 2, 2019, 20, 45,33,555);
        System.out.println("dateExtendedTime= "+dateExtendedTime); // 11/02/2019 20:45:33.555
        assert dateExtendedTime.toString().equals("11/02/2019 20:45:33.555"); //проверка на значение


        // ==================

/*
        // 3 способ - здесь уже с миллисекундами
        Date dateTime3 = new ExtendedTime()
                .withMillisecond(555)
                .withHour(20)
                .withMinute(58)
                .withSecond(35)
                .withDay(44)
                .withMonth(2)
                .withYear(2019);
        // ...
        System.out.println("dateTime3="+dateTime3);
*/

    }
}