package amc.practica2.interfaz;

import javax.swing.*;


public class MainVentana extends JFrame {

	public MainVentana() {
		
		setTitle("Práctica 2");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initJMenu();
		
		setVisible(true);
	}
	
	private void initJMenu() {
		bar = new JMenuBar();
		mAutomata = new JMenu("Automatas");
		itemAbrir = new JMenuItem("Abrir archivo...");
		
		setJMenuBar(bar);
		bar.add(mAutomata);
		mAutomata.add(itemAbrir);
	}
	
	private JMenuBar bar;
	private JMenu mAutomata;
	private JMenuItem itemAbrir;
	
}
