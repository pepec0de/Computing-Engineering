package si2023.josemariagonzalez1alu.p04.agente16.mente;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;

public class Mundo16 implements Mundo {
	
	public final int BLOQUE, COLUMNAS, FILAS;
	
	private StateObservation stateObs;

	public Mundo16(StateObservation so) {
		stateObs = so;
		BLOQUE = stateObs.getBlockSize();
		COLUMNAS = stateObs.getObservationGrid().length;
		FILAS = stateObs.getObservationGrid()[0].length;
		
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
		System.out.println(mapa);
	}
}
