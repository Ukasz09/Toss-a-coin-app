package UI;

import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    JPanel nested;
    public ResultPanel() {
        nested = new JPanel(new GridLayout(2,1));
        add(nested);
//        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        setBackground(Color.BLUE); //todo: tmp
        setOpaque(true); //todo: tmp
    }



}
