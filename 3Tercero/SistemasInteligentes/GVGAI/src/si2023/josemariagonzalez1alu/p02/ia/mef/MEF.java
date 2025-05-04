package si2023.josemariagonzalez1alu.p02.ia.mef;

import java.util.HashMap;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;

public abstract class MEF {
	
	protected HashMap<String, Estado> estados;
	protected Estado eActual;
	protected Mundo m;
	
	public MEF(Mundo m) {
		this.m = m;
		estados = new HashMap<>();
	}
	
	public abstract Estado disparo();
	public abstract ACTIONS pensar();

}
