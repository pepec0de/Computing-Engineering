package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Direccion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PRoca;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class RocaDown extends Operador<IPredicado> {

	private Posicion P;
	
	public RocaDown(Posicion P) {
		super();
		this.P = P;
		
		Posicion up = Posicion.getPosicion(P, Direccion.UP);
		Posicion down = Posicion.getPosicion(P, Direccion.DOWN);
		precondiciones.add(new PAvatarEn(up));
		precondiciones.add(new PLibre(down));
		precondiciones.add(new PRoca(P));
		
		adiciones.add(new PLibre(P));
		adiciones.add(new PAvatarEn(P));
		adiciones.add(new PRoca(down));
		
		supresiones.add(new PAvatarEn(up));
		supresiones.add(new PLibre(down));
		supresiones.add(new PRoca(P));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RocaDown(" + P + ")";
	}

	@Override
	public ACTIONS getAction() {
		return ACTIONS.ACTION_DOWN;
	}

}
