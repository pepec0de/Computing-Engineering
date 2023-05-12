package si2023.josemariagonzalez1alu.p05.ia.strips;

import java.util.ArrayList;

import ontology.Types.ACTIONS;

public abstract class Operador<T> implements IApilable {
	
	public ArrayList<T> precondiciones, adiciones, supresiones;

	public Operador() {
		precondiciones = new ArrayList<>();
		adiciones = new ArrayList<>();
		supresiones = new ArrayList<>();
	}

	public boolean ejecutable(Estado<T> e) {
		return e.abiertos.containsAll(precondiciones);
	}
	
	public void ejecutar(Estado<T> e) {
		e.plan.add(this);
		
		for (T t : supresiones) {
			e.abiertos.remove((Object) t);
		}
		
		for (T t : adiciones) {
			e.abiertos.add(t);
		}
	}
	
	public ACTIONS getAction() {
		return null;
	}

	@Override
	public boolean esOperador() {
		return true;
	}

}
