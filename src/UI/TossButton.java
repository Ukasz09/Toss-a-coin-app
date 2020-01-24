package UI;

import logic.observerPattern.EventKind;
import logic.observerPattern.IObservable;
import logic.observerPattern.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class TossButton extends JButton implements IObservable {
    private Set<IObserver> observers;
    private Icon buttonImage;
    private static final String BUTTON_ICON_PATH = "src/resources/tossTheCoinButton.png";

    public TossButton() {
        observers = new HashSet<>();
        setButtonImage();
        setIcon(buttonImage);
        addActionListener();
    }

    private void setButtonImage() {
        Image image = new ImageIcon(BUTTON_ICON_PATH).getImage();
        buttonImage = new ImageIcon(image);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    private void addActionListener() {
        this.addActionListener((ActionEvent event) -> notifyObservers(EventKind.TOSS_COIN_BUTTON_CLICKED));
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
}
