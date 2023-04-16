package si2023.josemariagonzalez1alu.p04;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.agente16.mente.Mundo16;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;
import tools.ElapsedCpuTimer;

public class Agente16 extends AbstractPlayer {

	private Mundo m;
	
	public Agente16(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo16(arg0);
		((Mundo16) m).imprimir();
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		return null;
	}

}
