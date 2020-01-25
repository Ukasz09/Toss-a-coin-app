package GUI.panels;

import javax.swing.*;
import java.awt.*;

public class TossButtonPanel extends JPanel {
    private static final double HGAP_TO_WIDTH_PROPORTION = 0.1;
    private static final double VGAP_TO_HEIGHT_PROPORTION = 0.1;

    public TossButtonPanel(int screenWidth, int screenHeight) {
        int hGap = (int) (screenWidth * HGAP_TO_WIDTH_PROPORTION);
        int vGap = (int) (screenHeight * VGAP_TO_HEIGHT_PROPORTION);
        setLayout(new FlowLayout(FlowLayout.CENTER, hGap, vGap));
        setOpaque(false);
    }
}
