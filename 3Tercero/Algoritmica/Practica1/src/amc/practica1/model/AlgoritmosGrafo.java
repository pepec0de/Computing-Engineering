package amc.practica1.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import amc.practica1.types.Arista;
import amc.practica1.types.GrafoDirigido;

public class AlgoritmosGrafo<E, L> {

	// Algoritmo de Dijkstra que devuelve las distancias
	public int[] Dijkstra(GrafoDirigido<E, Integer> grafo, int idcNodoInicial) {
		Set<Arista<E, Integer>> salientes = grafo.getAristasSalientes(idcNodoInicial);
		if (salientes.isEmpty())
			return null;
		Set<Integer> nodosPorRecorrer = new HashSet<>();
		int distancias[] = new int[grafo.getSize()];
		int distRecorrida = 0, distMin;
		int nodoActual = idcNodoInicial, i;
		
		for (i = 0; i < distancias.length; i++) {
			nodosPorRecorrer.add(i);
		}
		
		for (i = 0; i < distancias.length; i++)
			distancias[i] = Integer.MAX_VALUE;
		distancias[idcNodoInicial] = 0;
		
		while (!nodosPorRecorrer.isEmpty()) {
			nodosPorRecorrer.remove(nodoActual);
			
			for (Arista<E, Integer> arista : salientes) {
				i = grafo.getIndexOf(arista.getDestino());
				if (distancias[i] > (arista.getPeso()+distRecorrida)) {
					distancias[i] = (arista.getPeso()+distRecorrida);
				}
			}
			
			distMin = Integer.MAX_VALUE;
			for (i = 0; i < distancias.length; i++) {
				if (nodosPorRecorrer.contains(i) && distMin > distancias[i]) {
					distMin = distancias[i];
					nodoActual = i;
					distRecorrida = distancias[i];
				}
			}
			
			salientes = grafo.getAristasSalientes(nodoActual);
		}
		
		return distancias;
	}
	
	public boolean DijkstraPasos(GrafoDirigido<E, Integer> grafo, int idcNodoInicial,
			ArrayList<E> listaActuales, 
			ArrayList<ArrayList<E>> listaNodos, 
			ArrayList<int[]> listaDist) {

		Set<Arista<E, Integer>> salientes = grafo.getAristasSalientes(idcNodoInicial);
		if (salientes.isEmpty())
			return false;
		
		Set<Integer> nodosPorRecorrer = new HashSet<>();
		int distancias[] = new int[grafo.getSize()];
		int distRecorrida = 0, distMin;
		int nodoActual = idcNodoInicial, i;
		
		for (i = 0; i < distancias.length; i++) {
			nodosPorRecorrer.add(i);
		}
		
		for (i = 0; i < distancias.length; i++)
			distancias[i] = Integer.MAX_VALUE;
		distancias[idcNodoInicial] = 0;
		
		while (!nodosPorRecorrer.isEmpty()) {
			nodosPorRecorrer.remove(nodoActual);
			
			for (Arista<E, Integer> arista : salientes) {
				i = grafo.getIndexOf(arista.getDestino());
				if (distancias[i] > (arista.getPeso()+distRecorrida)) {
					distancias[i] = (arista.getPeso()+distRecorrida);
				}
			}
			
			distMin = Integer.MAX_VALUE;
			for (i = 0; i < distancias.length; i++) {
				if (nodosPorRecorrer.contains(i) && distMin > distancias[i]) {
					distMin = distancias[i];
					nodoActual = i;
					distRecorrida = distancias[i];
				}
			}
			listaDist.add(distancias.clone());
			listaActuales.add(grafo.getNodoAt(nodoActual).getValor());
			listaNodos.add(new ArrayList<>());
			for (Integer nodo : nodosPorRecorrer) {
				listaNodos.get(listaNodos.size()-1).add(grafo.getNodoAt(nodo).getValor());
			}
			
			salientes = grafo.getAristasSalientes(nodoActual);
		}
		return true;
	}
	
	public String DijkstraCamino(GrafoDirigido<E, Integer> grafo, int idcNodoInicial, int idcNodoFinal) {
		String s = "";
		
		Set<Arista<E, Integer>> salientes = grafo.getAristasSalientes(idcNodoInicial);
		if (salientes.isEmpty())
			return null;
		
		Set<Integer> nodosPorRecorrer = new HashSet<>();
		int distancias[] = new int[grafo.getSize()];
		int distRecorrida = 0, distMin;
		int nodoActual = idcNodoInicial, i;
		
		for (i = 0; i < distancias.length; i++) {
			nodosPorRecorrer.add(i);
		}
		
		for (i = 0; i < distancias.length; i++)
			distancias[i] = Integer.MAX_VALUE;
		distancias[idcNodoInicial] = 0;
		
		boolean encontrado = false;
		while (!nodosPorRecorrer.isEmpty() && !encontrado) {
			s += String.valueOf(grafo.getNodoAt(nodoActual).getValor()) + ", ";
			nodosPorRecorrer.remove(nodoActual);
			
			for (Arista<E, Integer> arista : salientes) {
				i = grafo.getIndexOf(arista.getDestino());
				if (distancias[i] > (arista.getPeso()+distRecorrida)) {
					distancias[i] = (arista.getPeso()+distRecorrida);
				}
			}
			
			distMin = Integer.MAX_VALUE;
			for (i = 0; i < distancias.length; i++) {
				if (nodosPorRecorrer.contains(i) && distMin > distancias[i]) {
					distMin = distancias[i];
					nodoActual = i;
					distRecorrida = distancias[i];
				}
			}
			if(nodoActual == idcNodoFinal) {
				encontrado = true;
			}
			salientes = grafo.getAristasSalientes(nodoActual);
		}
		
		return s;
	}
	
	
}
