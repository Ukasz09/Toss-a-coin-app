package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private JPanel nestedPanel;

    public ResultPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        nestedPanel = new JPanel(new GridLayout(2, 1));
        nestedPanel.setOpaque(false);
        add(nestedPanel);
        setOpaque(false);
    }

    public JPanel getNestedPanel() {
        return nestedPanel;
    }
}
