package amc.practica1.view;

import javax.swing.JFrame;

public class MainVentana extends JFrame {
	
	public MainVentana() {
		init();
	}
	
	private void init() {
		setTitle("AMC - Practica 1");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
