package com.academy.lesson09.Classwork;

public class Container<T> {
    private T value;

    public T getValue(){
        return value;
    }

    public void setValue(T value){
    this.value=value;
    }

    @Override
    public String toString() {
        return "Container{" +
                "value=" + value +
                '}';
    }
}
