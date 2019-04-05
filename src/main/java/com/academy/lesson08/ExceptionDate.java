package com.academy.lesson08;

import com.academy.lesson08.ex.Date;

public class ExceptionDate {
    public static void main(String[] args) throws IllegalDateException {

        Date date1 = new Date(1,1,2012);
        Date date2 = new Date(17, 2, 2019);
        Date date3 = new Date(29, 2, 2020);

        Date date4 = new Date(-1, 1, 2012);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
    }
}
