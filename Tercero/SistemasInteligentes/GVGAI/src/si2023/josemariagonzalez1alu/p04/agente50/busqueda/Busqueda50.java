package si2023.josemariagonzalez1alu.p04.agente50.busqueda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.agente50.mente.Mundo50;
import si2023.josemariagonzalez1alu.p04.agente50.mente.Objeto;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Busqueda;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Posicion;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;

public class Busqueda50 extends Busqueda {

	private Mundo50 m;
	private NodoBusqueda start;
	
	
	public Busqueda50(Mundo m) {
		this.m = (Mundo50) m;
	}
	
	public ArrayList<ACTIONS> pensar() {
		Posicion initPos = m.getAvatarPos();
		
		start = new NodoBusqueda(null, null, Objeto.SUELO, initPos.x, initPos.y);
		NodoBusqueda oligo = new NodoBusqueda(null, null, Objeto.SUELO, m.getOligoPos().x, m.getOligoPos().y);
		
		System.out.println("De oligo a Meta");
		Stack<ACTIONS> pasosMeta = calcStepsBusquedaAnchura(oligo, Objeto.META);
		
		System.out.println("De start a oligo");
		Stack<ACTIONS> pasosOligo = calcStepsBusquedaAnchura(start, Objeto.OLIGOELEMENTOS);
		
		ArrayList<ACTIONS> pasos = new ArrayList<>();
		
		while (!pasosOligo.isEmpty()) {
			pasos.add(pasosOligo.pop());
		}
		
		while (!pasosMeta.isEmpty()) {
			pasos.add(pasosMeta.pop());
		}
		
		return pasos;
		
	}
	
	public Stack<ACTIONS> calcStepsBusquedaAnchura(NodoBusqueda start, int objetivo) {
		Stack<ACTIONS> pasos = new Stack<>();
		ArrayList<NodoBusqueda> abiertos = new ArrayList<>();
		abiertos.add(start);
		HashSet<Posicion> checked = new HashSet<>();

		while (!abiertos.isEmpty()) {
			NodoBusqueda nodo = abiertos.remove(0);
			if (!checked.contains(new Posicion(nodo.x, nodo.y))) {
				checked.add(new Posicion(nodo.x, nodo.y));
				if (nodo.tipo == objetivo) {
					// Rescatamos los pasos
					NodoBusqueda nodoRecorrer = nodo;
					while (nodoRecorrer.padre != null) {
						pasos.push(nodoRecorrer.operador);
						nodoRecorrer = nodoRecorrer.padre;
					}
					return pasos;
				} else {	
					nodo.siguientes = calculoAccionesPosibles(nodo);
					if (nodo.siguientes != null) {
						for (NodoBusqueda n : nodo.siguientes) {
							abiertos.add(n);
						}
					}
				}
			}
		}
		return null;
	}
	
	public ArrayList<NodoBusqueda> calculoAccionesPosibles(NodoBusqueda padre) {
		ArrayList<NodoBusqueda> nodosPosibles = new ArrayList<>();
		if (m.movablePosition(padre.x, padre.y - 1))
			nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_UP, m.getTipo(padre.x, padre.y - 1), padre.x, padre.y - 1));
		
		if (m.movablePosition(padre.x, padre.y + 1))
			nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_DOWN, m.getTipo(padre.x, padre.y + 1), padre.x, padre.y + 1));
		
		if (m.movablePosition(padre.x - 1, padre.y))
			nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_LEFT, m.getTipo(padre.x - 1, padre.y), padre.x - 1, padre.y));
		
		if (m.movablePosition(padre.x + 1, padre.y))
			nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_RIGHT, m.getTipo(padre.x + 1, padre.y), padre.x + 1, padre.y));
		
		return nodosPosibles;
	}
}
