package com.academy.lesson07;
import com.academy.lesson07.model.Component;
import com.academy.lesson07.model.Button;
import com.academy.lesson07.model.Label;

public class ComponentDemo {
    public static void main (String[] args) {
        Component btn = new Button();// это уже полиморфизм
        btn.draw();

//        Component[] components = new Component[] {
        Object[] components = new Object[]{

                new Component(),
                new Label(),
                new Button(),
                12,
                10
        };
        //*************************

        for (int i = 0; i < components.length; i++) {
            //      components[i].draw();
            if (components[i] instanceof Component) {
                Component element = (Component) components[i];
                element.draw();
                element.draw3D();
            }
        }
    }
}
