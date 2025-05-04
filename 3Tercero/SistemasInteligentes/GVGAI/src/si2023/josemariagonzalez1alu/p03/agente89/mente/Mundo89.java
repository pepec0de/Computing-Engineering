package si2023.josemariagonzalez1alu.p03.agente89.mente;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;
import tools.Vector2d;

public class Mundo89 implements Mundo {
	
	public final int BLOQUE, COLUMNAS, FILAS;
	
	private StateObservation stateObs;
	private Vector2d carcelPos;
	
	private void findCarcel() {
		carcelPos = null;
		ArrayList<Observation>[][] grid = stateObs.getObservationGrid();
		
		for (int j = 0; j < FILAS && carcelPos == null; j++) {
			for (int i = 0; i < COLUMNAS && carcelPos == null; i++) {
				ArrayList<Observation> currObs = grid[i][j];
				for (Observation obs : currObs) {
					if (obs.itype == Objeto.CARCEL) {
						carcelPos = new Vector2d(obs.position);
						break;
					}
				}
			}
		}
	}
	
	public Mundo89(StateObservation so) {
		stateObs = so;
		BLOQUE = stateObs.getBlockSize();
		COLUMNAS = stateObs.getObservationGrid().length;
		FILAS = stateObs.getObservationGrid()[0].length;
		
		findCarcel();
	}

	@Override
	public void actualizarMundo(StateObservation so) {
		stateObs = so;
	}

	@Override
	public StateObservation getMundo() {
		return stateObs;
	}
	
	public Vector2d getCarcelPos() {
		return carcelPos;
	}

}
