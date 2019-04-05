package com.academy.lesson12;

import com.academy.lesson11.Subscriber;

import java.util.Comparator;

public class ComparatorByFirstNameImpl implements Comparator<Subscriber> {

    @Override
    public int compare(Subscriber o1, Subscriber o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}