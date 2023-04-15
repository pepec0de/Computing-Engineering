package si2023.josemariagonzalez1alu.p00;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

public class AgentWinner extends AbstractPlayer {

	int ruta1 = 2;
	int ruta2 = 4;
	int ruta3 = 4;
	int ruta4 = 7;
	int ruta5 = 13;
	int ruta6 = 8;
	public AgentWinner(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		if (ruta1 > 0) {
			ruta1--;
			return Types.ACTIONS.ACTION_RIGHT;
		} else if (ruta2 > 0) {
			ruta2--;
			return Types.ACTIONS.ACTION_DOWN;
		} else if (ruta3 > 0) {
			ruta3--;
			return Types.ACTIONS.ACTION_LEFT;
		} else if (ruta4 > 0) {
			ruta4--;
			return Types.ACTIONS.ACTION_UP;
		} else if (ruta5 > 0) {
			ruta5--;
			return Types.ACTIONS.ACTION_RIGHT;
		} else if (ruta6 > 0) {
			ruta6--;
			return Types.ACTIONS.ACTION_DOWN;
		}
		return Types.ACTIONS.ACTION_NIL;
	}

}
