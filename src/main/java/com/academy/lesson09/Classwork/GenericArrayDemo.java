package com.academy.lesson09.Classwork;

import com.academy.lesson09.GenericArray;
import com.academy.lesson09.GenericArrayException;

import java.util.Iterator;

public class GenericArrayDemo {
    public GenericArrayDemo() {
    }

    public static void main(String[] args) throws GenericArrayException {
        //GenericArray<String>getStrArray = new GenericArray<>(new String[]);
        String[] strArr = {"1","2","3","10"};

        GenericArray<String> genStrArray = new GenericArray<>(strArr);

        genStrArray.set(0,"111");// положил по индексу ноль - здесь может быть эксепшн!!!
        String str = genStrArray.get(0);//получаем значение по индексу

        System.out.println("Ваша строка: "+str);
        assert str.equals("111");

        System.out.println("Ваша массив: "+genStrArray);

//        for (String element : genStrArray) {
//            System.out.println(element);
//        }

        System.out.println("group foreach demo");
        Group group = new Group();

        for(int element : group)
            System.out.println(element);

        System.out.println("Так работает foreach");
        Iterator<Integer> iterator = group.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

    }
}
