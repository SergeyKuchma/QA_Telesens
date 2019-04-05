/*1)
        a) Создать массив типа Component (10 элементов), элементы которого могут любые потомки этого класса
        (Button, Label, CheckBox, RoundButton - см. иерархию в презентации)
        Component[] components = ...


        b) Наполнить массив:
        (экземплярами классов Button, Label, CheckBox, RoundButton)

        с) В цикле вывести все элемены Componentы на экран
        d) В цикле перебрать массив Component и вывести только Button и его потомков
        */
package com.academy.lesson06;

import com.academy.lesson06.ex.Component;
import com.academy.lesson06.ex.Button;
import com.academy.lesson06.ex.Label;
import com.academy.lesson06.ex.CheckBox;
import com.academy.lesson06.ex.RoundButton;


public class PolymorfismDz1 {
    public static void main(String[] args) {

         // Создание и наполнение массива типа Component (10 элементов)
        Component[] components = new Component[]
                {new Button(), new Label(), new CheckBox(), new RoundButton(), new Label(), new RoundButton(),
                 new Button(), new RoundButton(), new Button(), new CheckBox()};


        System.out.println("*** Polymorfism DZ1 *****");
        System.out.println("*** вывести все элемены Component на экран *****");
        for (int i = 0; i < components.length; i++)
            components[i].draw();


        // d) В цикле перебрать массив Component и вывести только Button и его потомков

        System.out.println("*** вывести только Button и его потомков *****");
        for (int i = 0; i < components.length; i++)
            if (components[i] instanceof Button)
                components[i].draw();

    }

}
