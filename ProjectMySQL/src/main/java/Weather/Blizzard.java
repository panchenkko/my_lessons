package Weather;

public class Blizzard extends Snow {
    private int id;
    private String powerLevel;

    public Blizzard() {
    }

    public Blizzard(int idSnow, int temperature, int atmospherePressure, int size, int speed,
                    int idBlizzard, String powerLevel) {
        super(idSnow, temperature, atmospherePressure, size, speed);
        this.id = idBlizzard;
        this.powerLevel = powerLevel;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
                "Размер: " + getSize() + " мм; " +
                "Уровень: " + this.powerLevel + "; " +
                "Скорость: " + getSpeed() + " м/сек" +
                "\033[0m";
    }
}
