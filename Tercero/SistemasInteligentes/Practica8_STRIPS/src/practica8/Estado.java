package practica8;

import java.util.ArrayList;
import java.util.Stack;

public class Estado implements Cloneable {

	public Stack<Apilable> pila;
	public ArrayList<Character> abiertos;
	public ArrayList<Estado> sucesores;
	
	public Estado() {
		pila = new Stack<>();
		abiertos = new ArrayList<>();
		sucesores = new ArrayList<>();
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
		return est;
	}
	
}
