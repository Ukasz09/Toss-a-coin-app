package logic.observerPattern;

public interface IObservable {
    void attachObserver(IObserver observer);
    void detachObserver(IObserver observer);
    void notifyObservers(EventKind eventKind);
}