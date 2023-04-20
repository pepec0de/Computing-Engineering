package csp.problema5;

import java.util.ArrayList;
import java.util.HashSet;

public class Restriccion {

	ArrayList<Condicion> condiciones;
	int amigo;
	HashSet<Integer> senoras;
	
	public Restriccion() {
		condiciones = new ArrayList<>();
	}
	
	public Restriccion(ArrayList<Condicion> condiciones, int amigo, HashSet<Integer> senoras) {
		super();
		this.condiciones = condiciones;
		this.amigo = amigo;
		this.senoras = senoras;
	}

	public boolean aplica(Estado estado) {
		
		for (Condicion c : condiciones) 
			if (!c.seCumple(estado))
				return false;
		
		return true;
	}

	public int getAmigo() {
		return amigo;
	}

	public HashSet<Integer> getSenoras() {
		return senoras;
	}

}
