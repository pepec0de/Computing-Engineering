package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class DespejarLeft extends Operador<IPredicado> {

	public DespejarLeft() {
		super();
	}

	public void setB(Posicion B) {
		precondiciones.add(new PAvatarEn(B.x, B.y + 1));
		
		adiciones.add(new PLibre(B));
		adiciones.add(new PAvatarEn(B));
		
		supresiones.add(new PAvatarEn(B.x, B.y + 1));
		supresiones.add(new PLibre(B.x, B.y - 1));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DespejarLeft";
	}

}
