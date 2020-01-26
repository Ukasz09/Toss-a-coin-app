package GUI.panels;

import resources.ResourceTemplateClass;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private static final String IMAGE_PATH = "background.jpg";

    private Image backgroundImage;
    private int screenHeight;
    private int screenWidth;

    public Background() {
        backgroundImage = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(IMAGE_PATH)).getImage();
        setScreenSize();
        setLayout(new BorderLayout());
    }

    private void setScreenSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        g.drawImage(backgroundImage, 0, 0, screenWidth,screenHeight,null);
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
