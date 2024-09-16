package si2023.josemariagonzalez1alu.p01.agente89.motor.reglas;

import si2023.josemariagonzalez1alu.p01.agente89.motor.acciones.IrCarcel;
import si2023.josemariagonzalez1alu.p01.agente89.motor.condiciones.TripaLlena;
import si2023.josemariagonzalez1alu.p01.ia.motor.Regla;

public class VaciarTripa extends Regla {

	public VaciarTripa() {
		antecedentes.add(new TripaLlena());
		
		accion = new IrCarcel();
	}

}
