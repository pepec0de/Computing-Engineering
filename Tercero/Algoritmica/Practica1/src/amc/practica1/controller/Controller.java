package amc.practica1.controller;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import amc.practica1.model.*;
import amc.practica1.types.*;
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
		algPuntos = new Algoritmos();
		gen = new Generador();
		dialog = new Dialogs();
		
		grafica.setSize(v.getWidth(), v.getHeight());
		v.add(grafica);
	}
	
	public void generarPuntos(Punto datos[]) {
		puntos = datos;
		pintarPuntos();
	}
	
	public void generarGrafo(GrafoDirigido<Integer, Integer> g) {
		grafo = g;
		puntos = new Punto[grafo.getSize()];
		for (Nodo<Integer> nodo : grafo.nodos())
			puntos[grafo.getIndexOf(nodo)] = nodo.getPunto();
		
		pintarPuntos();
		grafica.setColorArista(Color.BLUE);
		grafica.pintarAristas(grafo.aristas());
	}
	
	private void pintarPuntos() {
		grafica.pintarPuntos(puntos);
	}
	
	public void pintarLineasSolucion(Set<Arista<Integer, Integer>> lineas) {
		grafica.setColorArista(Color.GREEN);
		grafica.pintarAristas(lineas);
	}
	
	public void pintarSolucionPuntos(int result[]) {
		// result[0..2]
		int nexo = 2;
		if (puntos[result[0]].getDistancia(puntos[result[1]]) < puntos[result[0]].getDistancia(puntos[result[2]])) {
			nexo = 1;
		} else if (puntos[result[0]].getDistancia(puntos[result[2]]) < puntos[result[1]].getDistancia(puntos[result[2]])) {
			nexo = 0;
		}
		Set<Arista<Integer, Integer>> solucion = new HashSet<>();
		for (int i = 0; i < 3; i++) {
			if (i != nexo) {
				solucion.add(new Arista<Integer, Integer>(new Nodo<Integer>(null, puntos[result[nexo]]), new Nodo<Integer>(null, puntos[result[i]]), null));
			}
		}
		pintarLineasSolucion(solucion);
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

	public Algoritmos getAlgPuntos() {
		return algPuntos;
	}
	
	public GrafoDirigido<Integer, Integer> getGrafo() {
		return grafo;
	}
	
	public AlgoritmosGrafo<Integer, Integer> getAlgGrafo() {
		return algGrafo;
	}
	
}
