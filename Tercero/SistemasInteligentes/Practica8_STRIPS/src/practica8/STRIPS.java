package practica8;

import java.util.ArrayList;
import java.util.Stack;

public class STRIPS {

	private Stack<Apilable> pila;
	private ArrayList<Nodo> abiertos;
	
	public STRIPS() {
		pila = new Stack<>();
	}
	
	public void metodoSTRIPS() {
		while (!pila.isEmpty() || !abiertos.isEmpty()) {
			if (pila.peek().getClass() == Operador.class) {
				Operador op = (Operador) pila.peek();
				if (op.ejecutable()) {
					pila.pop();
					anadir(op);
				} else {
					// Introducir precondiciones del operador en la pila
					pila.push(op.getPrecondiciones());
				}
			} else if (pila.peek().getClass() == Nodo.class) {
				
			}
		}
	}
	
	// Funcion que ejecutara el operador y lo anadira al plan
	public void anadir(Operador op) {
		
	}

}
