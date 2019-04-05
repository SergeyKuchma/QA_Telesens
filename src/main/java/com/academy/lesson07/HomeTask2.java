/*
2) Добавить новый метод по умолчанию в VisualComponent
		- default void draw3D(); //

	унаследовать класс Component от VisualComponent
		- перекрыть метод draw3D у Label
		- продемонстрировать работу метода draw3D() во всех компонентах

 */
package com.academy.lesson07;

import com.academy.lesson07.model.Button;
import com.academy.lesson07.model.Component;
import com.academy.lesson07.model.Label;
import com.academy.lesson07.model.VisualComponent;

public class HomeTask2 {
    public static void main(String[] args) {
        // Массив компонент
        VisualComponent[] vComponents = new VisualComponent[]{
                new Button(),
                new Label(),
                new Component(),
                () -> System.out.println("Lambda component") //реализовали интерфейс на лету вставили лямбду
        };
        System.out.println("Демонстрируем работу метода draw3D() для каждого компонента:");

        for (VisualComponent element : vComponents) {
            System.out.print("Для компонента = ");
            element.draw(); // демонстрируем работу метода draw() у всех компонентов
            element.draw3D(); // демонстрируем работу метода draw3D() у всех компонентов
        }
    }
}