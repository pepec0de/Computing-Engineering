package si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones;

import java.util.ArrayList;

import core.game.Observation;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Objeto;
import si2023.josemariagonzalez1alu.p02.ia.mef.Condicion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;

public class NoHayCiviles implements Condicion {

	private Mundo89 mundo;
	
	public NoHayCiviles() {}

	@Override
	public boolean seCumple(Mundo m) {
		mundo = (Mundo89) m;
		ArrayList<Observation>[] movablePositions = mundo.getMundo().getMovablePositions();
		boolean noHayCiviles = true;
		if (movablePositions != null) {
			for (ArrayList<Observation> arrObs : movablePositions) {
				for (Observation obs : arrObs) {
					if (obs.itype == Objeto.CIVIL) {
						noHayCiviles = false;
						break;
					}
				}
				if (!noHayCiviles)
					break;
			}
		}
		return noHayCiviles;
	}

}
