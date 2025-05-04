package practica8;

import java.util.ArrayList;

public class STRIPS {

	private Estado inicial;
	private ArrayList<Estado> estados;
	private ArrayList<Operador> operadores;
	private Apilable meta;
	
	public STRIPS(Estado inicial, Apilable meta, ArrayList<Operador> operadores) {
		this.inicial = inicial;
		this.meta = meta;
		this.operadores = operadores;
		estados = new ArrayList<>();
	}
	
	public void solucionar() {
		estados.add(inicial);
		Estado actual = null;
		while (!estados.isEmpty()) {
			actual = estados.remove(0);
			
			if (esObjetivo(actual)) {
				return;
			}

// DEBUG
//			System.out.println(actual.toString());
//			System.out.println("Plan: ");
//			for (Operador op : actual.plan) {
//				System.out.print(op.toString() + ", ");
//			}
			
			BusquedaSTRIPS(actual);
			estados.addAll(actual.sucesores);
		}
		
		System.out.println("\n\nSOLUCION: ");
		for (Operador op : actual.plan) {
			System.out.print(op.toString() + ", ");
		}
		System.out.println();
		
	}
	
	private boolean esObjetivo(Estado actual) {
		if (meta.esMeta()) {
			Meta m = (Meta) meta;
			if (actual.abiertos.contains(m.getMeta()))
				return true;
		} else { // Es multimeta
			MultiMeta mm = (MultiMeta) meta;
			return actual.abiertos.containsAll(mm.metas);
		}
		return false;
	}

	public void BusquedaSTRIPS(Estado actual) {
		ArrayList<Estado> sucesores = new ArrayList<>();
		if (actual.pila.peek().esOperador()) {
			Operador op = (Operador) actual.pila.peek();
			if (op.ejecutable(actual)) {
				actual.pila.pop();
				
				Estado e = (Estado) actual.clone();
				e.plan.add(op);
				
				for (char c : op.supresiones) {
					e.abiertos.remove((Object) c);
				}
				for (char c : op.adiciones) {
					e.abiertos.add(c);
				}
				sucesores.add(e);
			} else {
				// Introducir precondiciones del operador en la pila
				Estado e = (Estado) actual.clone();
				e.pila.push(castPrecondiciones(op.precondiciones));
				sucesores.add(e);
			}
		} else if (actual.pila.peek().esMeta()) {
			Meta meta = (Meta) actual.pila.peek();
			if (meta.esCierta(actual)) {
				Estado e = (Estado) actual.clone();
				e.pila.pop();
				sucesores.add(e);
			} else {
				// else generar un sucesor por cada instanciacion de operador que añade la meta
				for (Operador op : operadores) {
					if (op.adiciones.contains(meta.getMeta())) {
						Estado e = (Estado) actual.clone();
						e.pila.add(op);
						sucesores.add(e);
					}
				}
				// Si hay sucesores elegir uno
				// else retroceder
			}
		} else if (actual.pila.peek().esMultiMeta()) {
			MultiMeta mmeta = (MultiMeta) actual.pila.peek();
			if (mmeta.esCierta(actual)) {
				actual.pila.pop();
			} else {
				// Generar como sucesores todas las posibles combinaciones de las metas
				ArrayList<ArrayList<Meta>> metas = permutate(mmeta.metas);
				for (ArrayList<Meta> arr : metas) {
					Estado e = (Estado) actual.clone();
					for (Meta m : arr) {
						e.pila.add(m);
					}
					sucesores.add(e);
				}
			}
		}
		actual.sucesores = sucesores;
	}
	
	public Apilable castPrecondiciones(ArrayList<Character> precondiciones) {
		if (precondiciones.size() == 1) {
			Meta m = new Meta(precondiciones.get(0));
			return m;
		} else {
			MultiMeta m = new MultiMeta();
			for (char p : precondiciones)
				m.addMeta(new Meta(p));
			return m;
			
		}
	}

	// Metodo de stackOverflow @ mike-elofson
	public <T> ArrayList<ArrayList<T>> permutate(ArrayList<T> list) {
	    ArrayList<ArrayList<T>> result = new ArrayList<ArrayList<T>>();

	    result.add(new ArrayList<T>());

	    for (int i = 0; i < list.size(); i++) {
	        ArrayList<ArrayList<T>> current = new ArrayList<ArrayList<T>>();

	        for (ArrayList<T> l : result) {
	            for (int j = 0; j < l.size()+1; j++) {
	                l.add(j, list.get(i));

	                ArrayList<T> temp = new ArrayList<T>(l);
	                current.add(temp);

	                l.remove(j);
	            }
	        }

	        result = new ArrayList<ArrayList<T>>(current);
	    }
	    return result;
	}
}
