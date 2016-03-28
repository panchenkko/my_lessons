public class Blizzard extends Snow {

    private String powerLevel;

    public Blizzard(int temperature, int atmospherePressure, int size, int speed, String powerLevel) {
        super(temperature, atmospherePressure, size, speed);
        this.powerLevel = powerLevel;
    }

    @Override
    public String toString() {
        return "Температура: " + getTemperature() + "; Атмосферное давление: " + getAtmospherePressure() +
                "; Размер: " + getSize() + " мм; Уровень: " + this.powerLevel + "; Скорость: " + getSpeed() + " м/сек";
    }
}
