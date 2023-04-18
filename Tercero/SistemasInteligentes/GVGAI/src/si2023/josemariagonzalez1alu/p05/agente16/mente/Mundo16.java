package si2023.josemariagonzalez1alu.p05.agente16.mente;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import si2023.josemariagonzalez1alu.p05.ia.busqueda.Posicion;
import si2023.josemariagonzalez1alu.p05.ia.mente.Mundo;
import tools.Vector2d;

public class Mundo16 implements Mundo {
	
	public final int BLOQUE, COLUMNAS, FILAS;
	private StateObservation stateObs;
	private Posicion posMeta;

	public Mundo16(StateObservation so) {
		stateObs = so;
		BLOQUE = stateObs.getBlockSize();
		COLUMNAS = stateObs.getObservationGrid().length;
		FILAS = stateObs.getObservationGrid()[0].length;
		
		buscarMeta();
	}

	private void buscarMeta() {
		ArrayList<Observation> grid[][] = stateObs.getObservationGrid();
		
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				for (Observation obs : grid[i][j]) {
					if (obs.itype == Objeto.META) {
						posMeta = getVectorPosicion(obs.position);
						return;
					}
					break;
				}
			}
		}
	}
	
	// Funcion booleana que devuelve si es posible desplazarse a otra posicion sin perder
	public boolean movablePosition(int x, int y) {
		ArrayList<Observation> arr = stateObs.getObservationGrid()[x][y];
		if (arr.size() == 0) {
			return true;
		} else {
			switch (arr.get(0).itype) {
			case Objeto.B_UP:
			case Objeto.B_DOWN:
			case Objeto.B_LEFT:
			case Objeto.B_RIGHT:
			case Objeto.META:
				return true;
			}
		}
		return false;
	}
	
	public boolean isWall(int x, int y) {
		if ( (x < 0 || x >= COLUMNAS) || (y < 0 || y >= FILAS))
			return true;
		
		ArrayList<Observation> arr = stateObs.getObservationGrid()[x][y];
		if (arr.isEmpty())
			return false;
		
		return arr.get(0).itype == Objeto.PARED;
	}
	
	public boolean isBooster(int x, int y) {
		ArrayList<Observation> arr = stateObs.getObservationGrid()[x][y];
		if (!arr.isEmpty()) {
			switch (arr.get(0).itype) {
			case Objeto.B_UP:
			case Objeto.B_DOWN:
			case Objeto.B_LEFT:
			case Objeto.B_RIGHT:
				return true;
			}
		}
		return false;
	}
	
	public int getTipo(int x, int y) {
		ArrayList<Observation> arr = stateObs.getObservationGrid()[x][y];
		if (arr.size() == 0)
			return Objeto.SUELO;
		
		return arr.get(0).itype;
	}
	
	public boolean esSuelo(int x, int y) {
		return stateObs.getObservationGrid()[x][y].size() == 0;
	}
	
	public Posicion getVectorPosicion(Vector2d v) {
		return new Posicion((int) (v.x / BLOQUE), (int) (v.y / BLOQUE));
	}
	
	public Posicion getAvatarPos() {
		return getVectorPosicion(stateObs.getAvatarPosition());
	}
	
	public Posicion getMetaPos() {
		return posMeta;
	}
	
	@Override
	public void actualizarMundo(StateObservation so) {
		stateObs = so;
	}

	@Override
	public StateObservation getMundo() {
		return stateObs;
	}
	
	public void imprimir() {
		ArrayList<Observation>[][] grid = stateObs.getObservationGrid();
		
		String mapa = "";
		
		for (int j = 0; j < FILAS; j++) {
			for (int i = 0; i < COLUMNAS; i++) {
				ArrayList<Observation> arr = grid[i][j];
				if (arr.size() == 0) {
					mapa += " ";
				} else {
					for (Observation obs : arr) {
						switch (obs.itype) {
						case 0:
							mapa += "#";
							break;
							
						case 15: // Meta
							mapa += "M";
							break;
							
						case 7: // DERECHA
							mapa += ">";
							break;
						
						case 8: // IZQUIERDA
							mapa += "<";
							break;
						
						case 6: // ARRIBA
							mapa += "^";
							break;
							
						case 5: // ABAJO
							mapa += "|";
							break;
						
						case 3: // AGUA
							mapa += ".";
							break;
							default:
								mapa += obs.itype;
						}
						
						break;
					}
				}
			}
			mapa += "\n";
		}
		
		System.out.println("FILAS: " + FILAS + "\nCOLUMNAS: " + COLUMNAS + "\n" + mapa);
	}
}
