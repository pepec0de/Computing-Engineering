package si2023.josemariagonzalez1alu.p04.ia.busqueda;

import java.util.ArrayList;

public abstract class Nodo {

	public ArrayList<Nodo> siguientes;
	
	public Nodo() {
		siguientes = new ArrayList<>();
	}
	
}
