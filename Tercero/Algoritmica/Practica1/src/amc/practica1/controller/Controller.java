package amc.practica1.controller;

import amc.practica1.model.Generador;
import amc.practica1.model.Punto;
import amc.practica1.view.Grafica;
import amc.practica1.view.MainVentana;

public class Controller {

	private MainVentana ventana;
	private Grafica grafica;
	private FrameController cVentana;
	
	public Controller(MainVentana v) {
		this.ventana = v;
		this.grafica = new Grafica();
		cVentana = new FrameController(v);
		v.add(grafica);
		Generador gen = new Generador();
	}
	
	public void imprimirPuntos(Punto puntos[]) {
		System.out.println("HOLA");
		double dPuntos[][] = new double[puntos.length][2];
		for (int i = 0; i < puntos.length; i++) {
			dPuntos[i][0] = puntos[i].getX();
			dPuntos[i][1] = puntos[i].getY();
			System.out.println(puntos[i].getX() + ", " + puntos[i].getY());
		}
		grafica.pintarPuntos(dPuntos);
	}
	
}
