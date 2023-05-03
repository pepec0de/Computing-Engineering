package practica8;

import java.util.ArrayList;
import java.util.Stack;

public class STRIPS {

	private Estado inicial;
	private ArrayList<Estado> estadosAbiertos;
	private ArrayList<Operador> plan, operadores;
	private Apilable meta;
	
	private int cont = 0;
	
	public STRIPS(Estado inicial, Apilable meta, ArrayList<Operador> operadores) {
		this.inicial = inicial;
		this.meta = meta;
		this.operadores = operadores;
		estadosAbiertos = new ArrayList<>();
		plan = new ArrayList<>();
	}
	
	public void solucionar() {
		estadosAbiertos.add(inicial);
		while (!estadosAbiertos.isEmpty()) {
			System.out.println(cont++);
			Estado actual = estadosAbiertos.remove(0);
			
			if (esObjetivo(actual)) {
				return;
			}
			
			actual.sucesores = BusquedaSTRIPS(actual);
			estadosAbiertos.addAll(actual.sucesores);
		}
		
		System.out.println("Plan: ");
		for (Operador op : plan) {
			System.out.print(op.getNombre() + ", ");
		}
		System.out.println();
		
	}
	
	private boolean esObjetivo(Estado actual) {
		if (meta.esMeta()) {
			Meta m = (Meta) meta;
			if (actual.abiertos.contains(m))
				return true;
		} else { // Es multimeta
			MultiMeta mm = (MultiMeta) meta;
			return actual.abiertos.containsAll(mm.metas);
		}
		return false;
	}

	public ArrayList<Estado> BusquedaSTRIPS(Estado padre) {
		ArrayList<Estado> sucesores = new ArrayList<>();
		while (!padre.pila.isEmpty() || !estadosAbiertos.isEmpty()) {
			System.out.println("while");
			if (padre.pila.peek().esOperador()) {
				Operador op = (Operador) padre.pila.peek();
				if (op.ejecutable(padre)) {
					padre.pila.pop();
					plan.add(op);
				} else {
					// Introducir precondiciones del operador en la pila
					padre.pila.push(castPrecondiciones(op.precondiciones));
				}
			} else if (padre.pila.peek().esMeta()) {
				Meta meta = (Meta) padre.pila.peek();
				if (meta.esCierta(padre)) {
					padre.pila.pop();
				} else {
					// Comprobar si es bucle
					
					// else generar un sucesor por cada instanciacion de operador que añade la meta
					for (Operador op : operadores) {
						if (op.adiciones.contains(meta.getMeta())) {
							Estado e = (Estado) padre.clone();
							e.pila.add(op);
							sucesores.add(e);
						}
					}
					// Si hay sucesores elegir uno
					// else retroceder
				}
			} else if (padre.pila.peek().esMultiMeta()) {
				MultiMeta mmeta = (MultiMeta) padre.pila.peek();
				if (mmeta.esCierta(padre)) {
					padre.pila.pop();
				} else {
					// Generar como sucesores todas las posibles combinaciones de las metas
					ArrayList<ArrayList<Meta>> metas = permutate(mmeta.metas);
					for (ArrayList<Meta> arr : metas) {
						Estado e = (Estado) padre.clone();
						for (Meta m : arr) {
							e.pila.add(m);
						}
						sucesores.add(e);
					}
				}
			}
		}
		return sucesores;
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
