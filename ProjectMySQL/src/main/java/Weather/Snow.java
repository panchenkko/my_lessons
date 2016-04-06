package Weather;

public class Snow extends Weather {

    private int size;
    private int speed;

    public Snow() {
    }

    public Snow(int temperature, int atmospherePressure, int size, int speed) {
        super(temperature, atmospherePressure);
        this.size = size;
        this.speed = speed;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "\033[30mТемпература: " + getTemperature() + "; Атмосферное давление: " + getAtmospherePressure() +
               "; Размер: " + this.size + " мм; Скорость: " + this.speed + " м/сек\033[0m";
    }
}
