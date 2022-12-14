package amc.practica2.clases;

import java.util.ArrayList;
import java.util.List;

public class AFD implements Cloneable, IAutomata {
	
	private int[] estadosFinales; // indica cuales son los estados Finales
	private List<TransicionAFD> transiciones; // indica la lista de transiciones del AFD

	public AFD() {
		transiciones = new ArrayList<>();
	}

	public void agregarTransicion(int e1, char simbolo, int e2) {
		transiciones.add(new TransicionAFD(e1, e2, simbolo));
	}

	public int transicion(int estado, char simbolo) {
		// Buscamos la Transicion que corresponda con estado y simbolo
		for (TransicionAFD transicion : transiciones) {
			if (transicion.getEstadoOrigen() == estado && transicion.getSimbolo() == simbolo) {
				return transicion.getEstadoDestino();
			}
		}
		return -1;
	}
	
	@Override
	public boolean esFinal(int estado) {
		for (int i = 0; i < estadosFinales.length; i++) {
			if (estadosFinales[i] == estado)
				return true;
		}
		return false;
	}
	
	@Override
	public boolean reconocer(String cadena) {
		char[] simbolo = cadena.toCharArray();
		int estado = 0; // El estado inicial es el 0
		for (int i = 0; i < simbolo.length; i++) {
			estado = transicion(estado, simbolo[i]);
		}
		return esFinal(estado);
	}

	public static AFD pedir() {
		AFD o = new AFD();
		
		return o;
	}
	
	public static AFD readFile() {
		AFD o = new AFD();
		
		return o;
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	public Object clone() {
		return null;
	}
}