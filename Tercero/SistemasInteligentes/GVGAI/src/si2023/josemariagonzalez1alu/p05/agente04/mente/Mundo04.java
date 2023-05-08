package si2023.josemariagonzalez1alu.p05.agente04.mente;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.ia.mente.Mundo;
import tools.Vector2d;

public class Mundo04 implements Mundo {

	public final int BLOQUE, COLUMNAS, FILAS;
	private StateObservation stateObs;
	private Posicion keyPos, metaPos;

	public Mundo04(StateObservation so) {
		stateObs = so;
		BLOQUE = stateObs.getBlockSize();
		COLUMNAS = stateObs.getObservationGrid().length;
		FILAS = stateObs.getObservationGrid()[0].length;
		
		keyPos = findLlavePos();
		metaPos = findMetaPos();
	}
	
	private Posicion findLlavePos() {
		ArrayList<Observation> grid[][] = stateObs.getObservationGrid();
		for (int j = 0; j < FILAS; j++) {
			for (int i = 0; i < COLUMNAS; i++) {
				ArrayList<Observation> arr = grid[i][j];
				for (Observation obs : arr)
					if (obs.itype == Objeto.LLAVE) {
						return new Posicion(i, j);
					}
			}
		}
		return new Posicion(0, 0);
	}
	
	private Posicion findMetaPos() {
		ArrayList<Observation> grid[][] = stateObs.getObservationGrid();
		for (int j = 0; j < FILAS; j++) {
			for (int i = 0; i < COLUMNAS; i++) {
				ArrayList<Observation> arr = grid[i][j];
				for (Observation obs : arr)
					if (obs.itype == Objeto.META) {
						return new Posicion(i, j);
					}
			}
		}
		return new Posicion(0, 0);
	}
	
	public boolean canMoveTo(int x, int y) {
		
		switch (getTipo(x, y)) {
		//case Objeto.BLOQUE: Nos podemos mover a la posicion del bloque pero esa casilla en realidad está "ocupada"
		case Objeto.PLAYER:
		case Objeto.LLAVE:
		case Objeto.META:
		case Objeto.SUELO:
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Posicion> findPosicionesLibres() {
		ArrayList<Posicion> posList = new ArrayList<>();
		for (int j = 0; j < FILAS; j++) {
			for (int i = 0; i < COLUMNAS; i++) {
				if (canMoveTo(i, j)) {
					posList.add(new Posicion(i, j));
				}
			}
		}
		return posList;
	}
	
	public boolean isWall(int x, int y) {
		if ( (x < 0 || x >= COLUMNAS) || (y < 0 || y >= FILAS))
			return true;
		
		ArrayList<Observation> arr = stateObs.getObservationGrid()[x][y];
		if (arr.isEmpty())
			return false;
		
		return arr.get(0).itype == Objeto.PARED;
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
	
	public Posicion getKeyPos() {
		return keyPos;
	}
	
	public Posicion getMetaPos() {
		return metaPos;
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
						case Objeto.PARED:
							mapa += "#";
							break;
						
						case Objeto.LLAVE:
							mapa += "K";
							break;
							
						case Objeto.BLOQUE:
							mapa += "B";
							break;
							
						case Objeto.META:
							mapa += "M";
							break;
							
						case Objeto.FOSO:
							mapa += "O";
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
