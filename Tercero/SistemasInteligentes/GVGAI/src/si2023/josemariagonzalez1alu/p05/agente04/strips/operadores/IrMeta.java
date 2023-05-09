package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.mente.Mundo04;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLlave;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PMeta;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class IrMeta extends Operador<IPredicado> implements IMov {
	
	public IrMeta(Mundo04 m) {
		super();
		
		precondiciones.add(new PAvatarEn(m.getMetaPos()));
		precondiciones.add(new PLlave());
		
		adiciones.add(new PMeta());
	}

	@Override
	public String toString() {
		return "IrMeta";
	}

	@Override
	public ACTIONS getAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
