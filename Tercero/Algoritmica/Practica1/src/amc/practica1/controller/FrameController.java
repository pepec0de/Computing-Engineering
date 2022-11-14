package amc.practica1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import amc.practica1.view.DijkstraView;
import amc.practica1.view.MainVentana;

public class FrameController {

	private MainVentana main;
	private Controller control;
	private DatosController datos;
	private DijkstraView dijkstraView;
	
	public FrameController(Controller c, MainVentana v) {
		control = c;
		datos = new DatosController(c);
		main = v;
		main.setVisible(true);
		dijkstraView = new DijkstraView();
		initActions();
	}
	
	private void initActions() {
		// GENERACION DE DATOS
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
		
		
		// ALGORITMOS
		main.exhaustivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (control.getPuntos() != null) {
					
				} else {
					control.getDialogs().showError("Necesitas generar puntos.");
				}
				
			}
		});
		
		main.recursivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (control.getPuntos() != null) {
					
				} else {
					control.getDialogs().showError("Necesitas generar puntos.");
				}
				
			}
		});
		
		main.dijkstra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (control.getGrafo() != null) {
					dijkstraView.abrir();
					
				} else {
					control.getDialogs().showError("Necesitas generar un grafo.");
				}
			}
		});
	}
}
