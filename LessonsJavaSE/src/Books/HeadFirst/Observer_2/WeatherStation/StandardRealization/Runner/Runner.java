package Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Runner;

import Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Observers.CurrentConditionsDisplay;
import Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Observers.ForecastDisplay;
import Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Observers.HeatIndexDisplay;
import Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Observers.StatisticsDisplay;
import Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Subject.WeatherData;

public class Runner {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
