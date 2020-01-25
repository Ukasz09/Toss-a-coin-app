package GUI.Panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultBarField extends JPanel {
    private static final double LABEL_WIDTH_TO_SCREEN_PROPORTION = 0.1;
    private static final double LABEL_HEIGHT_TO_SCREEN_PROPORTION = 0.1;

    private final int digitsNumbers = 2;
    private final String fontName = "SansSerif";
    private final int fontStyle = Font.BOLD;
    private final Color fontColor = Color.white;

    private JTextField counterTextField;
    private int actualCount = 0;

    public ResultBarField(String labelTxt, int textSize, int screenWidth, int screenHeight) {
        Font font = new Font(fontName, fontStyle, textSize);
        add(getNewIdLabel(font, fontColor, labelTxt, screenWidth, screenHeight));
        add(getNewCounterField(font, fontColor, digitsNumbers));
        setOpaque(false);
    }

    private JLabel getNewIdLabel(Font font, Color fontColor, String labelText, int screenWidth, int screenHeight) {
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setForeground(fontColor);
        int labelWidth = (int) (screenWidth * LABEL_WIDTH_TO_SCREEN_PROPORTION);
        int labelHeight = (int) (screenHeight * LABEL_HEIGHT_TO_SCREEN_PROPORTION);
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));
        label.setFont(font);
        label.setOpaque(false);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setVerticalAlignment(SwingConstants.CENTER);

        return label;
    }

    private JTextField getNewCounterField(Font font, Color fontColor, int numberPrecision) {
        counterTextField = new JTextField(numberPrecision);
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
