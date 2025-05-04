package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Direccion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PFoso;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PRoca;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class TaparDown extends Operador<IPredicado> {

	private Posicion P;
	
	public TaparDown(Posicion P) {
		super();
		this.P = P;
		
		Posicion up = Posicion.getPosicion(P, Direccion.UP);
		Posicion upup = Posicion.getPosicion(up, Direccion.UP);
		precondiciones.add(new PFoso(P));
		precondiciones.add(new PRoca(up));
		precondiciones.add(new PAvatarEn(upup));
		
		adiciones.add(new PLibre(P));
		adiciones.add(new PAvatarEn(up));
		
		supresiones.add(new PFoso(P));
		supresiones.add(new PRoca(up));
		supresiones.add(new PAvatarEn(upup));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TaparDown(" + P + ")";
	}

	@Override
	public ACTIONS getAction() {
		return ACTIONS.ACTION_DOWN;
	}

}
