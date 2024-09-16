package amc.practica1.model;

import java.io.*;
import java.util.HashSet;
import java.util.Random;

import amc.practica1.types.Arista;
import amc.practica1.types.GrafoDirigido;
import amc.practica1.types.Nodo;
import amc.practica1.types.Punto;

public class Generador {

	public GrafoDirigido<Integer, Integer> grafoEjemplo() {
		GrafoDirigido<Integer, Integer> grafo = new GrafoDirigido<>();
		Nodo<Integer> nodos[] = new Nodo[5];
		Random r = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < 5; i++) {
			nodos[i] = new Nodo<Integer>(i+1);
			grafo.addNodo(nodos[i]);
		}
		
		nodos[0].setPunto(new Punto(13, 39));
		nodos[1].setPunto(new Punto(15, 34));
		nodos[2].setPunto(new Punto(17, 30));
		nodos[3].setPunto(new Punto(10, 33));
		nodos[4].setPunto(new Punto(9, 40));
		
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
		
		return grafo;
	}
	
	// Funcion que obtiene el grafo de un conjunto de puntos
	public GrafoDirigido<Integer, Integer> getGraph(Punto[] nodes) {
		int n = nodes.length;
		GrafoDirigido<Integer, Integer> grafo = new GrafoDirigido<>();
		for (int i = 0; i != n; i++) {
			grafo.addNodo(new Nodo<Integer>(i+1, nodes[i]));
		}
		
		Arista<Integer, Integer> arista;
		for (int i = 0; i < n; i++) {
			// Nodo i
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
	
	// Funcion que toma el archivo de formato TSPLIB95
	public Punto[] getPuntosTSPFile(String filepath) {
		Punto[] result = null;
		
		BufferedReader br;
		String line;
		String dim1 = "DIMENSION: ", dim2 = "DIMENSION : ";
		String start = "NODE_COORD_SECTION";
		String coordLine[];
		int posStr[] = new int[3];
		
		boolean dataStart = false;
		int n = -1;
		try {
			br = new BufferedReader(new FileReader(filepath));
			
			while ( (line = br.readLine()) != null) {
				
				// Tomamos el valor del parametro DIMENSION
				if (line.indexOf(dim1) != -1) {
					n = Integer.parseInt(line.substring(dim1.length(), line.length()));
					result = new Punto[n];
				} else if(line.indexOf(dim2) != -1) {
					n = Integer.parseInt(line.substring(dim2.length(), line.length()));
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
	
	// Funcion para generar un conjunto de puntos aleatorio
	public Punto[] getPuntosRandom(int n, Punto pMin, Punto pMax) {
		Random r = new Random(System.currentTimeMillis());
		Punto[] plano = new Punto[n];
		HashSet<Punto> puntos = new HashSet<>();
		
		Punto p;
		for (int i = 0; i < n;) {
			p = new Punto((double) r.nextInt((int)pMin.getX(), (int)pMax.getX()), (double)r.nextInt( (int)pMin.getY(), (int)pMax.getY()));
			if (!puntos.contains(p)) {
				puntos.add(p);
				plano[i] = p;
				i++;
			}
		}
		
		return plano;
	}
}
