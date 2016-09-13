package Enum;

public class Runner {
    public static void main(String[] args) {
        for (Day day : Day.values()) {
            System.out.println(day);
        }

        System.out.println();

        for (Day day : Day.values()) {
            System.out.println(day.getToday());
        }
    }
}
