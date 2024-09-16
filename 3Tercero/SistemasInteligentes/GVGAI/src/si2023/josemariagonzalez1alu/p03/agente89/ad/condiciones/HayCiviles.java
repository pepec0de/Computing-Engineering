package si2023.josemariagonzalez1alu.p03.agente89.ad.condiciones;

import java.util.ArrayList;

import core.game.Observation;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Objeto;
import si2023.josemariagonzalez1alu.p03.ia.ad.Condicion;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;

public class HayCiviles implements Condicion {

	private Mundo89 mundo;
	
	public HayCiviles() {}

	@Override
	public boolean seCumple(Mundo m) {
		mundo = (Mundo89) m;
		ArrayList<Observation>[] movablePositions = mundo.getMundo().getMovablePositions();
		boolean hayCiviles = false;
		if (movablePositions != null) {
			for (ArrayList<Observation> arrObs : movablePositions) {
				for (Observation obs : arrObs) {
					if (obs.itype == Objeto.CIVIL) {
						hayCiviles = true;
						break;
					}
				}
				if (hayCiviles)
					break;
			}
		}
		return hayCiviles;
	}

}
