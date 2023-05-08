package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class DespejarDown extends Operador<IPredicado> {

	public DespejarDown() {
		super();
	}

	public void setB(Posicion B) {
		precondiciones.add(new PAvatarEn(B.x - 1, B.y));
		
		adiciones.add(new PLibre(B));
		adiciones.add(new PAvatarEn(B));
		
		supresiones.add(new PAvatarEn(B.x - 1, B.y));
		supresiones.add(new PLibre(B.x + 1, B.y));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DespejarDown";
	}

}
