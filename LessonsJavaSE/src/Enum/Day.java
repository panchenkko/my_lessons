package Enum;

public enum Day {

    SUNDAY(1, "Today is SUNDAY"),
    MONDAY(2, "Today is MONDAY"),
    TUESDAY(3, "Today is TUESDAY"),
    WEDNESDAY(4, "Today is WEDNESDAY"),
    THURSDAY(5, "Today is THURSDAY"),
    FRIDAY(6, "Today is FRIDAY"),
    SATURDAY(7, "Today is SATURDAY");

    private int value;
    private String today;

    Day(int value, String today) {
        this.value = value;
        this.today = today;
    }

    public int getValue() {
        return value;
    }

    public String getToday() {
        return today;
    }

    @Override
    public String toString() {
        switch (this) {
            case SUNDAY:
                return "My number: " + SUNDAY.getValue() + ", and " + SUNDAY.getToday();
            case MONDAY:
                return "My number: " + MONDAY.getValue() + ", and " + MONDAY.getToday();
            case TUESDAY:
                return "My number: " + TUESDAY.getValue() + ", and " + TUESDAY.getToday();
            case WEDNESDAY:
                return "My number: " + WEDNESDAY.getValue() + ", and " + WEDNESDAY.getToday();
            case THURSDAY:
                return "My number: " + THURSDAY.getValue() + ", and " + THURSDAY.getToday();
            case FRIDAY:
                return "My number: " + FRIDAY.getValue() + ", and " + FRIDAY.getToday();
            case SATURDAY:
                return "My number: " + SATURDAY.getValue() + ", and " + SATURDAY.getToday();
            default:
                return "Today is no day";
        }
    }
}
