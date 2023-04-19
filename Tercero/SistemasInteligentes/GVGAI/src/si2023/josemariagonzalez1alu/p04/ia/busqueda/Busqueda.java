package si2023.josemariagonzalez1alu.p04.ia.busqueda;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;

public abstract class Busqueda {
	
	protected Mundo mundo;
	protected Nodo raiz;
	
	public Busqueda(Mundo mundo) {
		this.mundo = mundo;
	}
	
	public abstract ArrayList<ACTIONS> pensar();
}
