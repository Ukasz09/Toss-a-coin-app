package UI;

import javax.swing.*;
import java.awt.*;

public class TossButtonPanel extends JPanel {
    private static final double HGAP_TO_WIDTH_PROPORTION = 0.1;
    private static final double VGAP_TO_HEIGHT_PROPORTION = 0.1;

    public TossButtonPanel(Background background) {
        int hGap = (int) (background.getScreenWidth() * HGAP_TO_WIDTH_PROPORTION);
        int vGap = (int) (background.getScreenHeight() * VGAP_TO_HEIGHT_PROPORTION);
        setLayout(new FlowLayout(FlowLayout.CENTER, hGap, vGap));

        setBackground(Color.CYAN);//todo: tmp
        setOpaque(true); //todo: tmp
    }
}
