package practica1.view;

import practica1.model.Algoritmos;
import practica1.model.Punto;

public class Main {
	public Main() {
		// Puntos más cercanos -> 7, 10, 8
		double coor[][] = {
				{16.47, 96.10},
				{16.47, 94.44},
				{20.09, 92.54},
				{22.39, 93.37},
				{25.23, 97.24},
				{22.00, 96.05},
				{20.47, 97.02},
				{17.20, 96.29},//
				{16.30, 97.38},//
				{14.05, 98.12},
				{16.53, 97.38},//
				{21.52, 95.59},
				{19.41, 97.13},
				{20.09, 94.55} 
		};
		
		double coor1[][] = {
				{16.53, 97.38},
				{21.52, 95.59},
				{19.41, 97.13},
				{20.09, 94.55} 
		};
		
		Punto plano[] = new Punto[coor.length];
		for (int i = 0; i < coor.length; i++) {
			plano[i] = new Punto(coor[i][0], coor[i][1]);
		}
		
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
		
//		alg.BusquedaExhaustivaRC(plano, orden);
//		for (int i = 0; i < plano.length; i++)
//			System.out.println(plano[i].getX() + ", " + plano[i].getY());
		
//		int exhaustiva[] = alg.BusquedaExhaustiva(plano);
//		System.out.println(String.valueOf(exhaustiva));
//		
//		for (int i = 0; i < exhaustiva.length; i++) {
//			System.out.println("Punto" + String.valueOf(i) + ": " + String.valueOf(exhaustiva[i]));
//		}
		
	}
	
	public static void main(String args[]) {
		new Main();
	}
}
