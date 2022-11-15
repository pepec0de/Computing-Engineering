package amc.practica1.controller;

import java.util.Set;

import amc.practica1.model.*;
import amc.practica1.view.Dialogs;
import amc.practica1.view.Grafica;
import amc.practica1.view.MainVentana;

public class Controller {

	private Grafica grafica;
	private FrameController cVentana;
	private Generador gen;
	private Dialogs dialog;
	
	private Punto puntos[];
	private GrafoDirigido<Integer, Integer> grafo;
	
	private Algoritmos algPuntos;
	private AlgoritmosGrafo<Integer, Integer> algGrafo;
	
	public Controller() {
		MainVentana v = new MainVentana();
		cVentana = new FrameController(this, v);
		this.grafica = new Grafica();
		algPuntos = new Algoritmos();
		algGrafo = new AlgoritmosGrafo<>();
		
		grafica.setSize(v.getWidth(), v.getHeight());
		v.add(grafica);
		
	}
	
	public void generarPuntos(Punto datos[]) {
		puntos = datos;
		representarPuntos();
	}
	
	public void generarGrafo(GrafoDirigido<Integer, Integer> g) {
		grafo = g;
		puntos = new Punto[grafo.getSize()];
		for (Nodo<Integer> nodo : grafo.nodos())
			puntos[grafo.getIndexOf(nodo)] = nodo.getPunto();
		
		representarPuntos();
		grafica.pintarAristas(grafo.aristas());
	}
	
	private void representarPuntos() {
		double dPuntos[][] = new double[puntos.length][2];
		for (int i = 0; i < puntos.length; i++) {
			dPuntos[i][0] = puntos[i].getX();
			dPuntos[i][1] = puntos[i].getY();
			System.out.println(puntos[i].getX() + ", " + puntos[i].getY());
		}
		grafica.pintarPuntos(dPuntos);
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
	
	public AlgoritmosGrafo<Integer, Integer> getAlgGrafo() {
		return algGrafo;
	}
	
	
}
