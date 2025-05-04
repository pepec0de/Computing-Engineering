package amc.practica1.view;

import javax.swing.*;

public class MainVentana extends JFrame {
	
	public JMenuBar bar;
	public JMenu datos;
	public JMenuItem archivo;
	public JMenuItem generarDatos;
	public JMenu grafo;
	public JMenuItem generarGrafo;
	public JMenuItem generarEjemplo;
	public JMenu algo;
	public JMenuItem exhaustivo;
	public JMenuItem recursivo;
	public JMenuItem dijkstra;
	public JMenu grafica;
	public JMenuItem zoomA;
	public JMenuItem zoomR;
	
	public MainVentana() {
		init();
		initMenu();
	}
	
	private void init() {
		setTitle("AMC - Practica 1");
		setSize(850, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initMenu() {
		bar = new JMenuBar();
		setJMenuBar(bar);
		
		datos = new JMenu("Datos");
		archivo = new JMenuItem("Abrir TSP...");
		generarDatos = new JMenuItem("Generar...");
		datos.add(archivo);
		datos.add(generarDatos);
		bar.add(datos);
		
		grafo = new JMenu("Grafo");
		generarGrafo = new JMenuItem("Generar grafo");
		generarEjemplo = new JMenuItem("Generar grafo ejemplo");
		grafo.add(generarGrafo);
		grafo.add(generarEjemplo);
		bar.add(grafo);
		
		algo = new JMenu("Algoritmo");
		exhaustivo = new JMenuItem("Exhaustivo");
		recursivo = new JMenuItem("DyV Recursivo");
		dijkstra = new JMenuItem("Dijkstra");
		algo.add(exhaustivo);
		algo.add(recursivo);
		algo.add(dijkstra);
		bar.add(algo);
		
		grafica = new JMenu("Grafica");
		zoomA = new JMenuItem("Zoom+");
		zoomR = new JMenuItem("Zoom-");
		grafica.add(zoomA);
		grafica.add(zoomR);
		bar.add(grafica);
	}

	
}
