package si2023.josemariagonzalez1alu.p04;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.agente16.busqueda.Busqueda16;
import si2023.josemariagonzalez1alu.p04.agente16.mente.Mundo16;
import si2023.josemariagonzalez1alu.p04.ia.mente.Mundo;
import tools.ElapsedCpuTimer;

public class Agente16 extends AbstractPlayer {

	private Mundo16 m;
	private Busqueda16 busqueda;
	
	public Agente16(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo16(arg0);
		m.imprimir();
		busqueda = new Busqueda16(m);
		busqueda.pensar();
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		return null;
	}

}
