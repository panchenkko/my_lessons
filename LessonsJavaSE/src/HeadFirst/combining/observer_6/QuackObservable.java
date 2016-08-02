package HeadFirst.Combining.observer_6;

public interface QuackObservable {
	public void registerObserver(Observer observer);
	public void notifyObservers();
}
