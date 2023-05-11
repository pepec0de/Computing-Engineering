package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class DespejarRight extends Operador<IPredicado> {

	private Posicion P;
	
	public DespejarRight(Posicion P) {
		super();
		this.P = P;
		
		precondiciones.add(new PAvatarEn(P.x - 1, P.y));
		//precondiciones.add(new PLibre(P.x + 1, P.y));
		
		adiciones.add(new PLibre(P));
		adiciones.add(new PAvatarEn(P));
		
		supresiones.add(new PAvatarEn(P.x - 1, P.y));
		supresiones.add(new PLibre(P.x + 1, P.y));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DespejarRight(" + P + ")";
	}

	@Override
	public ACTIONS getAction() {
		// TODO Auto-generated method stub
		return ACTIONS.ACTION_RIGHT;
	}

}
