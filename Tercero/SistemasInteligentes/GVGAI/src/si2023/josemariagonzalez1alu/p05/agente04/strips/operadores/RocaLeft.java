package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Direccion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PRoca;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class RocaLeft extends Operador<IPredicado> {

	private Posicion P;
	
	public RocaLeft(Posicion P) {
		super();
		this.P = P;
		
		Posicion right = Posicion.getPosicion(P, Direccion.RIGHT);
		Posicion left = Posicion.getPosicion(P, Direccion.LEFT);
		precondiciones.add(new PAvatarEn(right));
		precondiciones.add(new PLibre(left));
		precondiciones.add(new PRoca(P));
		
		adiciones.add(new PLibre(P));
		adiciones.add(new PAvatarEn(P));
		adiciones.add(new PRoca(left));
		
		supresiones.add(new PAvatarEn(right));
		supresiones.add(new PLibre(left));
		supresiones.add(new PRoca(P));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DespejarLeft(" + P + ")";
	}

	@Override
	public ACTIONS getAction() {
		// TODO Auto-generated method stub
		return ACTIONS.ACTION_LEFT;
	}

}
