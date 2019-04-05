package com.academy.lesson09.Classwork;

import java.util.Iterator;

public class Group implements Iterable<Integer>{
    private int val1 = 1;
    private int val2 = 234;
    private int val3 = 5;

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Integer> {
        int index = 0;
        @Override
        public boolean hasNext() {
            return index < 3;
        }

        @Override
        public Integer next() {
            index++;
            switch (index) {
                case 1:
                    return val1;
                case 2:
                    return val2;

                case 3:
                    return  val3;
            }

            throw new UnsupportedOperationException();
        }
    }
}