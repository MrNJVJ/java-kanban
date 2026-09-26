package coach;

public class CounterOfTrainings  implements Comparable<CounterOfTrainings> {
    // Тренер
    private Coach coach;
    // Количество занятий у тренера за неделю
    private int countOfTrainings;

    public CounterOfTrainings(Coach coach, int countOfTrainings) {
        this.coach = coach;
        this.countOfTrainings = countOfTrainings;
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
        return countOfTrainings - o.countOfTrainings;
    }

    @Override
    public String toString() {
        return "coach.CounterOfTrainings{" +
                "coach=" + coach +
                ", countOfTrainings=" + countOfTrainings +
                '}';
    }
}
