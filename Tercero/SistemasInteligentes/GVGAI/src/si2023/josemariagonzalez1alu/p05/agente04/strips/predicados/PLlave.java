package si2023.josemariagonzalez1alu.p05.agente04.strips.predicados;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;

public class PLlave implements IPredicado {

	public PLlave() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Llave";
	}
	
	public int hashCode() {
		return 534896743;
	}
	
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o.getClass() == PLlave.class)
			return true;
		
		return false;
	}

	@Override
	public Posicion getPos() {
		// TODO Auto-generated method stub
		return null;
	}
}
