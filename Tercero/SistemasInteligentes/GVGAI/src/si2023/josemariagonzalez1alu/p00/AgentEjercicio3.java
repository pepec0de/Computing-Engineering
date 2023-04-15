package si2023.josemariagonzalez1alu.p00;

import java.util.ArrayList;
import java.util.Random;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

public class AgentEjercicio3 extends AbstractPlayer {
	Random r;
	public AgentEjercicio3(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		r = new Random(System.currentTimeMillis());
	}

	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		ArrayList<Observation>[][] grid = arg0.getObservationGrid();
		int filas = grid[0].length;
		int columnas = grid.length;
		int bloque = arg0.getBlockSize();
		String mapa = "";
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				ArrayList<Observation> arr = grid[j][i];
				if (arg0.getMovablePositions() != null)
					for (ArrayList<Observation> arrMove : arg0.getMovablePositions()) {
						if (arrMove.size() != 0) {
							mapa += "?";
						}
					}
				if (arr.size() == 0) {
					mapa += " ";
				} else {
					boolean arbol = true;
					for (Observation obs : arr) {
						if (obs.itype == 1) {
							arbol = false;
							mapa += "X";
							break;
						}
						if (obs.itype == 5) {
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
		
		switch (r.nextInt(4)) {
		case 0:
			return Types.ACTIONS.ACTION_UP;
			
		case 1:
			return Types.ACTIONS.ACTION_LEFT;
			
		case 2:
			return Types.ACTIONS.ACTION_RIGHT;
		
		case 3:
			return Types.ACTIONS.ACTION_DOWN; 
		}
		return Types.ACTIONS.ACTION_NIL;
	}
}
