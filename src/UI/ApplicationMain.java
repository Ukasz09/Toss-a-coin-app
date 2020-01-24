package UI;

import logic.CoinTosser;

import javax.swing.*;
import java.awt.*;

public class ApplicationMain extends JFrame {
    private static final String APPLICATION_NAME = "Toss a coin app";

    private Background background;
    private TossButtonPanel tossButtonPanel;
    private ResultPanel resultPanel;
    private CoinTosser coinTosser;

    public ApplicationMain() {
        super(APPLICATION_NAME);
        setDefaultFrameProperty();
        addComponentsToPanels();

        coinTosser = new CoinTosser();
    }

    private void setDefaultFrameProperty() {
        background = new Background();
        add(background);

        tossButtonPanel = new TossButtonPanel(background);
        background.add(tossButtonPanel, BorderLayout.SOUTH);

        resultPanel=new ResultPanel();
        background.add(resultPanel,BorderLayout.EAST);

        setFullScreen();
        setVisible(true);
    }

    private void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    private void addComponentsToPanels() {
        tossButtonPanel.add(new TossButton());

        resultPanel.nested.add(new ResultBarField("Heads:", background.getScreenHeight() / 25), BorderLayout.WEST); //todo: tmp
        resultPanel.nested.add(new ResultBarField("Tails:", background.getScreenHeight() / 25), BorderLayout.WEST); //todo: tmp
    }

    private void tossACoin() {
        coinTosser.tossACoin();
    }


}
