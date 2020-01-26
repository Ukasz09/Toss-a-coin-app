package GUI.panels;

import resources.ResourceTemplateClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultBarField extends JPanel {
    private static final double LABEL_WIDTH_TO_SCREEN_PROPORTION = 0.15;
    private static final double LABEL_HEIGHT_TO_SCREEN_PROPORTION = 0.08;
    private static final double LABEL_VGAP_TO_SCREEN_PROPORTION = 0.25;
    private static final String TAILS_LABEL_IMAGE_PATH = "tailsLabel.png";
    private static final String HEADS_LABEL_IMAGE_PATH = "headsLabel.png";
    private static final String FONT_NAME = "SansSerif";
    private static final int FONT_STYLE = Font.BOLD;
    private static final Color FONT_COLOR = Color.white;
    private static final int DIGIT_NUMBERS=2;

    private JTextField counterTextField;
    private int actualCount = 0;
    private Image labelImage;
    private int labelWidth;
    private int labelHeight;

    public ResultBarField(boolean isTailsSide, int textSize, int screenWidth, int screenHeight) {
        labelWidth = (int) (screenWidth * LABEL_WIDTH_TO_SCREEN_PROPORTION);
        labelHeight = (int) (screenHeight * LABEL_HEIGHT_TO_SCREEN_PROPORTION);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, (int) (labelHeight * LABEL_VGAP_TO_SCREEN_PROPORTION)));
        Font font = new Font(FONT_NAME, FONT_STYLE, textSize);
        setLabelImage(isTailsSide);
        setPreferredSize(new Dimension(labelWidth, labelHeight));
        add(getNewCounterField(font));
        setOpaque(false);
    }

    private void setLabelImage(boolean isTailsSide) {
        if (isTailsSide)
            labelImage = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(TAILS_LABEL_IMAGE_PATH)).getImage();
        else
            labelImage = new javax.swing.ImageIcon(ResourceTemplateClass.class.getResource(HEADS_LABEL_IMAGE_PATH)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.repaint();
        g.drawImage(labelImage, 0, 0, labelWidth, labelHeight, null);
    }

    private JTextField getNewCounterField(Font font) {
        counterTextField = new JTextField(DIGIT_NUMBERS);
        counterTextField.setForeground(ResultBarField.FONT_COLOR);
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
