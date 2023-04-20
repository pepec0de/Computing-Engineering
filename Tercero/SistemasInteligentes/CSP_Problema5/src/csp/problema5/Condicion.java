package csp.problema5;

import java.util.HashSet;

public class Condicion {

	private int amigo;
	public HashSet<Integer> senoras;
	
	public Condicion(int amigo, HashSet<Integer> senoras) {
		super();
		this.amigo = amigo;
		this.senoras = senoras;
	}

	public boolean seCumple(Estado estado) {
		HashSet<Integer> senorasEstado = estado.valores.get(amigo);
		
		// Comprobamos que algun valor del conjunto de señoras de ese amigo (del estado actual) 
		// este en el conjunto de nuestra condicion
		for (Integer i : senoras) {
			if (senorasEstado.contains(i))
				return true;
		}
		return false;
	}
	
	

}
