package si2023.josemariagonzalez1alu.p01.ia.motor;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;

public abstract class Motor {
	
	protected Mundo mundo;
	protected ArrayList<Regla> reglas, disparadas;
	
	public Motor(Mundo m) {
		mundo = m;
		reglas = new ArrayList<>();
		disparadas = new ArrayList<>();
	}
	
	public void disparo(Mundo m) {
		disparadas.clear();
		for (Regla r : reglas) {
			if (r.seCumple(m)) 
				disparadas.add(r);
		}
	}
	
	public Regla resolucionConflicto() {
		return null;
	}
	
	public ACTIONS pensar() {
		disparo(mundo);
		Regla r = resolucionConflicto();
		if (r == null) {
			return ACTIONS.ACTION_NIL;
		}
		
		return r.accion.doAction(mundo);
	}
	
}
