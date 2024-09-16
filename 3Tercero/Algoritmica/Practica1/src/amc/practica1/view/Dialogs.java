package amc.practica1.view;

import java.awt.TrayIcon;

import javax.swing.JOptionPane;

public class Dialogs {

	public Dialogs() {}
	
	public void showError(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", TrayIcon.MessageType.ERROR.ordinal());
	}
}
