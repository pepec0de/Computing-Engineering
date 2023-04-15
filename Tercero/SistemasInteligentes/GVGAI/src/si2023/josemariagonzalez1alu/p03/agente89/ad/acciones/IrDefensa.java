package si2023.josemariagonzalez1alu.p03.agente89.ad.acciones;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Camino;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p03.ia.ad.Accion;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;
import tools.Vector2d;

public class IrDefensa extends Accion {

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

}
