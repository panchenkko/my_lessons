package HeadFirst.Observer.WeatherStation;

public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
