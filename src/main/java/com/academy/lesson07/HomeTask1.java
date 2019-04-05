/*
1) Добавить в иерархию классов Component новый интерфейс VisualComponent
	унаследовать класс Component от VisualComponent
	(Component implements VisualComponent)

	Интерфейс VisualComponent должен иметь один метод:
		- void draw();

		-продемонстрировать работу метода draw() у всех компонентов
 */
package com.academy.lesson07;

import com.academy.lesson07.model.Button;
import com.academy.lesson07.model.Component;
import com.academy.lesson07.model.Label;
import com.academy.lesson07.model.VisualComponent;

public class HomeTask1 {
    public  static void main (String[] args){
        // Массив компонент
        VisualComponent[] vComponents = new VisualComponent[]{
                new Button(),
                new Label(),
                new Component(),
                () -> System.out.println("Lambda component") //реализовали интерфейс на лету вставили лямбду
        };
        System.out.println("Демонстрируем работу метода draw() для каждого component");

for (VisualComponent element: vComponents)
    element.draw(); // демонстрируем работу метода draw() у всех компонентов
    }

}
