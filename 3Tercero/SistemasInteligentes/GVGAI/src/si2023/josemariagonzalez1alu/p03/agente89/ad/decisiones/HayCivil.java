package si2023.josemariagonzalez1alu.p03.agente89.ad.decisiones;

import si2023.josemariagonzalez1alu.p03.agente89.ad.acciones.IrDefensa;
import si2023.josemariagonzalez1alu.p03.agente89.ad.condiciones.HayCiviles;
import si2023.josemariagonzalez1alu.p03.ia.ad.Decision;

public class HayCivil extends Decision {

	public HayCivil() {
		pregunta = new HayCiviles();
		
		nodoSi = new IrDefensa();
		
		nodoNo = new PuedoMatar();
	}


}
