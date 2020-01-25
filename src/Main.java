import GUI.ApplicationMainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater (() -> {
            ApplicationMainFrame applicationMain=new ApplicationMainFrame();
            applicationMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            applicationMain.setVisible(true);
        });
    }
}
