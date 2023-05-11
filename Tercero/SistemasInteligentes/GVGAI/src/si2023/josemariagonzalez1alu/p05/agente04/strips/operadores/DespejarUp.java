package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class DespejarUp extends Operador<IPredicado> {

	private Posicion P;
	
	public DespejarUp(Posicion P) {
		super();
		this.P = P;
		
		precondiciones.add(new PAvatarEn(P.x, P.y + 1));
		//precondiciones.add(new PLibre(P.x, P.y - 1));
		
		adiciones.add(new PLibre(P));
		adiciones.add(new PAvatarEn(P));
		
		supresiones.add(new PAvatarEn(P.x, P.y + 1));
		supresiones.add(new PLibre(P.x, P.y - 1));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DespejarUp(" + P + ")";
	}

	@Override
	public ACTIONS getAction() {
		return ACTIONS.ACTION_UP;
	}

}
