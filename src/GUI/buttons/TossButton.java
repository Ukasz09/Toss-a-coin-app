package GUI.buttons;

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
    private static final String BUTTON_ICON_PATH = "tossTheCoinButton.png";
    private static final String BUTTON_PRESSED_ICON_PATH = "tossTheCoinButtonPressed.png";

    private Set<IObserver> observers;
    private int buttonWidth;
    private int buttonHeight;

    public TossButton(int screenWidth, int screenHeight) {
        observers = new HashSet<>();
        setDefaultProperty(screenWidth, screenHeight);
        setIcon(getNewButtonNormalIcon());
        setPressedIcon(getNewButtonPressedIcon());
        addActionListener();
    }

    private void setDefaultProperty(int screenWidth, int screenHeight) {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        buttonWidth = (int) (screenWidth * BUTTON_WIDTH_TO_SCREEN_PROPORTION);
        buttonHeight = (int) (screenHeight * BUTTON_HEIGHT_TO_SCREEN_PROPORTION);
    }

    private Icon getNewButtonNormalIcon() {
        Image image = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(BUTTON_ICON_PATH)).getImage();
        Image resizedImage = image.getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private Icon getNewButtonPressedIcon() {
        Image image = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(BUTTON_PRESSED_ICON_PATH)).getImage();
        Image resizedImage = image.getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
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
        if (eventKind == EventKind.COIN_READY_TO_TOSS)
            setVisible(true);
    }
}
