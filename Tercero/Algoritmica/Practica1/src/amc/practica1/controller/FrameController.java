package amc.practica1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import amc.practica1.view.MainVentana;

public class FrameController {

	private MainVentana main;
	private Controller control;
	private CVentanaDatos datos;
	
	public FrameController(Controller c, MainVentana v) {
		control = c;
		datos = new CVentanaDatos(c);
		main = v;
		main.setVisible(true);
		initActions();
	}
	
	private void initActions() {
		main.archivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser();
				ch.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int r = ch.showOpenDialog(main);
				if (r == JFileChooser.APPROVE_OPTION) {
					control.imprimirPuntos(control.getGenerador().getPuntosTSPFile(ch.getSelectedFile().getAbsolutePath()));
				}
			}
		});
		
		main.generarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datos.abrir();
			}
		});
	}
}
