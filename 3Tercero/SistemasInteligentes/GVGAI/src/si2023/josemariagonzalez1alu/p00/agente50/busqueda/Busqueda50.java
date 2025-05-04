package si2023.josemariagonzalez1alu.p00.agente50.busqueda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p00.agente50.mente.Mundo50;
import si2023.josemariagonzalez1alu.p00.agente50.mente.Objeto;
import si2023.josemariagonzalez1alu.p00.ia.busqueda.Busqueda;
import si2023.josemariagonzalez1alu.p00.ia.busqueda.Nodo;
import si2023.josemariagonzalez1alu.p00.ia.busqueda.Posicion;
import si2023.josemariagonzalez1alu.p00.ia.mente.Mundo;

public class Busqueda50 extends Busqueda {
	
	private Mundo50 m;
	
	public Busqueda50(Mundo mundo) {
		super(mundo);
		this.m = (Mundo50) mundo;
	}
	
	public Stack<ACTIONS> pensar() {
		Posicion initPos = m.getAvatarPos();
		
		raiz = new Nodo50(mundo, null, null, Objeto.SUELO, initPos);
		
		Stack<ACTIONS> pasos = calcStepsBusquedaAnchura(raiz, Objeto.META);
		
		return pasos;
	}
	
	public Stack<ACTIONS> calcStepsBusquedaAnchura(Nodo ini, int objetivo) {
		Stack<ACTIONS> pasos = new Stack<>();
		ArrayList<Nodo> abiertos = new ArrayList<>();
		abiertos.add(ini);
		HashSet<Posicion> checked = new HashSet<>();

		while (!abiertos.isEmpty()) {
			Nodo nodo = abiertos.remove(0);
			if (!checked.contains(nodo.posicion)) {
				checked.add(nodo.posicion);
				if (nodo.tipo == objetivo) {
					// Rescatamos los pasos
					Nodo nodoRecorrer = nodo;
					while (nodoRecorrer.padre != null) {
						pasos.push(nodoRecorrer.operador);
						nodoRecorrer = nodoRecorrer.padre;
					}
					return pasos;
				} else {	
					nodo.calculaSucesores();
					for (Nodo n : nodo.sucesores) {
						abiertos.add(n);
					}
				}
			}
		}
		return null;
	}
	
}
