package si2023.josemariagonzalez1alu.p05.agente04.strips.predicados;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;

public class PMeta implements IPredicado {

	public PMeta() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Puerta";
	}
	
	public int hashCode() {
		return 486341651;
	}
	
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o.getClass() == PMeta.class)
			return true;
		
		return false;
	}

	@Override
	public Posicion getPos() {
		// TODO Auto-generated method stub
		return null;
	}
}
