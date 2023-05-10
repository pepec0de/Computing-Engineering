package si2023.josemariagonzalez1alu.p05.agente04.strips.predicados;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;

public class PRoca implements IPredicado {

	private Posicion pos;
	
	public PRoca(Posicion pos) {
		this.pos = pos;
	}
	
	public PRoca(int x, int y) {
		this.pos = new Posicion(x, y);
	}
	
	public Posicion getPos() {
		return pos;
	}
	
	public String toString() {
		return "PRoca(" + pos.toString() + ")";
	}
	
	public int hashCode() {
		return pos.hashCode();
	}
	
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o.getClass() == PRoca.class) {
			PRoca c = (PRoca) o;
			return pos.equals(c.pos);
		}
		
		return false;
	}
}
