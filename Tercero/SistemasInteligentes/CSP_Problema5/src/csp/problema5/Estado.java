package csp.problema5;

import java.util.ArrayList;
import java.util.HashSet;

public class Estado {

	public ArrayList<HashSet<Integer>> valores = new ArrayList<>();
	
	public Estado() {
		for (int i = 0; i < Datos.N; i++) {
			valores.add(new HashSet<>());
		}
	}

	public void addSenora(int amigo, int senora) {
		valores.get(amigo).add(senora);
	}
	
}
