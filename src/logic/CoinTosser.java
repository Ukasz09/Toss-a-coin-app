package logic;

import logic.observerPattern.EventKind;
import logic.observerPattern.IObservable;
import logic.observerPattern.IObserver;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CoinTosser implements IObserver, IObservable {
    private Set<IObserver> observers;
    private Random randomGenerator;
    private int tailsCount = 0;
    private int headsCount = 0;

    public CoinTosser() {
        observers = new HashSet<>();
        randomGenerator = new Random();
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        if (eventKind == EventKind.COIN_STOP_ROTATING)
            tossACoin();
    }

    private void tossACoin() {
        if (isHeadSide()) {
            headsCount++;
            notifyObservers(EventKind.COMES_UP_HEADS);
        } else {
            tailsCount++;
            notifyObservers(EventKind.COMES_UP_TAILS);
        }
    }

    private boolean isHeadSide() {
        return randomGenerator.nextBoolean();
    }

    @Override
    public void attachObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(EventKind eventKind) {
        for (IObserver observer : observers)
            observer.updateObserver(eventKind);
    }

    public int getTailsCount() {
        return tailsCount;
    }

    public int getHeadsCount() {
        return headsCount;
    }
}
