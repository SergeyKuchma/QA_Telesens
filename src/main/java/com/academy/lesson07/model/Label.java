package com.academy.lesson07.model;

public class Label extends Component {
    public void draw() {
        System.out.println("Label");
    }

    @Override
    public void draw3D() {//переопределяем метод draw3D у Label для ДЗ 2.
        System.out.println("Результат ПЕРЕОПРЕДЕЛЕННОГО метода draw3D() для класса Label");
    }

}
