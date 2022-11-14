package amc.practica1.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
	
	public void DijkstraPasos(GrafoDirigido<E, Integer> grafo, int idcNodoInicial,
			ArrayList<Nodo<E>> listaActuales, 
			ArrayList<HashSet<Integer>> listaNodos, 
			ArrayList<int[]> listaDist) {
		
		Set<Arista<E, Integer>> salientes = grafo.getAristasSalientes(idcNodoInicial);
		if (salientes.isEmpty())
			return;
		
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
			listaActuales.add(grafo.getNodoAt(nodoActual));
			listaNodos.add((HashSet<Integer>) nodosPorRecorrer);
			
			salientes = grafo.getAristasSalientes(nodoActual);
		}
		
	}
	
	
}
