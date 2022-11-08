package amc.practica1.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import amc.practica1.model.Algoritmos;
import amc.practica1.model.Punto;

public class Main {
	public Main() {
		// burma14.tsp : Puntos más cercanos -> 7, 10, 8
		
		double coor[][] = {
				{160.53, 970.38},
				{21.52, 95.59},
				{19.41, 97.13},
				{20.09, 94.55} 
		};
		
		Punto plano[] = getTSPFileNodes("TSPSamples\\burma14.tsp");
		Algoritmos alg = new Algoritmos();
		
		int result[] = alg.BusquedaExhaustiva(plano);
		System.out.print("Result = ");
		for (int i = 0; i < result.length; i++)
			System.out.print(result[i] + ", ");
		System.out.println();
		
		result = alg.BusquedaExhaustivaRC(plano.clone());
		System.out.print("Result = ");
		for (int i = 0; i < result.length; i++)
			System.out.print(result[i] + ", ");
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
		
		boolean isTypeOk = false, dataStart = false;
		int n = -1;
		try {
			br = new BufferedReader(new FileReader(filepath));
			
			while ( (line = br.readLine()) != null) {
				
				// Tomamos el valor del parametro DIMENSION
				if (line.indexOf(dim) != -1) {
					n = Integer.parseInt(line.substring(dim.length(), line.length()));
					result = new Punto[n];
				} else // Comprobamos que el tipo de datos es el correcto
				if (line.equals(type)) {
					isTypeOk = true;
				} else // Recogemos los datos
				if (isTypeOk && dataStart) {
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
	
	public static void main(String args[]) {
		new Main();
	}
}
