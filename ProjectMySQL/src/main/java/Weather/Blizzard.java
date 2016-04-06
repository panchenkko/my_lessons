package Weather;

public class Blizzard extends Snow {

    private String powerLevel;

    public Blizzard() {
    }

    public Blizzard(int temperature, int atmospherePressure, int size, int speed, String powerLevel) {
        super(temperature, atmospherePressure, size, speed);
        this.powerLevel = powerLevel;
    }

    public void setPowerLevel(String powerLevel) {
        this.powerLevel = powerLevel;
    }

    @Override
    public String toString() {
        return "\033[30mТемпература: " + getTemperature() + "; Атмосферное давление: " + getAtmospherePressure() +
                "; Размер: " + getSize() + " мм; Уровень: " + this.powerLevel + "; Скорость: " + getSpeed() + " м/сек\033[0m";
    }
}
