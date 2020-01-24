package UI;

import javax.swing.*;
import java.awt.*;

public class TossButton extends JButton {
    private Icon buttonImage;
    private static final String BUTTON_ICON_PATH = "src/resources/tossTheCoinButton.png";

    public TossButton() {
        setButtonImage();
        setIcon(buttonImage);
    }

    private void setButtonImage() {
        Image image=new ImageIcon(BUTTON_ICON_PATH).getImage();
        buttonImage = new ImageIcon(image);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }
}
