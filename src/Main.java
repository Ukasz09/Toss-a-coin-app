import UI.ApplicationMain;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater (() -> {
            ApplicationMain applicationMain=new ApplicationMain();
            applicationMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            applicationMain.setVisible(true);
        });
    }
}
