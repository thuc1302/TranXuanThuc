package Bai1_8;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time nextSecond() {
        int newHour = hour;
        int newMinute = minute;
        int newSecond = second + 1;

        if (newSecond == 60) {
            newSecond = 0;
            newMinute++;
            if (newMinute == 60) {
                newMinute = 0;
                newHour++;
                if (newHour == 24) {
                    newHour = 0;
                }
            }
        }

        return new Time(newHour, newMinute, newSecond);
    }

    public Time previousSecond() {
        int newHour = hour;
        int newMinute = minute;
        int newSecond = second - 1;

        if (newSecond == -1) {
            newSecond = 59;
            newMinute--;
            if (newMinute == -1) {
                newMinute = 59;
                newHour--;
                if (newHour == -1) {
                    newHour = 23;
                }
            }
        }

        return new Time(newHour, newMinute, newSecond);
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}

