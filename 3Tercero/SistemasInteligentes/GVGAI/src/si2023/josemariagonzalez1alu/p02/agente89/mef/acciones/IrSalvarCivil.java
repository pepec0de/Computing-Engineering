package si2023.josemariagonzalez1alu.p02.agente89.mef.acciones;

import java.util.List;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Camino;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p02.ia.mef.Accion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;
import tools.Vector2d;

public class IrSalvarCivil implements Accion {

	private Mundo89 mundo;
	private List<Vector2d> posPeligro;
	
	public IrSalvarCivil(List<Vector2d> posPeligro) {
		this.posPeligro = posPeligro;
	}

	@Override
	public ACTIONS doAction(Mundo m) {
		mundo = (Mundo89) m;
		Vector2d avatarPos = mundo.getMundo().getAvatarPosition();
		int xo = (int) (avatarPos.x / mundo.BLOQUE),
			yo = (int) (avatarPos.y / mundo.BLOQUE);
		
		// Buscar el civil que este mas abajo iterando sobre la y de posPeligro
		double maxY = posPeligro.get(0).y;
		int idx = 0;
		for (int i = 1; i < posPeligro.size(); i++) {
			if (maxY < posPeligro.get(i).y) {
				maxY = posPeligro.get(i).y;
				idx = i;
			}
		}
		
		int xf = (int) (posPeligro.get(idx).x / mundo.BLOQUE);
		int yf = (int) (posPeligro.get(idx).y / mundo.BLOQUE);
		
		return Camino.DarPaso(xo, yo, xf, yf);
	}

}
