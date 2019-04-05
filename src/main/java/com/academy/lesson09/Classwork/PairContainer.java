package com.academy.lesson09.Classwork;

import java.util.Arrays;

public class PairContainer<T,V> {
    @Override
    public String toString() {
        if (firstValue.getClass().isArray() && secondValue.getClass().isArray())
        return Arrays.toString((Object[])firstValue)+"=>"+Arrays.toString((Object[])secondValue);

        return firstValue + "=>" + secondValue;
    }

    private T firstValue;
    private V secondValue;

    public T getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(T firstValue) {
        this.firstValue = firstValue;
    }

    public V getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(V secondValue) {
        this.secondValue = secondValue;
    }



}
