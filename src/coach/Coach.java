package coach;

import java.util.Objects;

public class Coach {

    //фамилия
    private String surname;
    //имя
    private String name;
    //отчество
    private String middleName;

    public Coach(String surname, String name, String middleName) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Coach coach = (Coach) o;
        return Objects.equals(surname, coach.surname) && Objects.equals(name, coach.name) && Objects.equals(middleName, coach.middleName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(surname);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(middleName);
        return result;
    }

    @Override
    public String toString() {
        return "coach.Coach{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}