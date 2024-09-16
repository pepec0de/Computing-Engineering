package si2023.josemariagonzalez1alu.p03.agente89.ad.acciones;


import java.util.ArrayList;

import core.game.Observation;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Camino;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Objeto;
import si2023.josemariagonzalez1alu.p03.ia.ad.Accion;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;
import tools.Vector2d;

public class IrMatarMalos extends Accion {

	private Mundo89 mundo;
	private ArrayList<Vector2d> posEnemigos;
	
	public IrMatarMalos() {
		posEnemigos = new ArrayList<>();
	}

	@Override
	public ACTIONS doAction(Mundo m) {
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
		
		if (posEnemigos.size() == 0)
			return ACTIONS.ACTION_NIL;
		
		Vector2d avatarPos = mundo.getMundo().getAvatarPosition();

		// Encontrar el enemigo mas cercano
		Vector2d nearestEnemy = posEnemigos.get(0);
		for (int i = 1; i < posEnemigos.size(); i++) {
			if (nearestEnemy.dist(avatarPos) > posEnemigos.get(i).dist(avatarPos)) {
				nearestEnemy = posEnemigos.get(i);
			}
		}
		
		int xo = (int) (avatarPos.x / mundo.BLOQUE),
			yo = (int) (avatarPos.y / mundo.BLOQUE);
		
		int xf = (int) (nearestEnemy.x / mundo.BLOQUE),
			yf = (int) (nearestEnemy.y / mundo.BLOQUE);
		
		return Camino.DarPaso(xo, yo, xf, yf);
	}
	
}
