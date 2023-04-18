package si2023.josemariagonzalez1alu.p04.agente50.mente;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import si2023.josemariagonzalez1alu.p04.ia.busqueda.Posicion;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;
import tools.Vector2d;

public class Mundo50 implements Mundo {
	
	public final int BLOQUE, COLUMNAS, FILAS;
	private StateObservation stateObs;
	private Posicion oligoPos;

	public Mundo50(StateObservation so) {
		stateObs = so;
		BLOQUE = stateObs.getBlockSize();
		COLUMNAS = stateObs.getObservationGrid().length;
		FILAS = stateObs.getObservationGrid()[0].length;
	
		oligoPos = buscarOligoPos();
	}
	
	private Posicion buscarOligoPos() {
		ArrayList<Observation> grid[][] = stateObs.getObservationGrid();
		for (int j = 0; j < FILAS; j++) {
			for (int i = 0; i < COLUMNAS; i++) {
				ArrayList<Observation> arr = grid[i][j];
				for (Observation obs : arr)
					if (obs.itype == Objeto.OLIGOELEMENTOS) {
						return new Posicion(i, j);
					}
			}
		}
		return null;
	}
	
	// Funcion booleana que devuelve si es posible desplazarse a otra posicion sin perder
	public boolean movablePosition(int x, int y) {
		ArrayList<Observation> arr = stateObs.getObservationGrid()[x][y];
		if (arr.isEmpty())
			return true;
			
		switch (arr.get(0).itype) {
		case Objeto.META:
			return true;
			
		case Objeto.OLIGOELEMENTOS:
			return true;
			
		default:
			return false;
		}
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
	
	// Posicion de los oligoelementos
	public Posicion getOligoPos() {
		return oligoPos;
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
						
						case 3:
							mapa += "S";
							break;
							
						case 4:
							mapa += "M";
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
