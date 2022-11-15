package amc.practica1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import amc.practica1.model.Punto;
import amc.practica1.view.Dialogs;
import amc.practica1.view.VentanaDatos;

public class DatosController {
	
	private VentanaDatos ventana;
	private Punto pMin, pMax;
	private int n;
	private Controller control;
	
	public DatosController(Controller c) {
		control = c;
		ventana = new VentanaDatos();
		pMin = new Punto();
		pMax = new Punto();
		initActions();
	}
	
	private void initActions() {
		ventana.btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				n = (int) ventana.nPuntos.getValue();
				pMin.setX( (double) (int) ventana.xPuntoMin.getValue());
				pMin.setY((double) (int) ventana.yPuntoMin.getValue());
				pMax.setX((double) (int) ventana.xPuntoMax.getValue());
				pMax.setY((double) (int) ventana.yPuntoMax.getValue());
				if (n <= 0 || (pMin.getX() >= pMax.getX() && pMin.getY() >= pMax.getY())) {
					control.getDialogs().showError("Datos incorrectos");
				} else {
					control.generarPuntos(control.getGenerador().getPuntosRandom(n, pMin, pMax));
					ventana.dispose();
				}
			}
		});
		
		ventana.btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
			}
		});
	}
	
	public void abrir() {
		ventana.setVisible(true);
	}
	
	public int getNPuntos() {
		return n;
	}
	public Punto getPuntoMax() {
		return pMax;
	}
	
	public Punto getPuntoMin() {
		return pMin;
	}
	
}
