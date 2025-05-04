package si2023.josemariagonzalez1alu.p01;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p01.agente89.mente.Mundo89;
import si2023.josemariagonzalez1alu.p01.agente89.motor.Motor89;
import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;
import si2023.josemariagonzalez1alu.p01.ia.motor.Motor;
import tools.ElapsedCpuTimer;

public class Agente89 extends AbstractPlayer{
	
	private Mundo mundo;
	private Motor motor;
	
	public Agente89(StateObservation arg0, ElapsedCpuTimer arg1) {
		mundo = new Mundo89(arg0);
		motor = new Motor89(mundo);
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		mundo.actualizarMundo(arg0);
		return motor.pensar();
	}

}
