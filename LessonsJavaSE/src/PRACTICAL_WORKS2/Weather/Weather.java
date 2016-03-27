package PRACTICAL_WORKS2.Weather;

public class Weather {

    private int temperature;
    private int atmospherePressure; // Атмосферное давление

    public Weather(int temperature, int atmospherePressure) {
        this.temperature = temperature;
        this.atmospherePressure = atmospherePressure;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getAtmospherePressure() {
        return atmospherePressure;
    }
}
