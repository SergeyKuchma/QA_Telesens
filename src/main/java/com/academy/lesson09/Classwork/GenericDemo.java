package com.academy.lesson09.Classwork;

public class GenericDemo {
    public static void main(String[] args) {
        Container<String> container = new Container<>();
                container.setValue("Hello");
        String msg = container.getValue();
        System.out.println(msg);

        Container<Integer> integerContainer=new Container<>();
        integerContainer.setValue(23);
        Integer n=integerContainer.getValue();
        System.out.println(n);

        Container<Object> containerObj = new Container<>();
        containerObj.setValue(1);
        int m =(int)containerObj.getValue();
        System.out.println(m);

        //нужно создать массив пар
        PairContainer<Integer, String> pair = new PairContainer<>();
        pair.setFirstValue(2);
        pair.setSecondValue("2");

        System.out.println(pair.getFirstValue()+"=>"+pair.getSecondValue());

      //  PairContainer<Integer[], String[]> pairContainer = new PairContainer<>();
       // pairContainer.setFirstValue(new Integer[{1,2,3}]);




    }
}
