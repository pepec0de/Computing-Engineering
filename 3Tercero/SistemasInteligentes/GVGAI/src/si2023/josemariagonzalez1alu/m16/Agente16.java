package si2023.josemariagonzalez1alu.m16;

import java.util.Stack;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.m16.agente16.busqueda.Busqueda16;
import si2023.josemariagonzalez1alu.m16.agente16.mente.Mundo16;
import tools.ElapsedCpuTimer;

public class Agente16 extends AbstractPlayer {

	private Mundo16 m;
	private Busqueda16 busqueda;
	private Stack<ACTIONS> pasos;
	
	public Agente16(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo16(arg0);
		m.imprimir();
		busqueda = new Busqueda16(m);
		
		pasos = busqueda.pensar();
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		if (!pasos.isEmpty() && arg0.getAvatarType() == 9) {
			return pasos.pop();
		}
		
		return ACTIONS.ACTION_NIL;
	}

}
