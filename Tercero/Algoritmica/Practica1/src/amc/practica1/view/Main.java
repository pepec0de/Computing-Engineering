package amc.practica1.view;

import amc.practica1.controller.Controller;
import amc.practica1.model.Generador;

public class Main {
	public Main() {
		// burma14.tsp : Puntos mas cercanos -> 7, 10, 8
		
		double coor[][] = {
				{160.53, 970.38},
				{21.52, 95.59},
				{19.41, 97.13},
				{20.09, 94.55} 
		};
		
		MainVentana v = new MainVentana();
		Controller controller = new Controller(v);
		v.setVisible(true);
		
		//controller.imprimirPuntos(new Generador().getPuntosTSPFile("TSPSamples\\burma14.tsp"));
		controller.imprimirPuntos(new Generador().getPuntosRandom(5, new amc.practica1.model.Punto(10, 10), new amc.practica1.model.Punto(80, 80)));
	}
	
	public static void main(String args[]) {
		new Main();
	}
}
