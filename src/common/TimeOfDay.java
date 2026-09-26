package common;

public class TimeOfDay implements Comparable<TimeOfDay> {

    //часы (от 0 до 23)
    private int hours;
    //минуты (от 0 до 59)
    private int minutes;

    public TimeOfDay(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TimeOfDay timeOfDay = (TimeOfDay) o;
        return hours == timeOfDay.hours && minutes == timeOfDay.minutes;
    }

    @Override
    public int hashCode() {
        int result = hours;
        result = 31 * result + minutes;
        return result;
    }

    @Override
    public int compareTo(TimeOfDay o) {
        if ((hours - o.hours) == 0) {
            return minutes - o.minutes;
        }
        return hours - o.hours;
    }

    @Override
    public String toString() {
        return "common.TimeOfDay{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                '}';
    }
}