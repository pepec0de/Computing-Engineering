package si2023.josemariagonzalez1alu.p03.agente89.ad;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p03.agente89.ad.decisiones.CivilCayendo;
import si2023.josemariagonzalez1alu.p03.ia.ad.Accion;
import si2023.josemariagonzalez1alu.p03.ia.ad.ArbolDecision;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;

public class ArbolDecision89 extends ArbolDecision {

	public ArbolDecision89(Mundo m) {
		super(m);
		raiz = new CivilCayendo();
	}

	@Override
	public ACTIONS piensa() {
		return ( (Accion) raiz.decide(m) ).doAction(m);
	}

}
