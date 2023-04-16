package si2023.josemariagonzalez1alu.p04.agente16.busqueda;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Nodo;
import si2023.josemariagonzalez1alu.p04.agente16.mente.Mundo16;

// Clase Nodo para el arbol de busqueda del juego 16

public class NodoBusqueda extends Nodo {

	private Mundo16 m;
	public int tipo;
	public int x, y;
	public ArrayList<ACTIONS> acciones;

	public NodoBusqueda(int tipo, int x, int y) {
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		acciones = new ArrayList<>();
	}
	
	public NodoBusqueda(Mundo16 m, int tipo, int x, int y) {
		this.m = m;
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		acciones = new ArrayList<>();
	}

}
