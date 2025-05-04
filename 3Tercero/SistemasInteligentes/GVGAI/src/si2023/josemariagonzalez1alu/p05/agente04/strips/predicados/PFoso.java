package si2023.josemariagonzalez1alu.p05.agente04.strips.predicados;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;

public class PFoso implements IPredicado {
	
	private Posicion pos;
	
	public PFoso(Posicion pos) {
		this.pos = pos;
	}
	
	public PFoso(int x, int y) {
		this.pos = new Posicion(x, y);
	}
	
	public Posicion getPos() {
		return pos;
	}
	
	public String toString() {
		return "Foso(" + pos.toString() + ")";
	}
	
	public int hashCode() {
		return pos.hashCode();
	}
	
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o.getClass() == PFoso.class) {
			PFoso c = (PFoso) o;
			return pos.equals(c.pos);
		}
		
		return false;
	}

}
