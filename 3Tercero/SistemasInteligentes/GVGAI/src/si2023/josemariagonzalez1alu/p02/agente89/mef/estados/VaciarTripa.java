package si2023.josemariagonzalez1alu.p02.agente89.mef.estados;

import si2023.josemariagonzalez1alu.p02.agente89.mef.acciones.IrCarcel;
import si2023.josemariagonzalez1alu.p02.ia.mef.Estado;

public class VaciarTripa extends Estado {

	public VaciarTripa() {
		super();
		accion = new IrCarcel();
	}

}
