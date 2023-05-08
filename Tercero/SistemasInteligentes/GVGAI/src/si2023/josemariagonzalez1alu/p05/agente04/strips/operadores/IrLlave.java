package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import si2023.josemariagonzalez1alu.p05.agente04.mente.Mundo04;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLlave;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class IrLlave extends Operador<IPredicado> {

	public IrLlave(Mundo04 m) {
		super();
		
		precondiciones.add(new PAvatarEn(m.getKeyPos()));
		
		adiciones.add(new PLlave());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "IrLlave";
	}

}
