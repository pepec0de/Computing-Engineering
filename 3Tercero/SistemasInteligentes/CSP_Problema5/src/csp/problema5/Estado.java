package csp.problema5;

import java.util.HashMap;
import java.util.HashSet;

public class Estado {

	public HashMap<Character, HashSet<Character>> valores = new HashMap<>();
	
	public Estado() {
		
	}

	public void addSenora(char amigo, char senora) {
		valores.get(amigo).add(senora);
	}
	
}
