//3) List
//	a) создать список List целых чисел
//	b) заполнить его случайными значениями 10-ти значных чисел, первый три цифры 999, остальные - любые, последняя заканчивается на 0 или 5
//	c) Вывести список на экран
//	d) Проверить значения списка, с помощью регулярного выражения.

package com.academy.lesson10;

import com.academy.lesson09.GenArrayGenerator;
import java.util.ArrayList;
import java.util.List;

public class HomeTask10_3 extends GenArrayGenerator{
    private static final String REGEX_FOR_NUMBERS ="^999\\d{6}[05]$";//работают и такие: "^\\d{10}$"; [0-9]{10}; "999\\d{7}$";

    public static void main(String[] args) {

        List<Long> list = new ArrayList<>();
        String test;
        System.out.println("****** Исходный массив случайных чисел **********");

        for (int i = 0; i < 20; i++){
            list.add(i,generateNextNumber());
            System.out.print(list.get(i));//для вывода массива в столбец
            test=list.get(i).toString();

            assert test.matches(REGEX_FOR_NUMBERS);//проверка, что число начинается на 999 и в конце 0 или 5

            if (test.matches(REGEX_FOR_NUMBERS)) System.out.println(" - проверено - OK!");
                else System.out.println(" - число не соответствует критериям: первые три цифры 999, затем 6 - любых цифр и последняя заканчивается на 0 или 5");
        }

    }
}
