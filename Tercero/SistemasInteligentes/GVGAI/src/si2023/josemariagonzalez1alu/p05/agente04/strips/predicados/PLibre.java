package si2023.josemariagonzalez1alu.p05.agente04.strips.predicados;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;

public class PLibre implements IPredicado {

	private Posicion pos;
	
	public PLibre(Posicion pos) {
		this.pos = pos;
	}
	
	public PLibre(int x, int y) {
		this.pos = new Posicion(x, y);
	}
	
	public Posicion getPos() {
		return pos;
	}
	
	public String toString() {
		return "Libre(" + pos.toString() + ")";
	}
	
	public int hashCode() {
		return pos.hashCode();
	}
	
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o.getClass() == PLibre.class) {
			PLibre c = (PLibre) o;
			return pos.equals(c.pos);
		}
		
		return false;
	}
}
