package com.academy.lesson06.ex;

import java.util.Objects;

public class Component {
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private String text;

    public Component(int xPosition, int yPosition, int width, int height, String text) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public Component() {
    }

    public void draw(){
        System.out.println("Component");
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition, width, height, text);
    }
}
