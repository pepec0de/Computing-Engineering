package si2023.josemariagonzalez1alu.p00;

import java.util.ArrayList;
import java.util.Random;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

public class Agent extends AbstractPlayer {

	public Agent(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		if (arg0.getGameTick() == 0) {
			int bloque = arg0.getBlockSize();
			
			ArrayList<Observation>[][] grid = arg0.getObservationGrid();
			int filas = grid[0].length;
			int columnas = grid.length;
			String mapa = "";
			
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					ArrayList<Observation> arr = grid[j][i];
					if (arr.size() == 0) {
						mapa += " ";
					} else {
						boolean arbol = true;
						for (Observation obs : arr) {
							if (obs.itype == 1) {
								arbol = false;
								mapa += "J";
								break;
							}
							if (arg0.getAvatarType() == obs.category) {
								mapa += "S";
								arbol = false;
								break;
							}
							if (obs.category == 2) {
								mapa += "G";
								arbol = false;
								break;
							}
						}
						if (arbol) {
							mapa += "#";
						}
					}
				}
				mapa += "\n";
			}
			System.out.println("Filas: " + filas + "\nColumnas: " + columnas + "\nBloque: " + bloque + "\nDimension del mapa: " + bloque*columnas + " x " + bloque*filas);
			System.out.println(mapa);
		}
		return Types.ACTIONS.ACTION_NIL;
	}

}
