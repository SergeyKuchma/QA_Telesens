package com.academy.lesson12;
import com.academy.lesson11.Subscriber;
import java.util.Comparator;

public class ComparatorComplexImpl implements Comparator<Subscriber> {
    @Override
    public int compare(Subscriber o1, Subscriber o2) {
        if (o1.getOperator().getName().equals(o2.getOperator().getName())){
            return Integer.compare(o2.getAge(), o1.getAge());
        } else {
            return o1.getOperator().getName().compareTo(o2.getOperator().getName());
        }
    }
}