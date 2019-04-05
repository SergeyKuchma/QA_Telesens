package com.academy.lesson09.Classwork;

public class Demo1 {
    public static void main(String[] args){
        String [] arrStr={"1","2","3","4","5"};

        Integer[] arrInt = new Integer[arrStr.length];
        int i=0;
        //перегоняем массив в Integer
        System.out.println("Массив Integer после String:");

        for(String str:arrStr){
            arrInt[i]=Integer.parseInt(str);//может быть Exception

            System.out.println(arrInt[i]); //выводим в столбец
            i++;
        }

        //массив по Object
        Object [] arrObj = new Object[arrStr.length];

        System.out.println("Массив Object:");
            for(i = 0; i < arrStr.length; i++) {
                arrObj[i] = arrInt[i];
                System.out.println(arrObj[i]); //выводим в столбец
            }
        //Возвращаем из Object в массив Integer
        System.out.println("Массив Integer после Object:");

        Integer[] newArrInt= new Integer[arrStr.length];

              for(i = 0; i < arrStr.length; i++)
                {
                    newArrInt[i] = (Integer)arrObj[i];
                    System.out.println(newArrInt[i]); //выводим в столбец
                }
        //нужно создать массив пар
        /*
        PairContainer<Integer, String>[] pairArr = new PairContainer<Integer, String>[arrStr.length];
        pair.setFirstValue(2);
        pair.setSecondValue("2");
*/
        System.out.println("Вывод двух массивов через контейнеры");
        PairContainer<Integer[], String[]> pairContainer = new PairContainer<>();
        pairContainer.setFirstValue(arrInt);
        pairContainer.setSecondValue(arrStr);
        System.out.println(pairContainer);


    }
}
