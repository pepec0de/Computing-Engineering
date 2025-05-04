package si2023.josemariagonzalez1alu.p00.ia.busqueda;

import java.util.Stack;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p00.ia.mente.Mundo;

public abstract class Busqueda {
	
	protected Mundo mundo;
	protected Nodo raiz;
	
	public Busqueda(Mundo mundo) {
		this.mundo = mundo;
	}
	
	public abstract Stack<ACTIONS> pensar();
}
