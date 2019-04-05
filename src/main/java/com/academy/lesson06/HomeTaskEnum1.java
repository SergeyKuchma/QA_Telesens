package com.academy.lesson06;

public class HomeTaskEnum1 {

    public static void main(String[] args) {
        //System.out.println(DayOfWeek.);
        DayOfWeek.printAll();

// -------------------- следующие день -----------------

        DayOfWeek d = DayOfWeek. MONDAY;
        for (int i= 0; i< 7; i++) {
            d = d.next();
            System.out.println(d + " " + d.isWeekend());
        }


    }
}
