package si2023.josemariagonzalez1alu.p05.ia.strips;

import java.util.ArrayList;

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

	public abstract String toString();

	@Override
	public boolean esMultiMeta() {
		return false;
	}

	@Override
	public boolean esMeta() {
		return false;
	}

	@Override
	public boolean esOperador() {
		return true;
	}

}
