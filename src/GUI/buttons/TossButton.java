package GUI.buttons;

import logic.Logger;
import logic.observerPattern.EventKind;
import logic.observerPattern.IObservable;
import logic.observerPattern.IObserver;
import resources.ResourceTemplateClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class TossButton extends JButton implements IObservable, IObserver {
    private static final double BUTTON_WIDTH_TO_SCREEN_PROPORTION = 0.15;
    private static final double BUTTON_HEIGHT_TO_SCREEN_PROPORTION = 0.1;

    private Set<IObserver> observers;
    private static final String BUTTON_ICON_PATH = "tossTheCoinButton.png";
    private static final String BUTTON_PRESSED_ICON_PATH = "tossTheCoinButtonPressed.png";

    public TossButton(int screenWidth, int screenHeight) {
        observers = new HashSet<>();
        setDefaultProperty();
        setIcon(getNewButtonIcon(screenWidth, screenHeight));
        setPressedIcon(getNewButtonPressedIcon(screenWidth, screenHeight));
        addActionListener();
    }

    private void setDefaultProperty() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    private Icon getNewButtonIcon(int screenWidth, int screenHeight) {
        Image image = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(BUTTON_ICON_PATH)).getImage();
        Image resizedImage = image.getScaledInstance( (int)(screenWidth*BUTTON_WIDTH_TO_SCREEN_PROPORTION), (int)(screenHeight*BUTTON_HEIGHT_TO_SCREEN_PROPORTION),  java.awt.Image.SCALE_SMOOTH ) ;
        return new ImageIcon(resizedImage);
    }

    private Icon getNewButtonPressedIcon(int screenWidth, int screenHeight) {
        Image image = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(BUTTON_PRESSED_ICON_PATH)).getImage();
        Image resizedImage = image.getScaledInstance( (int)(screenWidth*BUTTON_WIDTH_TO_SCREEN_PROPORTION), (int)(screenHeight*BUTTON_HEIGHT_TO_SCREEN_PROPORTION),  java.awt.Image.SCALE_SMOOTH ) ;
        return new ImageIcon(resizedImage);
    }

    private void addActionListener() {
        this.addActionListener((ActionEvent event) -> {
            notifyObservers(EventKind.TOSS_COIN_BUTTON_CLICKED);
            setVisible(false);
        });
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

    @Override
    public void updateObserver(EventKind eventKind) {
        if(eventKind==EventKind.COIN_READY_TO_TOSS)
            setVisible(true);
    }
}
