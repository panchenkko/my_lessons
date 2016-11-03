package Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Subject;

import Books.HeadFirst.Observer_2.WeatherStation.StandardRealization.Observers.interfaces.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
