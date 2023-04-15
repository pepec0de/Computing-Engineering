package si2023.josemariagonzalez1alu.p02.ia.mef;

import java.util.ArrayList;

public abstract class Estado {

	public ArrayList<Transicion> transiciones;
	protected Accion accion;
	
	public Estado() {
		transiciones = new ArrayList<>();
	}
	
	public Accion getAccion() {
		return accion;
	}

}
