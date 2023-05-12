package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Direccion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PRoca;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class RocaRight extends Operador<IPredicado> {

	private Posicion P;
	
	public RocaRight(Posicion P) {
		super();
		this.P = P;
		
		Posicion left = Posicion.getPosicion(P, Direccion.LEFT);
		Posicion right = Posicion.getPosicion(P, Direccion.RIGHT);
		precondiciones.add(new PAvatarEn(left));
		precondiciones.add(new PLibre(right));
		precondiciones.add(new PRoca(P));
		
		adiciones.add(new PLibre(P));
		adiciones.add(new PAvatarEn(P));
		adiciones.add(new PRoca(right));
		
		supresiones.add(new PAvatarEn(left));
		supresiones.add(new PLibre(right));
		supresiones.add(new PRoca(P));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RocaRight(" + P + ")";
	}

	@Override
	public ACTIONS getAction() {
		// TODO Auto-generated method stub
		return ACTIONS.ACTION_RIGHT;
	}

}
