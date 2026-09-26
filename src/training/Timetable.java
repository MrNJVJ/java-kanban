package training;

import coach.Coach;
import coach.CounterOfTrainings;
import common.DayOfWeek;
import common.TimeOfDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class Timetable {

    // Расписание тренировок
    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    @Override
    public String toString() {
        String out = "training.Timetable{" + "timetable=\n";
        for (DayOfWeek dayOfWeek : timetable.keySet()) {
            out += "  " + dayOfWeek + ":\n";
            for (TimeOfDay timeOfDay : timetable.get(dayOfWeek).keySet()) {
                out += "    " + timeOfDay + ":\n";
                for (TrainingSession trainingSession : timetable.get(dayOfWeek).get(timeOfDay)) {
                    out += "      " + trainingSession + ";\n";
                }
            }
        }
        return out + '}';
    }

    public void addNewTrainingSession(TrainingSession trainingSession) {
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTrainings = getTrainingSessionsForDay(trainingSession.getDayOfWeek());
        ArrayList<TrainingSession> timeSessions = dayTrainings.get(trainingSession.getTimeOfDay());
        if (timeSessions == null) {
            // Создаём запись для этого времени
            timeSessions = new ArrayList<>();
            dayTrainings.put(trainingSession.getTimeOfDay(), timeSessions);
        }

        timeSessions.add(trainingSession);
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (!timetable.containsKey(dayOfWeek)) {
            // сли нет - создадим пустую мапу для данного дня недели
            timetable.put(dayOfWeek, new TreeMap<>());
        }
        return timetable.get(dayOfWeek);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTrainings = getTrainingSessionsForDay(dayOfWeek);
        if (!dayTrainings.containsKey(timeOfDay)) {
            // сли нет - возвращаем пустой лист
            return new ArrayList<>();
        }
        return dayTrainings.get(timeOfDay);
    }

    public ArrayList<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> coachesCounters = new HashMap<>();

        for (DayOfWeek dayOfWeek : timetable.keySet()) {
            for (TimeOfDay timeOfDay : timetable.get(dayOfWeek).keySet()) {
                for (TrainingSession trainingSession : timetable.get(dayOfWeek).get(timeOfDay)) {
                    coachesCounters.put(trainingSession.getCoach(), coachesCounters.getOrDefault(trainingSession.getCoach(), 0) + 1);
                }
            }
        }
        // Перекидываем в лист с реализованным сравнением
        ArrayList<CounterOfTrainings> countersOfTrainings = new ArrayList<>();
        for (HashMap.Entry<Coach, Integer> entry : coachesCounters.entrySet()) {
            countersOfTrainings.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }
        countersOfTrainings.sort(Comparator.reverseOrder());
        return countersOfTrainings;
    }

}