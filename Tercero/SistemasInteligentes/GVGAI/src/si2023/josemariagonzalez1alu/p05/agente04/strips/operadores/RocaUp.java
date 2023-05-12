package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Direccion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PRoca;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class RocaUp extends Operador<IPredicado> {

	private Posicion P;
	
	public RocaUp(Posicion P) {
		super();
		this.P = P;
		
		Posicion down = Posicion.getPosicion(P, Direccion.DOWN);
		Posicion up = Posicion.getPosicion(P, Direccion.UP);
		precondiciones.add(new PAvatarEn(down));
		precondiciones.add(new PLibre(up));
		precondiciones.add(new PRoca(P));
		
		adiciones.add(new PLibre(P));
		adiciones.add(new PAvatarEn(P));
		adiciones.add(new PRoca(up));
		
		supresiones.add(new PAvatarEn(down));
		supresiones.add(new PLibre(up));
		supresiones.add(new PRoca(P));
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RocaUp(" + P + ")";
	}

	@Override
	public ACTIONS getAction() {
		return ACTIONS.ACTION_UP;
	}

}
