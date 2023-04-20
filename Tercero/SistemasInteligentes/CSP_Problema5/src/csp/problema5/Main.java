package csp.problema5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		HashMap<Integer, String> nombreVariables = new HashMap<>();
		nombreVariables.put(0, "Antonio");
		nombreVariables.put(1, "Luis");
		nombreVariables.put(2, "Pedro");
		nombreVariables.put(3, "Enrique");
		nombreVariables.put(4, "Ramon");
		nombreVariables.put(5, "David");
		
		// Lista de las listas en la que cada amigo esta con un CONJUNTO posibles señoras
		ArrayList<ArrayList<HashSet<Integer>>> combinatoria = new ArrayList<>();
		
		// Regla 1
		Condicion c1 = new Condicion(0, new HashSet<Integer>() { {add(0);} });
		Condicion c2 = new Condicion(1, new HashSet<Integer>() { {add(2);} });
		Condicion c3 = new Condicion(2, new HashSet<Integer>() { {add(1); add(3);} });
		Condicion c4 = new Condicion(4, new HashSet<Integer>() { {add(1); add(3);} });
		Condicion c5 = new Condicion(5, new HashSet<Integer>() { {add(5); } });
		
		Restriccion regla1 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 3, new HashSet<Integer>() {{add(4);}});
		
		// Regla 2
		c1 = new Condicion(0, new HashSet<Integer>() { {add(0);} });
		c2 = new Condicion(1, new HashSet<Integer>() { {add(1); add(4); add(5);} });
		c3 = new Condicion(2, new HashSet<Integer>() { {add(2);} });
		c4 = new Condicion(3, new HashSet<Integer>() { {add(1); add(4); add(5);} });
		c5 = new Condicion(5, new HashSet<Integer>() { {add(3); } });
		
		Restriccion regla2 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 4, new HashSet<Integer>() {{add(4); add(5);}});
		
		// Regla 3
		c1 = new Condicion(0, new HashSet<Integer>() { {add(0); add(1); add(2);} });
		c2 = new Condicion(2, new HashSet<Integer>() { {add(0); add(1); add(2); add(5);} });
		c3 = new Condicion(3, new HashSet<Integer>() { {add(3);} });
		c4 = new Condicion(4, new HashSet<Integer>() { {add(4);} });
		c5 = new Condicion(5, new HashSet<Integer>() { {add(0); add(1); add(2); add(5);} });
		
		Restriccion regla3 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 1, new HashSet<Integer>() {{add(0); add(1); add(5);}});
		
		// Regla 4
		c1 = new Condicion(0, new HashSet<Integer>() { {add(0);} });
		c2 = new Condicion(2, new HashSet<Integer>() { {add(1); add(3); add(5);} });
		c3 = new Condicion(3, new HashSet<Integer>() { {add(1); add(3); add(5);} });
		c4 = new Condicion(4, new HashSet<Integer>() { {add(4);} });
		c5 = new Condicion(5, new HashSet<Integer>() { {add(1); add(5); } });
		
		Restriccion regla4 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 1, new HashSet<Integer>() { {add(2);} });
		
		// Regla 5
		c1 = new Condicion(1, new HashSet<Integer>() { {add(1);} });
		c2 = new Condicion(2, new HashSet<Integer>() { {add(2);} });
		c3 = new Condicion(3, new HashSet<Integer>() { {add(4);} });
		c4 = new Condicion(4, new HashSet<Integer>() { {add(0); add(3); add(5);} });
		c5 = new Condicion(5, new HashSet<Integer>() { {add(0); add(3); add(5);} });
		
		Restriccion regla5 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 0, new HashSet<Integer>() { {add(0); add(3);} });
		
		// Regla 6
		c1 = new Condicion(0, new HashSet<Integer>() { {add(0);} });
		c2 = new Condicion(1, new HashSet<Integer>() { {add(2);} });
		c3 = new Condicion(2, new HashSet<Integer>() { {add(1); add(3);} });
		c4 = new Condicion(4, new HashSet<Integer>() { {add(1); add(3);} });
		c5 = new Condicion(5, new HashSet<Integer>() { {add(5); } });
		
		Restriccion regla6 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 4, new HashSet<Integer>() {{ add(1); }});
		
		
		
		
		Estado ini = new Estado();
		ini.addSenora(0, 0);
		
		if (c1.seCumple(ini)) {
			System.out.println("yes");
		}
		
		
	}

}
