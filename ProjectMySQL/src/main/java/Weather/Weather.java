package Weather;

public abstract class Weather {
    private int temperature;
    private int atmospherePressure; // Атмосферное давление

    public Weather() {
    }

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

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setAtmospherePressure(int atmospherePressure) {
        this.atmospherePressure = atmospherePressure;
    }
}
