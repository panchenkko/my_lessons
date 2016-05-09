package Weather;

public class Rain extends Weather {
    private int id;
    private int speed;
    private String powerLevel;

    public Rain() {
    }

    public Rain(int id, int temperature, int atmospherePressure, int speed, String powerLevel) {
        super(temperature, atmospherePressure);
        this.id = id;
        this.speed = speed;
        this.powerLevel = powerLevel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPowerLevel(String powerLevel) {
        this.powerLevel = powerLevel;
    }

    @Override
    public String toString() {
        return "\033[30m" +
                this.id + ". " +
                "Температура: " + getTemperature() + "; " +
                "Атмосферное давление: " + getAtmospherePressure() + "; " +
                "Уровень: " + this.powerLevel + "; " +
                "Скорость: " + this.speed + " м/сек; " +
                "\033[0m";
    }
}
