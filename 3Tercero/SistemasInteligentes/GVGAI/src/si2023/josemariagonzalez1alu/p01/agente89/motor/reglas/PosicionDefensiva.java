package si2023.josemariagonzalez1alu.p01.agente89.motor.reglas;

import si2023.josemariagonzalez1alu.p01.agente89.motor.acciones.IrDefensa;
import si2023.josemariagonzalez1alu.p01.ia.motor.Regla;

public class PosicionDefensiva extends Regla {
	
	public PosicionDefensiva() {
		super();
		accion = new IrDefensa();
	}

}
