package amc.practica1.controller;

import amc.practica1.model.Generador;
import amc.practica1.model.GrafoDirigido;
import amc.practica1.model.Punto;
import amc.practica1.view.Dialogs;
import amc.practica1.view.Grafica;
import amc.practica1.view.MainVentana;

public class Controller {

	private Grafica grafica;
	private FrameController cVentana;
	private Generador gen;
	private Punto puntos[];
	private GrafoDirigido<Integer, Integer> grafo;
	private Dialogs dialog;
	
	public Controller() {
		MainVentana v = new MainVentana();
		
		cVentana = new FrameController(this, v);
		
		this.grafica = new Grafica();
		grafica.setSize(v.getWidth(), v.getHeight());
		v.add(grafica);
		
		gen = new Generador();
		dialog = new Dialogs();
	}
	
	public void imprimirPuntos(Punto datos[]) {
		puntos = datos;
		double dPuntos[][] = new double[puntos.length][2];
		for (int i = 0; i < puntos.length; i++) {
			dPuntos[i][0] = puntos[i].getX();
			dPuntos[i][1] = puntos[i].getY();
			System.out.println(puntos[i].getX() + ", " + puntos[i].getY());
		}
		grafica.pintarPuntos(dPuntos);
	}
	
	public void imprimirGrafo(GrafoDirigido<Integer, Integer> datos) {
		grafo = datos;
	}
	
	public Generador getGenerador() {
		return gen;
	}
	
	public Grafica getGrafica() {
		return grafica;
	}
	
	public Dialogs getDialogs() {
		return dialog;
	}

	public Punto[] getPuntos() {
		return puntos;
	}

	public void setPuntos(Punto[] puntos) {
		this.puntos = puntos;
	}

	public GrafoDirigido<Integer, Integer> getGrafo() {
		return grafo;
	}

	public void setGrafo(GrafoDirigido<Integer, Integer> grafo) {
		this.grafo = grafo;
	}
	
	
}
