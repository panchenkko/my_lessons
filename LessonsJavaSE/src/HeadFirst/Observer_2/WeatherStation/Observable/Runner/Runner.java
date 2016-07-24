package HeadFirst.Observer_2.WeatherStation.Observable.Runner;

import HeadFirst.Observer_2.WeatherStation.Observable.Observers.ForecastDisplay;
import HeadFirst.Observer_2.WeatherStation.Observable.Observers.CurrentConditionsDisplay;
import HeadFirst.Observer_2.WeatherStation.Observable.Observers.HeatIndexDisplay;
import HeadFirst.Observer_2.WeatherStation.Observable.Observers.StatisticsDisplay;
import HeadFirst.Observer_2.WeatherStation.Observable.Subject.WeatherData;

public class Runner {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
