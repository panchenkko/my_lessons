public class Rain extends Weather {

    private int speed;
    private String powerLevel;

    public Rain(int temperature, int atmospherePressure, int speed, String powerLevel) {
        super(temperature, atmospherePressure);
        this.speed = speed;
        this.powerLevel = powerLevel;
    }

    @Override
    public String toString() {
        return "Температура: " + getTemperature() + "; Атмосферное давление: " + getAtmospherePressure() +
                "; Уровень: " + this.powerLevel + "; Скорость: " + this.speed + " м/сек; ";
    }
}
