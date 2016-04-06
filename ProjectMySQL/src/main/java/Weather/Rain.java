package Weather;

public class Rain extends Weather {

    private int speed;
    private String powerLevel;

    public Rain() {
    }

    public Rain(int temperature, int atmospherePressure, int speed, String powerLevel) {
        super(temperature, atmospherePressure);
        this.speed = speed;
        this.powerLevel = powerLevel;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPowerLevel(String powerLevel) {
        this.powerLevel = powerLevel;
    }

    @Override
    public String toString() {
        return "\033[30mТемпература: " + getTemperature() + "; Атмосферное давление: " + getAtmospherePressure() +
                "; Уровень: " + this.powerLevel + "; Скорость: " + this.speed + " м/сек; \033[0m";
    }
}
