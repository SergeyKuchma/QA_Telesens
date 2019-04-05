package com.academy.lesson07.model;

public interface VisualComponent {
    void draw();

    default void draw3D() {
        System.out.println("Результат метода draw3D() из VisualComponent");
    }

}
