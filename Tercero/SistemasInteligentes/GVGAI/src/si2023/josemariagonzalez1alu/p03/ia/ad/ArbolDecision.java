package si2023.josemariagonzalez1alu.p03.ia.ad;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;

public abstract class ArbolDecision {

	protected Mundo m;
	protected NodoArbol raiz;
	
	public ArbolDecision(Mundo m) {
		this.m = m;
	}
	
	public abstract ACTIONS piensa();

}
