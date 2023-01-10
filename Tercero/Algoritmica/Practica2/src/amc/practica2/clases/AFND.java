package amc.practica2.clases;

import java.util.List;

public class AFND implements Cloneable, IAutomata {

	private List<Estado> estados;
	private List<Estado> estadosFinales;

	public void agregarTransicion(int e1, char simbolo, int[] e2) {
		
	}

	public void agregarTransicionL(int e1, int[] e2) {
		
	}

	private int[] transicion(int estado, char simbolo) {
		return null;
	}

	public int[] transicion(int[] macroestado, char simbolo) {
		return null;
	}
	
	public int[] transicionL(int estado) {
		return null;
	}

	@Override
	public boolean esFinal(int estado) {
		return false;
		
	}

	public boolean esFinal(int[] macroestado) {
		return false;
	}

	private int[] L_clausura(int[] macroestado) {
		
		return null;
	}

	@Override
	public boolean reconocer(String cadena) {
		char[] simbolo = cadena.toCharArray();
		int[] estado = { 0 }; // El estado inicial es el 0
		int[] macroestado = L_clausura(estado);
		for (int i = 0; i < simbolo.length; i++) {
			macroestado = transicion(macroestado, simbolo[i]);
		}
		return esFinal(macroestado);
	}

	@Override
	public String toString() {
		return null;
	}

	public Object clone() {
		return null;
	}
}
