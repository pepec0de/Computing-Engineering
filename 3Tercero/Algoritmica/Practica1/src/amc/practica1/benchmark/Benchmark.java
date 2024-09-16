package amc.practica1.benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import amc.practica1.model.Algoritmos;
import amc.practica1.model.AlgoritmosGrafo;
import amc.practica1.model.Generador;
import amc.practica1.types.Punto;

public class Benchmark {
	
	private static Generador gen = new Generador();
	private static Algoritmos alg = new Algoritmos();
	private static AlgoritmosGrafo<Integer, Integer> algG = new AlgoritmosGrafo<>();
	private static String plotExh = "Cubica(x) = a*x*x*x +b*x*x +c*x +d\n"
									+ "fit Cubica(x) \"Exhaustivo.dat\" using 1:2 via a, b, c, d\n"
									+ "plot \"Exhaustivo.dat\" using 1:2 title \"Exhaustivo\", Cubica(x)\n",
						plotDV = "NlogN(x) = a*x*(log(x)/log(2)) + b*x +c\n"
								+ "fit NlogN(x) \"DyV.dat\" using 1:2 via a, b, c\n"
								+ "plot \"DyV.dat\" using 1:2 title \"DyV\", NlogN(x)\n",
						plotDijkstra = "Cuadrada(x) = a*x*x + b*x + c\n"
										+ "fit Cuadrada(x) \"Dijkstra.dat\" using 1:2 via a, b, c\n"
										+ "plot \"Dijkstra.dat\" using 1:2 title \"Dijkstra\", Cuadrada(x)\n";

	public Benchmark() {
		
	}
	
	public static void generarDatos(String filename, String metodo, String plot) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
		
		bw.write("# Script de gnuplot generado por benchmark\n"
				+ "set title \"" + metodo + "\"\n"
				+ "set key top left vertical inside\n"
				+ "set grid\n"
				+ "set xlabel \"Talla (n)\"\n"
				+ "set ylabel \"Tiempo (ms)\"\n"
				+ plot
				+ "set terminal pdf\n"
				+ "set output \"" + metodo + ".pdf\"\n"
				+ "replot\n"
				+ "pause 5 \"Pulsa Enter para continuar...\"");
		bw.close();
	}
	
	public static long tiempoExhaustivo(int n, int p) {
		long ini = System.currentTimeMillis(), fini;
		alg.BusquedaExhaustiva(gen.getPuntosRandom(n, new Punto(0, 0), new Punto(p, p)));
		fini = System.currentTimeMillis();
		return fini - ini;
	}

	public static void exhaustivoDat(int[] ns) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Exhaustivo.dat")));
		for (int i = 0; i < ns.length; i++) {
			bw.write(ns[i] + "\t" + tiempoExhaustivo(ns[i], ns[i]*2) + "\n");
		}
		bw.close();
		
		generarDatos("exhaustivo.gpl", "Exhaustivo", plotExh);
	}
	
	public static long tiempoDivideVenceras(int n, int p) {
		long ini = System.currentTimeMillis(), fini;
		alg.BusquedaDyV(gen.getPuntosRandom(n, new Punto(0, 0), new Punto(p, p)));
		fini = System.currentTimeMillis();
		return fini - ini;
	}
	
	public static void dyvDat(int[] ns) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("DyV.dat")));
		for (int i = 0; i < ns.length; i++) {
			bw.write(ns[i] + "\t" + tiempoExhaustivo(ns[i], ns[i]*2) + "\n");
		}
		bw.close();
		
		generarDatos("dyv.gpl", "DyV", plotDV);
	}
	
	public static long tiempoDijkstra(int n, int p) {
		long ini = System.currentTimeMillis(), fini;
		algG.Dijkstra(gen.getGraph(gen.getPuntosRandom(n, new Punto(0, 0), new Punto(p, p))), 0);
		fini = System.currentTimeMillis();
		return fini - ini;
	}
	
	public static void dijkstraDat(int[] ns) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Dijkstra.dat")));
		for (int i = 0; i < ns.length; i++) {
			bw.write(ns[i] + "\t" + tiempoDijkstra(ns[i], ns[i]*2) + "\n");
		}
		bw.close();
		
		generarDatos("Dijkstra.gpl", "Dijkstra", plotDijkstra);
	}
}
