package si2023.josemariagonzalez1alu.p03;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p03.agente89.ad.ArbolDecision89;
import si2023.josemariagonzalez1alu.p03.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p03.ia.ad.ArbolDecision;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;
import tools.ElapsedCpuTimer;

public class Agente89 extends AbstractPlayer {

	private Mundo m;
	private ArbolDecision ad;
	
	public Agente89(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo89(arg0);
		ad = new ArbolDecision89(m);
	}

	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		m.actualizarMundo(arg0);
		return ad.piensa();
	}

}
