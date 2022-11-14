package isdd.practice3.view;

import java.awt.TrayIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Pepe
 */
public class DialogView {

    public void show(int type, String msg) {
        switch (type) {
            case -1:
                JOptionPane.showMessageDialog(null, msg, "ERROR", TrayIcon.MessageType.ERROR.ordinal());
                break;
            
            default:
                JOptionPane.showMessageDialog(null, msg, "WARNING", TrayIcon.MessageType.INFO.ordinal());
        }
    }
}
