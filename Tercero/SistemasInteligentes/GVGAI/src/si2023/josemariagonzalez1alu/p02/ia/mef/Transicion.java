package si2023.josemariagonzalez1alu.p02.ia.mef;

import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;

public class Transicion {

	protected Condicion condicion;
	protected Estado siguiente;
	
	public Transicion(Condicion condicion, Estado siguiente) {
		this.condicion = condicion;
		this.siguiente = siguiente;
	}

	public boolean seDispara(Mundo m) {
		return condicion.seCumple(m); 
	}
	
	public Estado siguienteEstado() {
		return siguiente;
	}
}
