package GUI;

import GUI.buttons.TossButton;
import GUI.panels.Background;
import GUI.panels.ResultBarField;
import GUI.panels.ResultPanel;
import GUI.panels.TossButtonPanel;
import GUI.sprites.CoinSprite;
import GUI.sprites.spriteProperties.KindOfStateEnum;
import logic.CoinTosser;
import logic.Logger;
import logic.observerPattern.EventKind;
import logic.observerPattern.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ApplicationMainFrame extends JFrame implements IObserver {
    private static final String APPLICATION_NAME = "Toss a coin app";
    private static final String HEADS_LABEL_NAME = "Heads count =";
    private static final String TAILS_LABEL_NAME = "Tails count =";
    private static final double FONT_SIZE_TO_SCREEN_HEIGHT_PROPORTION = 0.025;

    private Background background;
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

    private void addEndProgramShortcutListener() {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }

    private void initializeComponents() {
        background = new Background();
        coinTosser = new CoinTosser();
        coinSprite = new CoinSprite(background.getScreenWidth(), background.getScreenHeight()); //todo: wyrzucic pozycje ??
        int textSize = (int) (background.getScreenHeight() * FONT_SIZE_TO_SCREEN_HEIGHT_PROPORTION);
        int screenWidth = background.getScreenWidth();
        int screenHeight = background.getScreenHeight();
        headsField = new ResultBarField(HEADS_LABEL_NAME, textSize, screenWidth, screenHeight);
        tailsField = new ResultBarField(TAILS_LABEL_NAME, textSize, screenWidth, screenHeight);
    }

    private void setDefaultFrameProperty() {
        setFullScreen();
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
        addCoinSprite();
    }

    private void addTossButtonPanel() {
        TossButtonPanel tossButtonPanel = new TossButtonPanel(background.getScreenWidth(), background.getScreenHeight());
        background.add(tossButtonPanel, BorderLayout.SOUTH);
        TossButton tossButton = new TossButton(background.getScreenWidth(),background.getScreenHeight());
        tossButton.attachObserver(coinSprite);
        coinSprite.attachObserver(coinTosser);
        tossButtonPanel.add(tossButton);
    }

    private void addResultPanel() {
        ResultPanel resultPanel = new ResultPanel();
        resultPanel.getNestedPanel().add(headsField, BorderLayout.WEST);
        resultPanel.getNestedPanel().add(tailsField, BorderLayout.WEST);
        background.add(resultPanel, BorderLayout.EAST);
    }

    private void addCoinSprite() {
        background.add(coinSprite, BorderLayout.CENTER);
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
            default:
                Logger.logError(getClass(), "Unknown event");
        }
    }

}
