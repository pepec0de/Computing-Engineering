package si2023.josemariagonzalez1alu.p01.agente89.motor.acciones;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p01.agente89.mente.Camino;
import si2023.josemariagonzalez1alu.p01.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;
import si2023.josemariagonzalez1alu.p01.ia.motor.Accion;
import tools.Vector2d;

public class IrCarcel implements Accion {

	private Mundo89 mundo;
	
	public IrCarcel() {}

	@Override
	public ACTIONS doAction(Mundo m) {
		mundo = (Mundo89) m;
		Vector2d avatarPos = mundo.getMundo().getAvatarPosition();
		
		int xo = (int) (avatarPos.x / mundo.BLOQUE),
			yo = (int) (avatarPos.y / mundo.BLOQUE);
		
		int xf = (int) (mundo.getCarcelPos().x / mundo.BLOQUE),
			yf = (int) (mundo.getCarcelPos().y / mundo.BLOQUE);
		
		// Resolver hitbox de los bloques de ambas esquinas
		if (xo == 0 && yo == mundo.FILAS - 3) {
			return ACTIONS.ACTION_RIGHT;
		}
		if (xo == mundo.COLUMNAS - 1 && yo == mundo.FILAS - 3) {
			return ACTIONS.ACTION_LEFT;
		}
		return Camino.DarPaso(xo, yo, xf, yf);
	}

}
