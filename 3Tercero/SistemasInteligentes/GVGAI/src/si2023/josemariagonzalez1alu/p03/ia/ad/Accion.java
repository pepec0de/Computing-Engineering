package si2023.josemariagonzalez1alu.p03.ia.ad;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;

public abstract class Accion extends NodoArbol {
	
	@Override
	public NodoArbol decide(Mundo m) {
		return this;
	}
	
	public abstract ACTIONS doAction(Mundo m);

}
