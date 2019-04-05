/*
3) Создать класс GenArrayGenerator, которые
	a) инициализирует GenArray для целых чисел емкостью 20 элементов
	b) заполнить его случайными значениями 10-ти значных чисел, первый три цифры 999, остальные - любые,
	последняя заканчивается на 0 или 5
		(для генерации следующего реализовать отдельный метод: long generateNextNumber() {...}
	с) выводит на консоль содержимое GenArray
	d)* сортирует GenArray, используя Comparator по умолчанию
	e)* сортирует GenArray, используя внешний Comparator
 */
package com.academy.lesson09;

import java.util.*;

public class GenArrayGenerator {
      public static void main(String[] args) throws GenericArrayException {
        Long[] intArr = new Long[20];//инициализируется исходный массив
        GenericArray<Long> genIntArray = new GenericArray<>(intArr);

        for (int i = 0; i < intArr.length; i++){

            try {               //сюда добавить обработку exceptions
                genIntArray.set(i,generateNextNumber());
                //System.out.println(genIntArray.get(i));//для вывода массива в столбец
            } catch (GenericArrayException e) {
                e.printStackTrace();
            }
        }
         System.out.println("****** Исходный массив случайных чисел **********\n"+genIntArray);

        List<String> list = new ArrayList<>();

          for (int i = 0; i < intArr.length; i++)
          list.add(String.valueOf(genIntArray.get(i))); //добавляем в List все числа из генерика, преобразуя их в String

          Collections.sort(list); // Сортируем List
          System.out.println("Отсортированный массив\n"+list);
      }


    public static Long generateNextNumber(){// возвращает случайное значение 10-ти значных чисел, первый три цифры 999,
                                            // остальные - любые, последняя заканчивается на 0 или 5
        String str_temp="999";
        Random ran = new Random();

        for (int i = 0; i < 6; i++) str_temp+=ran.nextInt(10);// возвращает случайное число от 0 до 9 и накапливает его в строку str_temp
        if (ran.nextInt(10)>4) str_temp+=5; //заполняем последнее число или 5
            else str_temp+=0; //или 0
        return  Long.parseLong(str_temp);//возвращаем строку, преобразовывая ее в Long
    }

}
