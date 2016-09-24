package Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Observers.interfaces;

public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
