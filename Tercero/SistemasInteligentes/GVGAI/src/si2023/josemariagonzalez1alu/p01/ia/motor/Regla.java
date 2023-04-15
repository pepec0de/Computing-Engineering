package si2023.josemariagonzalez1alu.p01.ia.motor;

import java.util.ArrayList;

import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;

public abstract class Regla {
	
	protected ArrayList<Condicion> antecedentes;
	public Accion accion;
	
	public Regla() {
		antecedentes = new ArrayList<>();
	}
	
	public boolean seCumple(Mundo m) {
		for (Condicion cond : antecedentes)
			if (!cond.seCumple(m))
				return false;
		
		return true;
	}
	
}
