package GUI;

import GUI.buttons.TossButton;
import GUI.panels.Background;
import GUI.panels.ResultBarField;
import GUI.panels.ResultPanel;
import GUI.panels.TossButtonPanel;
import GUI.sprites.CoinSprite;
import GUI.sprites.spriteProperties.KindOfStateEnum;
import logic.CoinTosser;
import logic.observerPattern.EventKind;
import logic.observerPattern.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ApplicationMainFrame extends JFrame implements IObserver {
    private static final String APPLICATION_NAME = "Toss a coin app";
    private static final double FONT_SIZE_TO_SCREEN_HEIGHT_PROPORTION = 0.025;
    private static final int EXIT_SHORTCUT = KeyEvent.VK_ESCAPE;

    private Background background;
    private int screenWidth;
    private int screenHeight;
    private ResultBarField headsField;
    private ResultBarField tailsField;
    private CoinTosser coinTosser;
    private CoinSprite coinSprite;

    public ApplicationMainFrame() {
        super(APPLICATION_NAME);
        setDefaultFrameProperty();
        initializeComponents();
        addComponentsToPanels();
        coinTosser.attachObserver(this);
        addEndProgramShortcutListener();
    }

    private void setDefaultFrameProperty() {
        setFullScreen();
        setVisible(true);
        background = new Background();
        screenWidth = background.getScreenWidth();
        screenHeight = background.getScreenHeight();
    }

    private void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    private void initializeComponents() {
        coinTosser = new CoinTosser();
        coinSprite = new CoinSprite(screenWidth, screenHeight);
        int textSize = (int) (screenHeight * FONT_SIZE_TO_SCREEN_HEIGHT_PROPORTION);
        headsField = new ResultBarField(false, textSize, screenWidth, screenHeight);
        tailsField = new ResultBarField(true, textSize, screenWidth, screenHeight);
    }

    private void addComponentsToPanels() {
        add(background);
        addTossButtonPanel();
        addResultPanel();
        addCoinSprite();
    }

    private void addTossButtonPanel() {
        TossButtonPanel tossButtonPanel = new TossButtonPanel(screenWidth, screenHeight);
        background.add(tossButtonPanel, BorderLayout.SOUTH);
        TossButton tossButton = new TossButton(screenWidth, screenHeight);
        tossButton.attachObserver(coinSprite);
        coinSprite.attachObserver(tossButton);
        tossButtonPanel.add(tossButton);
    }

    private void addResultPanel() {
        ResultPanel resultPanel = new ResultPanel();
        resultPanel.add(headsField);
        resultPanel.add(tailsField);
        background.add(resultPanel, BorderLayout.NORTH);
    }

    private void addCoinSprite() {
        background.add(coinSprite, BorderLayout.CENTER);
        coinSprite.attachObserver(coinTosser);
    }

    private void addEndProgramShortcutListener() {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(EXIT_SHORTCUT, 0, false);
        Action exitKeyAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke,EXIT_SHORTCUT);
        getRootPane().getActionMap().put(EXIT_SHORTCUT, exitKeyAction);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        switch (eventKind) {
            case COMES_UP_HEADS: {
                coinSprite.changeState(KindOfStateEnum.COIN_HEADS);
                headsField.setActualCount(coinTosser.getHeadsCount());

            }
            break;
            case COMES_UP_TAILS: {
                coinSprite.changeState(KindOfStateEnum.COIN_TAILS);
                tailsField.setActualCount(coinTosser.getTailsCount());
            }
            break;
        }
    }

}
