package si2023.josemariagonzalez1alu.p02.agente89.mef.estados;

import si2023.josemariagonzalez1alu.p02.agente89.mef.acciones.IrDefensa;
import si2023.josemariagonzalez1alu.p02.ia.mef.Estado;

public class PosicionDefensiva extends Estado {
	
	public PosicionDefensiva() {
		super();
		accion = new IrDefensa();
	}

	
}
