package si2023.josemariagonzalez1alu.p01.agente89;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

public class Agente89_Pintar extends AbstractPlayer{
	
	public Agente89_Pintar(StateObservation arg0, ElapsedCpuTimer arg1) {}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		StateObservation stateObs = arg0;
		ArrayList<Observation>[][] grid = stateObs.getObservationGrid();
		int filas = grid[0].length;
		int columnas = grid.length;
		int bloque = stateObs.getBlockSize();
		System.out.println("Filas: " + filas + "\nColumnas: " + columnas + "\nBloque: " + bloque + "\nDimension del mapa: " + bloque*columnas + " x " + bloque*filas);
		String mapa = "";
		
		for (int j = 0; j < filas; j++) {
			for (int i = 0; i < columnas; i++) {
				ArrayList<Observation> arr = grid[i][j];
				if (arr.size() == 0) {
					mapa += " ";
				} else {
					for (Observation obs : arr) {
						switch (obs.itype) {
						case 11:
						case 12: // Enemigo
							mapa += "E";
							break;
							
						case 16: // Civiles
						case 18: // Civiles cayendo
							mapa += "B";
							break;
						
						case 3: // Nubes
							mapa += "N";
							break;
						
						case 0:
						case 4:
						case 8:
							mapa += "#";
							break;
							
						case 9:
							mapa += "X";
							break;
							
						case 6:
							mapa += "J";
							break;
						}
						break;
					}
				}
			}
			mapa += "\n";
		}
		System.out.println(mapa);
		
		return ACTIONS.ACTION_NIL;
	}

}

