package amc.practica1.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Estructura de datos aplicada a Lista de Adyacencia usando ArrayLists
public class GrafoDirigido<E, L> {
	private ArrayList<Nodo<E>> nodos;
	private ArrayList<Arista<E, L>> aristas;
	private ArrayList<ArrayList<Nodo<E>>> adyacencia;
	
	public GrafoDirigido() {
		nodos = new ArrayList<>();
		aristas = new ArrayList<>();
		adyacencia = new ArrayList<>();
	}
	
	public void addArista(Arista<E, L> arista) {
		aristas.add(arista);
		adyacencia.get(getIndexOf(arista.getOrigen())).add(arista.getDestino());
	}
	
	public int getIndexOf(Nodo<E> nodo) {
		return nodos.indexOf(nodo);
	}
	
	public void addNodo(Nodo<E> nodo) {
		nodos.add(nodo);
		adyacencia.add(new ArrayList<>());
	}

	public Nodo<E> getNodoAt(int index) {
		return nodos.get(index);
	}
	
	public int getSize() {
		return nodos.size();
	}
	
	public Set<Nodo<E>> nodos() {
		HashSet<Nodo<E>> conjunto = new HashSet<>();
		for (Nodo<E> nodo : nodos) {
			conjunto.add(nodo);
		}
		return conjunto;
	}
	
	public Set<Arista<E, L>> aristas() {
		HashSet<Arista<E, L>> conjunto = new HashSet<>();
		for (Arista<E, L> a : aristas) {
			conjunto.add(a);
		}
		return conjunto;
	}
	
	public Set<Nodo<E>> adyacentes(int idc) {
		HashSet<Nodo<E>> conjunto = new HashSet<>();
		
		for (Nodo<E> nodo : adyacencia.get(idc)) {
			conjunto.add(nodo);
		}
		
		return conjunto;
	}
	
	public Set<Arista<E, L>> getAristasSalientes(int idc) {
		HashSet<Arista<E, L>> aristasSalientes = new HashSet<>();
		Nodo<E> nodo = getNodoAt(idc);
		for (Arista<E, L> arista : aristas) {
			if (arista.getOrigen().equals(nodo)) {
				aristasSalientes.add(arista);
			}
		}
		return aristasSalientes;
	}
}
