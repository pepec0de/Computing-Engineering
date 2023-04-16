package si2023.josemariagonzalez1alu.p04.agente16.busqueda;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.agente16.mente.Mundo16;
import si2023.josemariagonzalez1alu.p04.agente16.mente.Objeto;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Busqueda;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Posicion;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;

public class Busqueda16 extends Busqueda {

	private Mundo16 m;
	private NodoBusqueda start;
	
	public Busqueda16(Mundo m) {
		this.m = (Mundo16) m;
	}
	
	public void pensar() {
		Posicion initPos = m.getVectorPosicion(m.getMundo().getAvatarPosition());
		start = new NodoBusqueda(m, Objeto.SUELO, initPos.x, initPos.y);
		
		/* TODO :
		 * + Mundo16.posMeta : Posicion
		 * funcion para sacar la posicion de la meta
		 * inicializar correctamente el nodo start y el nodo meta
		 * probar
		*/
		
	}
	
	public ArrayList<ACTIONS> calculoAccionesPosibles(int tipo, int x, int y) {
		switch (tipo) {
		case Objeto.SUELO:
			ArrayList<ACTIONS> acciones = new ArrayList<>();
			if (m.movablePosition(x, y - 1))
				acciones.add(ACTIONS.ACTION_UP);
			
			if (m.movablePosition(x, y + 1))
				acciones.add(ACTIONS.ACTION_DOWN);
			
			if (m.movablePosition(x - 1, y))
				acciones.add(ACTIONS.ACTION_LEFT);
			
			if (m.movablePosition(x + 1, y))
				acciones.add(ACTIONS.ACTION_RIGHT);
			
			return acciones;
			
		case Objeto.B_UP:
		case Objeto.B_DOWN:
		case Objeto.B_LEFT:
		case Objeto.B_RIGHT:
			Posicion pos = getBoosterFinalPos(tipo, x, y);
			if (pos != null)
				return calculoAccionesPosibles(Objeto.SUELO, pos.x, pos.y);
			return null;
		}
		return null;
	}

	// Funcion que calcula la posicion final a la que lleva un booster en x, y
	// Devolverá null si no hay posicion valida
	public Posicion getBoosterFinalPos(int tipo, int x, int y) {
		switch (tipo) {
		case Objeto.B_UP:
			for (int j = y; j >= 0; j--) {
				if (m.movablePosition(x, j)) {
					if (m.esSuelo(x, j)) {
						// Posicion valida
						return new Posicion(x, j);
					} else {
						// Posicion de otro booster por tanto se recalcula
						getBoosterFinalPos(m.getTipo(x, j), x, j);
					}					
					break;
				}
			}
			break;
			
		case Objeto.B_DOWN:
			for (int j = y; j < m.FILAS; j++) {
				if (m.movablePosition(x, j)) {
					if (m.esSuelo(x, j)) {
						// Posicion valida
						return new Posicion(x, j);
					} else {
						// Posicion de otro booster por tanto se recalcula
						getBoosterFinalPos(m.getTipo(x, j), x, j);
					}					
					break;
				}
			}
			break;
			
		case Objeto.B_LEFT:
			for (int i = x; i >= 0; i--) {
				if (m.movablePosition(i, y)) {
					if (m.esSuelo(i, y)) {
						// Posicion valida
						return new Posicion(i, y);
					} else {
						// Posicion de otro booster por tanto se recalcula
						getBoosterFinalPos(m.getTipo(i, y), i, y);
					}					
					break;
				}
			}
			break;
			
		case Objeto.B_RIGHT:
			for (int i = x; i < m.COLUMNAS; i++) {
				if (m.movablePosition(i, y)) {
					if (m.esSuelo(i, y)) {
						// Posicion valida
						return new Posicion(i, y);
					} else {
						// Posicion de otro booster por tanto se recalcula
						getBoosterFinalPos(m.getTipo(i, y), i, y);
					}					
					break;
				}
			}
			break;
		}
		return null;
	}
}
