package si2023.josemariagonzalez1alu.p01.agente89.motor.condiciones;

import java.util.ArrayList;
import java.util.List;

import core.game.Observation;
import si2023.josemariagonzalez1alu.p01.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p01.agente89.mente.Objeto;
import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;
import si2023.josemariagonzalez1alu.p01.ia.motor.Condicion;
import tools.Vector2d;

public class HayMalos implements Condicion {

	private Mundo89 mundo;
	private List<Vector2d> posEnemigos;
	
	public HayMalos(List<Vector2d> posEnemigos) {
		this.posEnemigos = posEnemigos;
	}


	@Override
	public boolean seCumple(Mundo m) {
		mundo = (Mundo89) m;
		posEnemigos.clear();
		// Usamos getNPCPositions
		
		ArrayList<Observation>[] npcPos = mundo.getMundo().getNPCPositions();
		if (npcPos != null) {
			for (ArrayList<Observation> arrObs : npcPos) {
				for (Observation obs : arrObs) {
					if (obs.category == Objeto.ENEMIGO) {
						posEnemigos.add(obs.position);
					}
				}
			}
		}
		
		return !posEnemigos.isEmpty();
	}

}
