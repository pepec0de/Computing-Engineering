package si2023.josemariagonzalez1alu.p01.agente89.motor.reglas;

import java.util.ArrayList;
import java.util.List;

import si2023.josemariagonzalez1alu.p01.agente89.motor.acciones.IrSalvarCivil;
import si2023.josemariagonzalez1alu.p01.agente89.motor.condiciones.Cayendo;
import si2023.josemariagonzalez1alu.p01.ia.motor.Regla;
import tools.Vector2d;

public class SalvarCivil extends Regla {

	private List<Vector2d> posPeligro;
	
	public SalvarCivil() {
		super();
		
		posPeligro = new ArrayList<>();
		antecedentes.add(new Cayendo(posPeligro));
		
		accion = new IrSalvarCivil(posPeligro);
	}
}
