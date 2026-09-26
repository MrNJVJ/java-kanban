import coach.Coach;
import common.Age;
import common.DayOfWeek;
import common.TimeOfDay;
import group.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import training.Timetable;
import training.TrainingSession;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.SortedSet;

public class TimetableTest {
    @Test
    @DisplayName("Одна тренировка в день")
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        Assertions.assertEquals(timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size(), 1);
        Assertions.assertEquals(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size(), 0);
    }

    @Test
    @DisplayName("Несколько тренировок в один день")
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        Assertions.assertEquals(timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size(), 1);
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        ArrayList<TimeOfDay> keys = new ArrayList<>();
        keys.add(new TimeOfDay(13, 0));
        keys.add(new TimeOfDay(20, 0));
        Assertions.assertEquals(
                new ArrayList<>(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).keySet()),
                keys
                );
        Assertions.assertEquals(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size(), 0);
    }

    @Test
    @DisplayName("Одна тренировка в конкретное время")
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        Assertions.assertEquals(
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13,0)).size(),
                1);
        Assertions.assertEquals(
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14,0)).size(),
                0);
    }
}
