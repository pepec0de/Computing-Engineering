package si2023.josemariagonzalez1alu.p01.agente89.motor.condiciones;

import java.util.ArrayList;
import java.util.List;

import core.game.Observation;
import si2023.josemariagonzalez1alu.p01.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p01.agente89.mente.Objeto;
import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;
import si2023.josemariagonzalez1alu.p01.ia.motor.Condicion;
import tools.Vector2d;

public class Cayendo implements Condicion {
	
	private Mundo89 mundo;
	private List<Vector2d> posPeligro;
	
	/*
	 * Condicion que se va a disparar si un civil esta cayendo
	 */
	
	public Cayendo(List<Vector2d> posPeligro) {
		this.posPeligro = posPeligro;
	}

	@Override
	public boolean seCumple(Mundo m) {
		mundo = (Mundo89) m;
		
		posPeligro.clear();
		ArrayList<Observation>[] movablePositions = mundo.getMundo().getMovablePositions();
		boolean civilCayendo = false;
		if (movablePositions != null) {
			for (ArrayList<Observation> arrObs : movablePositions) {
				for (Observation obs : arrObs) {
					if (obs.itype == Objeto.CAYENDO) {
						civilCayendo = true;
						posPeligro.add(obs.position);
					}
				}
				// Ya hemos iterado todos los posibles civiles cayendo
				if (civilCayendo)
					break;
			}
		}
		
		return civilCayendo;
	}

}
