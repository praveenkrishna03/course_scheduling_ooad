import View.MainFrame;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        // runs in AWT thread
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
