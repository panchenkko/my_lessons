package com.company.PRACTICAL_WORKS2.Weather;

public class Snow extends Weather {

    private int size;
    private int speed;

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

    @Override
    public String toString() {
        return "Температура: " + getTemperature() + "; Атмосферное давление: " + getAtmospherePressure() +
               "; Размер: " + this.size + " мм; Скорость: " + this.speed + " м/сек";
    }
}
