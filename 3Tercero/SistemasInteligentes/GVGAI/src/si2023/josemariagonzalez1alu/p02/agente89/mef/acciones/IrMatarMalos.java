package si2023.josemariagonzalez1alu.p02.agente89.mef.acciones;

import java.util.List;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Camino;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p02.ia.mef.Accion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;
import tools.Vector2d;

public class IrMatarMalos implements Accion {

	private Mundo89 mundo;
	private List<Vector2d> posEnemigos;
	
	public IrMatarMalos(List<Vector2d> posEnemigos) {
		this.posEnemigos = posEnemigos;
	}

	@Override
	public ACTIONS doAction(Mundo m) {
		if (posEnemigos.size() == 0)
			return ACTIONS.ACTION_NIL;
		
		mundo = (Mundo89) m;
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
