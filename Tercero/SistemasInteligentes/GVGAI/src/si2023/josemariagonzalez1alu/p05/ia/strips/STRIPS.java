package si2023.josemariagonzalez1alu.p05.ia.strips;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;

public abstract class STRIPS<T> {

	protected Estado<T> inicial;
	protected HashSet<Estado<T>> visitados;
	protected ArrayList<Operador<T>> operadores;
	protected Meta<T> meta;
	protected Estado<T> actual;
	
	public STRIPS() {
		this.inicial = new Estado<>();
		this.meta = new Meta<>();
		this.operadores = new ArrayList<>();
		this.visitados = new HashSet<>();
	}
	
	public void solucionarProfundidad() {
		Stack<Estado<T>> estados = new Stack<>();
		estados.push(inicial);
		while (!estados.isEmpty()) {
			actual = estados.pop();
			
			if (visitados.contains(actual))
				continue;
			
			visitados.add(actual);
			
			if (esObjetivo(actual))
				break;
			
			System.out.println(actual);
			BusquedaSTRIPS(actual);
			
			
			for (Estado<T> e : actual.sucesores) {
				estados.push(e);
			}
		}
		System.out.println("Visitados: " + visitados.size());
	}
	
	public void solucionarAnchura() {
		ArrayList<Estado<T>> estados = new ArrayList<>();
		estados.add(inicial);
		while (!estados.isEmpty()) {
			actual = estados.remove(0);
						
			if (visitados.contains(actual))
				continue;
			
			//System.out.println(actual);
			if (esObjetivo(actual))
				break;
			
			BusquedaSTRIPS(actual);
			
			visitados.add(actual);
			
			for (Estado<T> e : actual.sucesores) {
				estados.add(e);
			}
		}
		System.out.println(visitados.size());
	}
	
	@SuppressWarnings("unused")
	public void expandirEstado(Estado<T> e) {
		if (e.pila.peek().esOperador()) {
			Operador<T> op = (Operador<T>) e.pila.peek();
			if (op.ejecutable(e)) {
				op.ejecutar(e);
				e.pila.pop();
			} else {
				// Introducir precondiciones del operador en la pila
				e.pila.push(castPrecondiciones(op.precondiciones));
			}
			expandirEstado(e);
		} else if (e.pila.peek().esMeta()) {
			Meta<T> meta = (Meta<T>) e.pila.peek();
			if (meta.esCierta(e)) {
				e.pila.pop();
				expandirEstado(e);
			}
		}
	}
	
	public boolean esObjetivo(Estado<T> e) {
		return e.abiertos.contains(meta.getMeta());
	}

	public abstract void BusquedaSTRIPS(Estado<T> e);

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
