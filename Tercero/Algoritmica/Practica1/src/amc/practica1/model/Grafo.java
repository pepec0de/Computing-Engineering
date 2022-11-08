package amc.practica1.model;

import java.util.ArrayList;

public class Grafo<E, F> {
	private ArrayList<Nodo<E>> nodos;
	private ArrayList<Arista<E, F>> aristas;
	
	public Grafo() {
	}
	
	public void addArista(Arista<E, F> arista) {
		this.aristas.add(arista);
	}
	
	public void addNodo(Nodo<E> nodo) {
		this.nodos.add(nodo);
	}

	public Nodo<E> getNodo(int index) {
		return nodos.get(index);
	}
	
}
