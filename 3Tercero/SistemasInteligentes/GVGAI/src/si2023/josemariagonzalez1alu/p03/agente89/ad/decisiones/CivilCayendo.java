package si2023.josemariagonzalez1alu.p03.agente89.ad.decisiones;

import java.util.ArrayList;
import java.util.List;

import si2023.josemariagonzalez1alu.p03.agente89.ad.acciones.IrSalvarCivil;
import si2023.josemariagonzalez1alu.p03.agente89.ad.condiciones.HayCivilCayendo;
import si2023.josemariagonzalez1alu.p03.ia.ad.Decision;
import tools.Vector2d;

public class CivilCayendo extends Decision {

	private List<Vector2d> posPeligro;
	
	public CivilCayendo() {
		posPeligro = new ArrayList<>();
		pregunta = new HayCivilCayendo(posPeligro);
		nodoSi = new IrSalvarCivil(posPeligro);
		
		nodoNo = new HayCivil();
	}

}
