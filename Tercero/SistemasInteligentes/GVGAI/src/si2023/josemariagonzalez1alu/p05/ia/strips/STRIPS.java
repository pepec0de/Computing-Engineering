package si2023.josemariagonzalez1alu.p05.ia.strips;

import java.util.ArrayList;
import java.util.Stack;

public abstract class STRIPS<T> {

	protected Estado<T> inicial;
	protected Stack<Estado<T>> estados;
	protected ArrayList<Operador<T>> operadores;
	protected Meta<T> meta;
	protected Estado<T> actual;
	
	public STRIPS() {
		this.inicial = new Estado<>();
		this.meta = new Meta<>();
		this.operadores = new ArrayList<>();
		this.estados = new Stack<>();
	}
	
	public void solucionar() {
		estados.push(inicial);
		while (!estados.isEmpty()) {
			actual = estados.pop();
			
			if (esObjetivo(actual))
				break;
			
			System.out.println(actual);
			BusquedaSTRIPS(actual);
			for (Estado<T> e : actual.sucesores) {
				estados.push(e);
			}
		}
	}
	
	private boolean esObjetivo(Estado<T> actual) {
		return actual.abiertos.contains(meta.getMeta());
	}

	public abstract void BusquedaSTRIPS(Estado<T> actual);

	@SuppressWarnings("unchecked")
	protected boolean existeBucle(Estado<T> actual) {
		Stack<IApilable> pila = (Stack<IApilable>) actual.pila.clone();
		ArrayList<Meta<T>> metas = new ArrayList<>();
		
		while (!pila.isEmpty()) {
			IApilable head = pila.pop();
			if (head.esMeta()) {
				metas.add((Meta<T>) head);
			}
		}

		for (int i = 0; i < metas.size(); i++) {
			for (int j = i + 1; j < metas.size(); j++) {
				if (metas.get(i).equals(metas.get(j)))
					return true;
			}
		}
		return false;
	}
	
	protected IApilable castPrecondiciones(ArrayList<T> precondiciones) {
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
	protected <A> ArrayList<ArrayList<A>> permutate(ArrayList<A> list) {
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