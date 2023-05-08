package si2023.josemariagonzalez1alu.p05.ia.strips;

import java.util.ArrayList;

public class STRIPS<T> {

	private Estado<T> inicial;
	private ArrayList<Estado<T>> estados;
	private ArrayList<Operador<T>> operadores;
	private IApilable meta;
	
	public STRIPS(Estado<T> inicial, IApilable meta, ArrayList<Operador<T>> operadores) {
		this.inicial = inicial;
		this.meta = meta;
		this.operadores = operadores;
		estados = new ArrayList<>();
	}
	
	public void solucionar() {
		estados.add(inicial);
		Estado<T> actual = null;
		while (!estados.isEmpty()) {
			actual = estados.remove(0);
			
			if (esObjetivo(actual)) {
				return;
			}
			
			BusquedaSTRIPS(actual);
			estados.addAll(actual.sucesores);
		}
		
		System.out.println("\n\nSOLUCION: ");
		for (Operador<T> op : actual.plan) {
			System.out.print(op.toString() + ", ");
		}
		System.out.println();
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean esObjetivo(Estado<T> actual) {
		if (meta.esMeta()) {
			Meta<T> m = (Meta<T>) meta;
			if (actual.abiertos.contains(m.getMeta()))
				return true;
		} else { // Es multimeta
			MultiMeta<T> mm = (MultiMeta<T>) meta;
			return actual.abiertos.containsAll(mm.metas);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void BusquedaSTRIPS(Estado<T> actual) {
		ArrayList<Estado<T>> sucesores = new ArrayList<>();
		if (actual.pila.peek().esOperador()) {
			Operador<T> op = (Operador<T>) actual.pila.peek();
			if (op.ejecutable(actual)) {
				actual.pila.pop();
				
				Estado<T> e = new Estado<T>(actual);
				e.plan.add(op);
				
				for (T c : op.supresiones) {
					e.abiertos.remove((Object) c);
				}
				for (T c : op.adiciones) {
					e.abiertos.add(c);
				}
				sucesores.add(e);
			} else {
				// Introducir precondiciones del operador en la pila
				Estado<T> e = new Estado<T>(actual);
				e.pila.push(castPrecondiciones(op.precondiciones));
				sucesores.add(e);
			}
		} else if (actual.pila.peek().esMeta()) {
			Meta<T> meta = (Meta<T>) actual.pila.peek();
			if (meta.esCierta(actual)) {
				Estado<T> e = new Estado<T>(actual);
				e.pila.pop();
				sucesores.add(e);
			} else {
				// else generar un sucesor por cada instanciacion de operador que añade la meta
				for (Operador<T> op : operadores) {
					if (op.adiciones.contains(meta.getMeta())) {
						Estado<T> e = new Estado<T>(actual);
						e.pila.add(op);
						sucesores.add(e);
					}
				}
				// Si hay sucesores elegir uno
				// else retroceder
			}
		} else if (actual.pila.peek().esMultiMeta()) {
			MultiMeta<T> mmeta = (MultiMeta<T>) actual.pila.peek();
			if (mmeta.esCierta(actual)) {
				actual.pila.pop();
			} else {
				// Generar como sucesores todas las posibles combinaciones de las metas
				ArrayList<ArrayList<Meta<T>>> metas = permutate(mmeta.metas);
				for (ArrayList<Meta<T>> arr : metas) {
					Estado<T> e = new Estado<T>(actual);
					for (Meta<T> m : arr) {
						e.pila.add(m);
					}
					sucesores.add(e);
				}
			}
		}
		actual.sucesores = sucesores;
	}
	
	public IApilable castPrecondiciones(ArrayList<T> precondiciones) {
		if (precondiciones.size() == 1) {
			Meta<T> m = new Meta<T>(precondiciones.get(0));
			return m;
		} else {
			MultiMeta<T> m = new MultiMeta<T>();
			for (T p : precondiciones)
				m.addMeta(new Meta<T>(p));
			return m;
			
		}
	}

	// Metodo de stackOverflow @ mike-elofson
	public <A> ArrayList<ArrayList<A>> permutate(ArrayList<A> list) {
	    ArrayList<ArrayList<A>> result = new ArrayList<ArrayList<A>>();

	    result.add(new ArrayList<A>());

	    for (int i = 0; i < list.size(); i++) {
	        ArrayList<ArrayList<A>> current = new ArrayList<ArrayList<A>>();

	        for (ArrayList<A> l : result) {
	            for (int j = 0; j < l.size()+1; j++) {
	                l.add(j, list.get(i));

	                ArrayList<A> temp = new ArrayList<A>(l);
	                current.add(temp);

	                l.remove(j);
	            }
	        }

	        result = new ArrayList<ArrayList<A>>(current);
	    }
	    return result;
	}
}
