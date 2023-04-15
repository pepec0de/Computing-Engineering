package si2023.josemariagonzalez1alu.p01.agente89.motor.reglas;

import java.util.ArrayList;
import java.util.List;

import si2023.josemariagonzalez1alu.p01.agente89.motor.acciones.IrMatarMalos;
import si2023.josemariagonzalez1alu.p01.agente89.motor.condiciones.HayMalos;
import si2023.josemariagonzalez1alu.p01.agente89.motor.condiciones.NoHayCiviles;
import si2023.josemariagonzalez1alu.p01.ia.motor.Regla;
import tools.Vector2d;

public class TerminarPartida extends Regla {
	
	private List<Vector2d> posEnemigos;
	
	public TerminarPartida() {
		super();
		posEnemigos = new ArrayList<>();
		antecedentes.add(new HayMalos(posEnemigos));
		antecedentes.add(new NoHayCiviles());
		
		accion = new IrMatarMalos(posEnemigos);
	}

}
