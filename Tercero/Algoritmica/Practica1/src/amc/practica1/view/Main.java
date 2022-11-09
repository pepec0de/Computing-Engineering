package amc.practica1.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import amc.practica1.model.*;

public class Main {
	public Main() {
		// burma14.tsp : Puntos mas cercanos -> 7, 10, 8
		
		double coor[][] = {
				{160.53, 970.38},
				{21.52, 95.59},
				{19.41, 97.13},
				{20.09, 94.55} 
		};
		
//		Punto plano[] = getTSPFileNodes("TSPSamples\\burma14.tsp");
//		Algoritmos alg = new Algoritmos();
//		
//		int result[] = alg.BusquedaExhaustiva(plano);
//		System.out.print("Result = ");
//		for (int i = 0; i < result.length; i++)
//			System.out.print(result[i] + ", ");
//		System.out.println();
//		
//		result = alg.BusquedaExhaustivaRC(plano.clone());
//		System.out.print("Result = ");
//		for (int i = 0; i < result.length; i++)
//			System.out.print(result[i] + ", ");
//		System.out.println();
		
		
		GrafoDirigido<Integer, Integer> grafo = new GrafoDirigido<>();
		Nodo<Integer> nodos[] = new Nodo[5];
		for (int i = 0; i < 5; i++) {
			nodos[i] = new Nodo<Integer>(i+1);
			grafo.addNodo(nodos[i]);
		}
		Arista<Integer, Integer> aristas[] = new Arista[8];
		aristas[0] = new Arista(nodos[1-1], nodos[5-1], 10);
		aristas[1] = new Arista(nodos[1-1], nodos[4-1], 100);
		aristas[2] = new Arista(nodos[1-1], nodos[3-1], 30);
		aristas[3] = new Arista(nodos[1-1], nodos[2-1], 50);
		aristas[4] = new Arista(nodos[5-1], nodos[4-1], 10);
		aristas[5] = new Arista(nodos[4-1], nodos[2-1], 20);
		aristas[6] = new Arista(nodos[4-1], nodos[3-1], 50);
		aristas[7] = new Arista(nodos[3-1], nodos[2-1], 5);
		for (int i = 0; i < 8; i++)
			grafo.addArista(aristas[i]);
		
		AlgoritmosGrafo<Integer, Integer> algGrafo = new AlgoritmosGrafo<>();
		int distancias[] = algGrafo.Dijkstra(grafo, 0);
		System.out.print("Dijkstra = ");
		for (int i = 0; i < distancias.length; i++) {
			System.out.print(distancias[i] + ", ");
		}
		System.out.println();
		
	}
	
	// Funcion que toma el archivo de formato TSPLIB95
	private Punto[] getTSPFileNodes(String filepath) {
		Punto[] result = null;
		
		BufferedReader br;
		String line;
		String dim = "DIMENSION: ";
		String type = "DISPLAY_DATA_TYPE: COORD_DISPLAY";
		String start = "NODE_COORD_SECTION";
		String coordLine[];
		int posStr[] = new int[3];
		
		boolean dataStart = false;
		int n = -1;
		try {
			br = new BufferedReader(new FileReader(filepath));
			
			while ( (line = br.readLine()) != null) {
				
				// Tomamos el valor del parametro DIMENSION
				if (line.indexOf(dim) != -1) {
					n = Integer.parseInt(line.substring(dim.length(), line.length()));
					result = new Punto[n];
				} else // Recogemos los datos
				if (dataStart) {
					if (line.strip().equals("EOF")) {
						dataStart = false;
						
					} else {
						line.stripIndent();
						coordLine = line.stripIndent().split(" ");
						for (int i = 0, j = 0; i < coordLine.length && j < 3; i++) {
							if (!coordLine[i].equals("")) {
								posStr[j] = i;
								j++;
							}
						}
						result[Integer.parseInt(coordLine[posStr[0]]) - 1]
								= new Punto(Double.parseDouble(coordLine[posStr[1]]), Double.parseDouble(coordLine[posStr[2]]));			
					}
				} else // Comprobamos si en la siguiente linea ya se presentan los datos 
				if (line.equals(start)) {
					dataStart = true;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Funcion que obtiene el grafo de un conjunto de puntos
	public GrafoDirigido<Integer, Integer> getGraph(Punto[] nodes) {
		int n = nodes.length;
		GrafoDirigido<Integer, Integer> grafo = new GrafoDirigido<>();
		for (int i = 0; i != n; i++) {
			grafo.addNodo(new Nodo<Integer>(i+1, nodes[i]));
		}
		
		for (int i = 0; i < n; i++) {
			// Nodo i
			Arista<Integer, Integer> arista;
			for (int j = i+1; j < n; j++) {
				arista = new Arista<>();
				arista.setOrigen(grafo.getNodoAt(i));
				arista.setDestino(grafo.getNodoAt(j));
				// Aplicamos formula peso = [ (distancia entre los 2 puntos * 100) % 100] +1
				arista.setPeso( (int) (((arista.getOrigen().getPunto().getDistancia( arista.getDestino().getPunto())*100) % 100) + 1));
				
				grafo.addArista(arista);
			}
		}

		return grafo;
	}
	
	public static void main(String args[]) {
		new Main();
	}
}
