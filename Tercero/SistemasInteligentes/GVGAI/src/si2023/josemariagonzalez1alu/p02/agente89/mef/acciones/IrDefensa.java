package si2023.josemariagonzalez1alu.p02.agente89.mef.acciones;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Camino;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p02.ia.mef.Accion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;
import tools.Vector2d;

public class IrDefensa implements Accion {

	private Mundo89 mundo;
	
	public IrDefensa() {}

	@Override
	public ACTIONS doAction(Mundo m) {
		mundo = (Mundo89) m;
		
		Vector2d avatarPos = mundo.getMundo().getAvatarPosition();
		
		int xo = (int) (avatarPos.x / mundo.BLOQUE),
			yo = (int) (avatarPos.y / mundo.BLOQUE);
		
		int xf = (int) (mundo.COLUMNAS / 2),
			yf = (int) (mundo.FILAS / 2);
		
		return Camino.DarPaso(xo, yo, xf, yf);
	}
	
	/*public ACTIONS doAction1(Mundo m) {
		mundo = (Mundo89) m;
		
		// Buscamos el civil que este situado mas abajo
		ArrayList<Observation>[] movablePositions = mundo.getMundo().getMovablePositions();
		ArrayList<Vector2d> ultimosCiviles = new ArrayList<>();
		
		double x = -1, y, maxY = -1, maxX = -1, minX = Double.MAX_VALUE;
		Vector2d posDefensa = null;
		if (movablePositions != null) {
			for (ArrayList<Observation> arrObs : movablePositions) {
				for (Observation obs : arrObs) {
					if (obs.itype == Objeto.CIVIL) {
						y = obs.position.y;
						if (y > maxY) {
							maxY = y;
							posDefensa = obs.position;
						} else if (y == maxY) {
							x = obs.position.x;
							if (x > maxX) {
								maxX = x;
							} else
							if (x < minX) {
								minX = x;
							}
						}
					}
				}
			}
			System.out.println("maxX: " + (int)(maxX / mundo.BLOQUE) + ", minX: " + (int)(minX / mundo.BLOQUE));
		}
		
		if (posDefensa != null) {
			Vector2d avatarPos = mundo.getMundo().getAvatarPosition();
			
			int xo = (int) (avatarPos.x / mundo.BLOQUE),
				yo = (int) (avatarPos.y / mundo.BLOQUE);
			
			if (x == -1)
				x = posDefensa.x;
			else {
				x = ((maxX - minX) / 2) + minX;
			}
			
			int xf = (int) (x / mundo.BLOQUE),
				yf = (int) (posDefensa.y / mundo.BLOQUE);
			
			return Camino.DarPaso(xo, yo, xf, yf);
		}
		return ACTIONS.ACTION_NIL;
	}*/

}
