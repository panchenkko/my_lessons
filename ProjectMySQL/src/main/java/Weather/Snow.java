package Weather;

public class Snow extends Weather {
    private int id;
    private int size;
    private int speed;

    public Snow() {
    }

    public Snow(int id, int temperature, int atmospherePressure, int size, int speed) {
        super(temperature, atmospherePressure);
        this.id = id;
        this.size = size;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "\033[30m" +
                this.id + ". " +
                "Температура: " + getTemperature() + "; " +
                "Атмосферное давление: " + getAtmospherePressure() + "; " +
                "Размер: " + this.size + " мм; " +
                "Скорость: " + this.speed + " м/сек" +
                "\033[0m";
    }
}
