package si2023.josemariagonzalez1alu.p04.agente50.busqueda;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.agente50.mente.Mundo50;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Nodo;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Posicion;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;

public class Nodo50 extends Nodo {

	private Mundo50 m;
	
	public Nodo50(Mundo mundo, Nodo padre, ACTIONS operador, int tipo, Posicion posicion) {
		super(mundo, padre, operador, tipo, posicion);
		this.m = (Mundo50) mundo;
	}

	public void calculaSucesores() {
		sucesores.clear();
		if (m.movablePosition(posicion.x, posicion.y - 1))
			sucesores.add(
					new Nodo50(mundo, this, ACTIONS.ACTION_UP, m.getTipo(posicion.x, posicion.y - 1), new Posicion(posicion.x, posicion.y - 1)));
		
		if (m.movablePosition(posicion.x, posicion.y + 1))
			sucesores.add(
					new Nodo50(mundo, this, ACTIONS.ACTION_DOWN, m.getTipo(posicion.x, posicion.y + 1), new Posicion(posicion.x, posicion.y + 1)));
		
		if (m.movablePosition(posicion.x - 1, posicion.y))
			sucesores.add(
					new Nodo50(mundo, this, ACTIONS.ACTION_LEFT, m.getTipo(posicion.x - 1, posicion.y), new Posicion(posicion.x - 1, posicion.y)));
		
		if (m.movablePosition(posicion.x + 1, posicion.y))
			sucesores.add(
					new Nodo50(mundo, this, ACTIONS.ACTION_RIGHT, m.getTipo(posicion.x + 1, posicion.y), new Posicion(posicion.x + 1, posicion.y)));
	}
	
}
