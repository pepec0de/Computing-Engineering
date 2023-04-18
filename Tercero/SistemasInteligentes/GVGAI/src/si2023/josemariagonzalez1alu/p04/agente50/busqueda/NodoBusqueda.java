package si2023.josemariagonzalez1alu.p04.agente50.busqueda;

import java.util.ArrayList;

import ontology.Types.ACTIONS;

// Clase Nodo para el arbol de busqueda del juego 50

public class NodoBusqueda {

	public NodoBusqueda padre;
	public ACTIONS operador;
	public int tipo;
	public int x, y;
	public ArrayList<NodoBusqueda> siguientes;

	public NodoBusqueda(NodoBusqueda padre, ACTIONS operador, int tipo, int x, int y) {
		this.padre = padre;
		this.operador = operador;
		this.tipo = tipo;
		this.x = x;
		this.y = y;
	}

}
