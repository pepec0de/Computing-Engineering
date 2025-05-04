package csp.problema5;

import java.util.HashSet;

public class Condicion {

	private char amigo;
	public HashSet<Character> senoras;
	
	public Condicion(char amigo, HashSet<Character> senoras) {
		super();
		this.amigo = amigo;
		this.senoras = senoras;
	}

	public boolean seCumple(Estado estado) {
		HashSet<Character> senorasEstado = estado.valores.get(amigo);
		
		// Comprobamos que algun valor del conjunto de señoras de ese amigo (del estado actual) 
		// este en el conjunto de nuestra condicion
		for (char i : senoras) {
			if (senorasEstado.contains(i))
				return true;
		}
		return false;
	}
	
	

}
