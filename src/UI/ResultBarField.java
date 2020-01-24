package UI;

import javafx.scene.layout.VBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultBarField extends JPanel {
    private int digitsNumbers = 3;
    private String fontName = "SansSerif";
    private int fontStyle = Font.BOLD;

    public ResultBarField(String labelTxt, int textSize) {
        Font font = new Font(fontName, fontStyle, textSize);
        add(getNewIdLabel(font, labelTxt));
        add(getNewCounterField(font, digitsNumbers));
        setBackground(Color.pink); //todo: tmp
//        setOpaque(false);
    }

    //todo: border na stale wzgledem ekranu
    private JLabel getNewIdLabel(Font font, String labelText) {
        JLabel label = new JLabel(labelText,SwingConstants.CENTER);
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.red);
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(10,20,10,20));
        return label;
    }

    private JTextField getNewCounterField(Font font, int numberPrecision) {
        JTextField textField = new JTextField(numberPrecision);
        textField.setText("0");
        textField.setOpaque(false);
//        textField.setBorder(new EmptyBorder(0, 0, 0, 0));
        textField.setBorder(new EmptyBorder(10,20,10,20));
        textField.setEditable(false);
        textField.setFont(font);
        textField.setHorizontalAlignment(JTextField.CENTER);

        return textField;
    }
}
