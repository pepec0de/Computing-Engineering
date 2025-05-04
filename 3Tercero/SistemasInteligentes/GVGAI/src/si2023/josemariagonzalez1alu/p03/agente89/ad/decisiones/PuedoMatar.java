package si2023.josemariagonzalez1alu.p03.agente89.ad.decisiones;

import si2023.josemariagonzalez1alu.p03.agente89.ad.acciones.IrCarcel;
import si2023.josemariagonzalez1alu.p03.agente89.ad.acciones.IrMatarMalos;
import si2023.josemariagonzalez1alu.p03.agente89.ad.condiciones.TripaNoLlena;
import si2023.josemariagonzalez1alu.p03.ia.ad.Decision;

public class PuedoMatar extends Decision {

	public PuedoMatar() {
		pregunta = new TripaNoLlena();
		
		nodoSi = new IrMatarMalos();
		nodoNo = new IrCarcel();
	}



}
