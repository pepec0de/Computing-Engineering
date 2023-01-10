package amc.practica2.clases;

import java.util.ArrayList;
import java.util.List;

public class AFND implements Cloneable, IAutomata {

	private List<Estado> estados;
	private List<Estado> estadosFinales;
	private List<Transicion> transiciones;
	private List<TransicionL> transicionesL;

	public void agregarTransicion(Estado e1, char simbolo, List<Estado> e2) {
		
	}

	public void agregarTransicionL(Estado e1, List<Estado> e2) {
		
	}

	private List<Integer> transicion(int estado, char simbolo) {
		return null;
	}

	public List<Integer> transicion(List<Integer> macroestado, char simbolo) {
		return null;
	}
	
	public int[] transicionL(int estado) {
		
		return null;
	}

	@Override
	public boolean esFinal(int estado) {
		if (estado == -1)
			return false;
		
		for (int i = 0; i < estadosFinales.size(); i++) {
			if (estadosFinales.get(i).getId() == estado)
				return true;
		}
		return false;
	}

	public boolean esFinal(List<Integer> macroestado) {
		for (int est : macroestado) {
			if (esFinal(est))
				return true;
		}
		return false;
	}

	private List<Integer> L_clausura(List<Integer> macroestado) {
		for (int est : macroestado) {
			for (TransicionL t : transicionesL) {
				if (est == t.getEstadoDestino().getId()) {
					macroestado.add(t.getEstadoDestino().getId());
				}
			}
		}

		return macroestado;
	}

	@Override
	public boolean reconocer(String cadena) {
		char[] simbolo = cadena.toCharArray();
		List<Integer> estado = new ArrayList<>();// El estado inicial es el 0
		estado.add(0);
		List<Integer> macroestado = L_clausura(estado);
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
		AFND r = new AFND();
		r.estados = new ArrayList<>(estados);
		r.estadosFinales = new ArrayList<>(estadosFinales);
		r.transiciones = new ArrayList<>(transiciones);
		r.transicionesL = new ArrayList<>(transicionesL);
		return r;
	}
}
