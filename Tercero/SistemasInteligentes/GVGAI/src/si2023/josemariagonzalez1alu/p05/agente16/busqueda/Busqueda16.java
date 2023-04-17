package si2023.josemariagonzalez1alu.p05.agente16.busqueda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente16.mente.Mundo16;
import si2023.josemariagonzalez1alu.p05.agente16.mente.Objeto;
import si2023.josemariagonzalez1alu.p05.ia.busqueda.Busqueda;
import si2023.josemariagonzalez1alu.p05.ia.busqueda.Posicion;
import si2023.josemariagonzalez1alu.p05.ia.mente.Mundo;

public class Busqueda16 extends Busqueda {

	private Mundo16 m;
	private NodoBusqueda start, meta;
	private HashSet<Posicion> checked;
	
	public Busqueda16(Mundo m) {
		this.m = (Mundo16) m;
	}
	
	public Stack<ACTIONS> pensar() {
		Posicion initPos = m.getAvatarPos();
		checked = new HashSet<>();
		
		start = new NodoBusqueda(null, null, Objeto.SUELO, initPos.x, initPos.y);
		
		Stack<ACTIONS> pasos = new Stack<>();
		expandirArbolDeBusqueda(pasos, start);
		
//		System.out.println("\n\nRESULTADO FINAL\n");
		
		while (!pasos.isEmpty()) {
			System.out.println(pasos.pop().toString());
		}
		System.out.println();
		return pasos;
	}
	
	public void expandirArbolDeBusqueda(Stack<ACTIONS> pasos, NodoBusqueda nodo) {
//		for (Posicion pos : checked) {
//			System.out.println(pos.x + ", " + pos.y);
//		}
		if (!checked.contains(new Posicion(nodo.x, nodo.y))) {
			checked.add(new Posicion(nodo.x, nodo.y));
			if (nodo.tipo == Objeto.META) {
				// Rescatamos los pasos
				NodoBusqueda nodoRecorrer = nodo;
				while (nodoRecorrer.padre != null) {
					pasos.push(nodoRecorrer.operador);
					nodoRecorrer = nodoRecorrer.padre;
				}
			} else {
				if (nodo.operador != null) {
					System.out.println(nodo.operador.toString() + " : " + nodo.x + ", " + nodo.y);
//					try {
//						System.in.read();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
				
				nodo.siguientes = calculoAccionesPosibles(nodo);
				if (nodo.siguientes != null) {
					for (NodoBusqueda n : nodo.siguientes) {
						expandirArbolDeBusqueda(pasos, n);
						if (pasos.size() > 0)
							break;
					}
				}
			}
		}
	}
	
	public ArrayList<NodoBusqueda> calculoAccionesPosibles(NodoBusqueda padre) {
		switch (padre.tipo) {
		case Objeto.SUELO:
			ArrayList<NodoBusqueda> nodosPosibles = new ArrayList<>();
			if (m.movablePosition(padre.x, padre.y - 1) && !checked.contains(new Posicion(padre.x, padre.y - 1)))
				nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_UP, m.getTipo(padre.x, padre.y - 1), padre.x, padre.y - 1));
			
			if (m.movablePosition(padre.x, padre.y + 1) && !checked.contains(new Posicion(padre.x, padre.y + 1)))
				nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_DOWN, m.getTipo(padre.x, padre.y + 1), padre.x, padre.y + 1));
			
			if (m.movablePosition(padre.x - 1, padre.y) && !checked.contains(new Posicion(padre.x - 1, padre.y)))
				nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_LEFT, m.getTipo(padre.x - 1, padre.y), padre.x - 1, padre.y));
			
			if (m.movablePosition(padre.x + 1, padre.y) && !checked.contains(new Posicion(padre.x + 1, padre.y)))
				nodosPosibles.add(new NodoBusqueda(padre, ACTIONS.ACTION_RIGHT, m.getTipo(padre.x + 1, padre.y), padre.x + 1, padre.y));
			
			return nodosPosibles;
			
		case Objeto.B_UP:
		case Objeto.B_DOWN:
		case Objeto.B_LEFT:
		case Objeto.B_RIGHT:
			Posicion pos = getBoosterFinalPos(padre.tipo, padre.x, padre.y);
			if (pos != null) {
				// La posicion calculada es valida
				padre.x = pos.x;
				padre.y = pos.y;
				padre.tipo = Objeto.SUELO;
				return calculoAccionesPosibles(padre);
			}
			return null;
		}
		return null;
	}

	// Funcion que calcula la posicion final a la que lleva un booster en x, y
	// Devolverá null si no hay posicion valida
	public Posicion getBoosterFinalPos(int tipo, int x, int y) {
		switch (tipo) {
		case Objeto.B_UP:
			for (int j = y - 1; j >= 0; j--) {
				if (m.movablePosition(x, j)) {
					if (m.esSuelo(x, j)) {
						// Posicion valida
						return new Posicion(x, j);
					} else {
						// Posicion de otro booster por tanto se recalcula
						return getBoosterFinalPos(m.getTipo(x, j), x, j);
					}					
				}
			}
			break;
			
		case Objeto.B_DOWN:
			for (int j = y + 1; j < m.FILAS; j++) {
				if (m.movablePosition(x, j)) {
					if (m.esSuelo(x, j)) {
						// Posicion valida
						return new Posicion(x, j);
					} else {
						// Posicion de otro booster por tanto se recalcula
						return getBoosterFinalPos(m.getTipo(x, j), x, j);
					}					
				}
			}
			break;
			
		case Objeto.B_LEFT:
			for (int i = x - 1; i >= 0; i--) {
				if (m.movablePosition(i, y)) {
					if (m.esSuelo(i, y)) {
						// Posicion valida
						return new Posicion(i, y);
					} else {
						// Posicion de otro booster por tanto se recalcula
						return getBoosterFinalPos(m.getTipo(i, y), i, y);
					}					
				}
			}
			break;
			
		case Objeto.B_RIGHT:
			for (int i = x + 1; i < m.COLUMNAS; i++) {
				if (m.movablePosition(i, y)) {
					if (m.esSuelo(i, y)) {
						// Posicion valida
						return new Posicion(i, y);
					} else {
						// Posicion de otro booster por tanto se recalcula
						return getBoosterFinalPos(m.getTipo(i, y), i, y);
					}					
				}
			}
			break;
		}
		return null;
	}
}
