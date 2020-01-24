package UI;

import logic.CoinTosser;
import logic.Logger;
import logic.observerPattern.EventKind;
import logic.observerPattern.IObserver;

import javax.swing.*;
import java.awt.*;

public class ApplicationMain extends JFrame implements IObserver {
    private static final String APPLICATION_NAME = "Toss a coin app";
    private static final String HEADS_LABEL_NAME = "Heads count =";
    private static final String TAILS_LABEL_NAME = "Tails count =";
    private static final double FONT_SIZE_TO_SCREEN_HEIGHT_PROPORTION = 0.025;

    private Background background;
    private ResultBarField headsField;
    private ResultBarField tailsField;
    private CoinTosser coinTosser;

    public ApplicationMain() {
        super(APPLICATION_NAME);
        setDefaultFrameProperty();
        initializeComponents();
        addComponentsToPanels();
        coinTosser.attachObserver(this);
    }

    private void initializeComponents() {
        background = new Background();
        coinTosser = new CoinTosser();
        int textSize = (int) (background.getScreenHeight() * FONT_SIZE_TO_SCREEN_HEIGHT_PROPORTION);
        int screenWidth = background.getScreenWidth();
        int screenHeight = background.getScreenHeight();
        headsField = new ResultBarField(HEADS_LABEL_NAME, textSize, screenWidth, screenHeight);
        tailsField = new ResultBarField(TAILS_LABEL_NAME, textSize, screenWidth, screenHeight);
    }

    private void setDefaultFrameProperty() {
//        setFullScreen();
        setSize(1600, 900); //todo: tmp
        setVisible(true);
    }

    private void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    private void addComponentsToPanels() {
        add(background);
        addTossButtonPanel();
        addResultPanel();
    }

    private void addTossButtonPanel() {
        TossButtonPanel tossButtonPanel = new TossButtonPanel(background.getScreenWidth(), background.getScreenHeight());
        background.add(tossButtonPanel, BorderLayout.SOUTH);
        TossButton tossButton = new TossButton();
        tossButton.attachObserver(coinTosser);
        tossButtonPanel.add(tossButton);
    }

    private void addResultPanel() {
        ResultPanel resultPanel = new ResultPanel();
        resultPanel.getNestedPanel().add(headsField, BorderLayout.WEST);
        resultPanel.getNestedPanel().add(tailsField, BorderLayout.WEST);
        background.add(resultPanel, BorderLayout.EAST);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case COMES_UP_HEADS:
                headsField.setActualCount(coinTosser.getHeadsCount());
                break;
            case COMES_UP_TAILS:
                tailsField.setActualCount(coinTosser.getTailsCount());
                break;
            default:
                Logger.logError(getClass(), "Unknown event");
        }
    }
}
