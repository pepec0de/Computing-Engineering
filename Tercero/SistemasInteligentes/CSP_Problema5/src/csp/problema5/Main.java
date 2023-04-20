package csp.problema5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

	public static void main(String args[]) {
		HashMap<Character, String> nombreVariables = new HashMap<>();
		nombreVariables.put('A', "Antonio");
		nombreVariables.put('L', "Luis");
		nombreVariables.put('P', "Pedro");
		nombreVariables.put('E', "Enrique");
		nombreVariables.put('R', "Ramon");
		nombreVariables.put('D', "David");
		
		// Lista de las listas en la que cada amigo esta con un CONJUNTO posibles señoras
		ArrayList<HashMap<Character, HashSet<Character>>> combinatoria = new ArrayList<>();
		
		// Regla 1
		Condicion c1 = new Condicion('A', new HashSet<Character>() { {add('A');} });
		Condicion c2 = new Condicion('L', new HashSet<Character>() { {add('P');} });
		Condicion c3 = new Condicion('P', new HashSet<Character>() { {add('L'); add('E');} });
		Condicion c4 = new Condicion('R', new HashSet<Character>() { {add('L'); add('E');} });
		Condicion c5 = new Condicion('D', new HashSet<Character>() { {add('D'); } });
		
		Restriccion regla1 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 'E', new HashSet<Character>() {{add('R');}});
		
		// Regla 2
		c1 = new Condicion('A', new HashSet<Character>() { {add('A');} });
		c2 = new Condicion('L', new HashSet<Character>() { {add('L'); add('R'); add('D');} });
		c3 = new Condicion('P', new HashSet<Character>() { {add('P');} });
		c4 = new Condicion('E', new HashSet<Character>() { {add('L'); add('R'); add('D');} });
		c5 = new Condicion('D', new HashSet<Character>() { {add('E'); } });
		
		Restriccion regla2 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 'R', new HashSet<Character>() {{add('R'); add('D');}});
		
		// Regla 3
		c1 = new Condicion('A', new HashSet<Character>() { {add('A'); add('L'); add('P');} });
		c2 = new Condicion('P', new HashSet<Character>() { {add('A'); add('L'); add('P'); add('D');} });
		c3 = new Condicion('E', new HashSet<Character>() { {add('E');} });
		c4 = new Condicion('R', new HashSet<Character>() { {add('R');} });
		c5 = new Condicion('D', new HashSet<Character>() { {add('A'); add('L'); add('P'); add('D');} });
		
		Restriccion regla3 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 'L', new HashSet<Character>() {{add('A'); add('L'); add('D');}});
		
		// Regla 4
		c1 = new Condicion('A', new HashSet<Character>() { {add('A');} });
		c2 = new Condicion('P', new HashSet<Character>() { {add('L'); add('E'); add('D');} });
		c3 = new Condicion('E', new HashSet<Character>() { {add('L'); add('E'); add('D');} });
		c4 = new Condicion('R', new HashSet<Character>() { {add('R');} });
		c5 = new Condicion('D', new HashSet<Character>() { {add('L'); add('D'); } });
		
		Restriccion regla4 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 'L', new HashSet<Character>() { {add('P');} });
		
		// Regla 5
		c1 = new Condicion('L', new HashSet<Character>() { {add('L');} });
		c2 = new Condicion('P', new HashSet<Character>() { {add('P');} });
		c3 = new Condicion('E', new HashSet<Character>() { {add('R');} });
		c4 = new Condicion('R', new HashSet<Character>() { {add('A'); add('E'); add('D');} });
		c5 = new Condicion('D', new HashSet<Character>() { {add('A'); add('E'); add('D');} });
		
		Restriccion regla5 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 'A', new HashSet<Character>() { {add('A'); add('E');} });
		
		// Regla 6
		c1 = new Condicion('A', new HashSet<Character>() { {add('A'); add('P'); add('R'); } });
		c2 = new Condicion('L', new HashSet<Character>() { {add('A'); add('R'); } });
		c3 = new Condicion('P', new HashSet<Character>() { {add('A'); add('P'); add('R');} });
		c4 = new Condicion('E', new HashSet<Character>() { {add('E');} });
		c5 = new Condicion('D', new HashSet<Character>() { {add('D'); } });
		
		Restriccion regla6 = new Restriccion((ArrayList<Condicion>) Arrays.asList(c1, c2, c3, c4, c5), 'R', new HashSet<Character>() {{ add('L'); }});


		
		Estado ini = new Estado();
		
		
	}

}
