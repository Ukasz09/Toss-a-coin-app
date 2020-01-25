package GUI.panels;

import resources.ResourceTemplateClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultBarField extends JPanel {
    private static final double LABEL_WIDTH_TO_SCREEN_PROPORTION = 0.15;
    private static final double LABEL_HEIGHT_TO_SCREEN_PROPORTION = 0.08;
    private static final String TAILS_LABEL_IMAGE_PATH = "tailsLabel.png";
    private static final String HEADS_LABEL_IMAGE_PATH = "headsLabel.png";
    private final int digitsNumbers = 2;
    private final String fontName = "SansSerif";
    private final int fontStyle = Font.BOLD;
    private final Color fontColor = Color.white;

    private JTextField counterTextField;
    private int actualCount = 0;
    private Image labelImage;
    private int labelWidth;
    private int labelHeight;

    public ResultBarField(boolean tailsSide, int textSize, int screenWidth, int screenHeight) {
        labelWidth = (int) (screenWidth * LABEL_WIDTH_TO_SCREEN_PROPORTION);
        labelHeight = (int) (screenHeight * LABEL_HEIGHT_TO_SCREEN_PROPORTION);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, labelHeight / 4));
        Font font = new Font(fontName, fontStyle, textSize);
        if (tailsSide)
            labelImage = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(TAILS_LABEL_IMAGE_PATH)).getImage();
        else
            labelImage = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(HEADS_LABEL_IMAGE_PATH)).getImage();
        setPreferredSize(new Dimension(labelWidth, labelHeight));
        add(getNewCounterField(font, fontColor));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.repaint();
        g.drawImage(labelImage, 0, 0, labelWidth, labelHeight, null);
    }

    private JTextField getNewCounterField(Font font, Color fontColor) {
        counterTextField = new JTextField(2);
        counterTextField.setForeground(fontColor);
        counterTextField.setText(String.valueOf(actualCount));
        counterTextField.setOpaque(false);
        counterTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        counterTextField.setEditable(false);
        counterTextField.setFont(font);
        counterTextField.setHorizontalAlignment(JTextField.CENTER);
        return counterTextField;
    }

    public void setActualCount(int actualCount) {
        this.actualCount = actualCount;
        counterTextField.setText(String.valueOf(actualCount));
    }
}
