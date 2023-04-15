package si2023.josemariagonzalez1alu.p02;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p02.agente89.mef.MEF89;
import si2023.josemariagonzalez1alu.p02.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p02.ia.mef.MEF;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;
import tools.ElapsedCpuTimer;

public class Agente89 extends AbstractPlayer {

	private Mundo m;
	private MEF mef;
	
	public Agente89(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo89(arg0);
		mef = new MEF89(m);
	}

	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		m.actualizarMundo(arg0);
		return mef.pensar();
	}

}
