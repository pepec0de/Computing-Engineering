package si2023.josemariagonzalez1alu.p04.ia.busqueda;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;

public abstract class Nodo {

	protected Mundo mundo;
	public Nodo padre;
	public ACTIONS operador;
	public int tipo;
	public Posicion posicion;
	public ArrayList<Nodo> sucesores;

	public Nodo(Mundo mundo, Nodo padre, ACTIONS operador, int tipo, Posicion posicion) {
		this.mundo = mundo;
		this.padre = padre;
		this.operador = operador;
		this.tipo = tipo;
		this.posicion = posicion;
		sucesores = new ArrayList<>();
	}

	public abstract void calculaSucesores();
}
