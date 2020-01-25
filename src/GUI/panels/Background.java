package GUI.panels;

import resources.ResourceTemplateClass;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private static final String IMAGE_PATH = "background.jpg";
    private final Image backgroundImage;
    private int screenHeight;
    private int screenWidth;
    private Toolkit toolkit;

    public Background() {
        backgroundImage = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(IMAGE_PATH)).getImage();
        toolkit = Toolkit.getDefaultToolkit();
        setScreenSize();
        setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        g.drawImage(backgroundImage, 0, 0, screenWidth,screenHeight,null);
    }

    private void setScreenSize(){
        Dimension screenSize = toolkit.getScreenSize();
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
