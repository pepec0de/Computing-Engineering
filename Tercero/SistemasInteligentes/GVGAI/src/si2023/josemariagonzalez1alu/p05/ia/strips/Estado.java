package si2023.josemariagonzalez1alu.p05.ia.strips;

import java.util.ArrayList;
import java.util.Stack;

public class Estado<T> {

	public Stack<IApilable> pila;
	public ArrayList<T> abiertos;
	public ArrayList<Estado<T>> sucesores;
	public ArrayList<Operador<T>> plan;
	
	public Estado() {
		pila = new Stack<>();
		abiertos = new ArrayList<>();
		sucesores = new ArrayList<>();
		plan = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	public Estado(Estado<T> e) {
		pila = (Stack<IApilable>) e.pila.clone();
		abiertos = (ArrayList<T>) e.abiertos.clone();
		sucesores = new ArrayList<>();
		plan = (ArrayList<Operador<T>>) e.plan.clone();
	}

	public void apilar(IApilable a) {
		pila.add(a);
	}
	
	public void addAbiertos(T c) {
		abiertos.add(c);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		String str = "ESTADO\nAbiertos: ";
		for (T c : abiertos) {
			str += c + ", ";
		}
		str += "\n";
		Stack<IApilable> copia = (Stack<IApilable>) pila.clone();
		while (!copia.isEmpty()) str += "{" + copia.pop().toString() + "}\n";
		str += "******************";
		System.out.print("Plan: ");
		for (Operador<T> op : plan) {
			System.out.print(op + ", ");
		}
		System.out.println("\n");
		return str;
	}
	
}