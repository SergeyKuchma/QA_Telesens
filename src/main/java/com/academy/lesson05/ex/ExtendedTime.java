package com.academy.lesson05.ex;

public class ExtendedTime extends DateTime {
    private int millisecond;

    public ExtendedTime() {
            }

    public ExtendedTime(int day, int month, int year, int hour, int minute, int second, int millisecond) {
        super(day, month, year, hour, minute, second);
        this.millisecond = millisecond;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    @Override
    public String toString() {
        String dateTimeFormat = super.toString();
        String millisecondFormat = String.format("%03d", millisecond);
        return dateTimeFormat +"."+ millisecondFormat;
//        return super.toString() + "." + String.format("%03d", millisecond);
    }
    public ExtendedTime withMillisecond(int millisecond) {
        this.millisecond = millisecond;
        return this;
    }
}
