package si2023.josemariagonzalez1alu.p05.agente04.strips.predicados;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;

public class PAvatarEn implements IPredicado {

	private Posicion pos;
	
	public PAvatarEn(Posicion pos) {
		this.pos = pos;
	}
	
	public PAvatarEn(int x, int y) {
		this.pos = new Posicion(x, y);
	}
	
	public Posicion getPos() {
		return pos;
	}
	
	public String toString() {
		return "En(" + pos.toString() + ")";
	}
	
	public int hashCode() {
		return pos.hashCode();
	}
	
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o.getClass() == PAvatarEn.class) {
			PAvatarEn c = (PAvatarEn) o;
			return pos.equals(c.pos);
		}
		
		return false;
	}
}
