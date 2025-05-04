package si2023.josemariagonzalez1alu.p01.agente89.motor;

import java.util.ArrayList;

import si2023.josemariagonzalez1alu.p01.agente89.motor.reglas.PosicionDefensiva;
import si2023.josemariagonzalez1alu.p01.agente89.motor.reglas.SalvarCivil;
import si2023.josemariagonzalez1alu.p01.agente89.motor.reglas.TerminarPartida;
import si2023.josemariagonzalez1alu.p01.agente89.motor.reglas.VaciarTripa;
import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;
import si2023.josemariagonzalez1alu.p01.ia.motor.Motor;
import si2023.josemariagonzalez1alu.p01.ia.motor.Regla;

public class Motor89 extends Motor {
	
	public Motor89(Mundo m) {
		super(m);
		
		reglas.add(new SalvarCivil());
		reglas.add(new VaciarTripa());
		reglas.add(new TerminarPartida());
		reglas.add(new PosicionDefensiva());
		
		disparadas = new ArrayList<>();
	}


	@Override
	public Regla resolucionConflicto() {
		if (disparadas.isEmpty())
			return null;
		else
			return disparadas.get(0);
	}
}
