package practica8;

import java.util.ArrayList;
import java.util.Stack;

public class Estado implements Cloneable {

	public Stack<Apilable> pila;
	public ArrayList<Character> abiertos;
	public ArrayList<Estado> sucesores;
	public ArrayList<Operador> plan;
	
	public Estado() {
		pila = new Stack<>();
		abiertos = new ArrayList<>();
		sucesores = new ArrayList<>();
		plan = new ArrayList<>();
	}

	public void apilar(Apilable a) {
		pila.add(a);
	}
	
	public void addAbiertos(Character c) {
		abiertos.add(c);
	}
	
	@Override
	public Object clone() {
		Estado est = new Estado();
		est.pila = (Stack<Apilable>) pila.clone();
		est.abiertos = (ArrayList<Character>) abiertos.clone();
		est.plan = (ArrayList<Operador>) plan.clone();
		return est;
	}
	
	@Override
	public String toString() {
		String str = "ESTADO\nAbiertos: ";
		for (Character c : abiertos) {
			str += c + ", ";
		}
		str += "\nPila: ";
		Stack<Apilable> copia = (Stack<Apilable>) pila.clone();
		while (!copia.isEmpty()) str += "{" + copia.pop().toString() + "}, ";
		str += "\n";
		return str;
	}
	
}
