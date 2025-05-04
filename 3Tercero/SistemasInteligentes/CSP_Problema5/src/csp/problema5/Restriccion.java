package csp.problema5;

import java.util.ArrayList;
import java.util.HashSet;

public class Restriccion {

	ArrayList<Condicion> condiciones;
	int amigo;
	HashSet<Character> senoras;
	
	public Restriccion() {
		condiciones = new ArrayList<>();
	}
	
	public Restriccion(ArrayList<Condicion> condiciones, int amigo, HashSet<Character> senoras) {
		super();
		this.condiciones = condiciones;
		this.amigo = amigo;
		this.senoras = senoras;
	}

	public boolean esAplicable(Estado estado) {
		for (Condicion c : condiciones) 
			if (!c.seCumple(estado))
				return false;
		
		return true;
	}

	public Estado aplica(Estado estado) {
		return null;
	}

}
