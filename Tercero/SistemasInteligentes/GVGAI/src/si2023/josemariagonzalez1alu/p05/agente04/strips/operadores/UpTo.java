package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class UpTo extends Operador<IPredicado> {

	public UpTo() {
		super();
	}

	public void setP(Posicion P) {
		precondiciones.add(new PLibre(P));
		precondiciones.add(new PAvatarEn(P.x + 1, P.y));
		
		adiciones.add(new PAvatarEn(P));
		
		supresiones.add(new PAvatarEn(P.x + 1, P.y));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "UpTo";
	}

}
