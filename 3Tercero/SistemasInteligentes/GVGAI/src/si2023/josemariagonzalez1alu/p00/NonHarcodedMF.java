package si2023.josemariagonzalez1alu.p00;

import java.util.Stack;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p00.agente50.busqueda.Busqueda50;
import si2023.josemariagonzalez1alu.p00.agente50.mente.Mundo50;
import si2023.josemariagonzalez1alu.p00.ia.busqueda.Busqueda;
import si2023.josemariagonzalez1alu.p00.ia.mente.Mundo;
import tools.ElapsedCpuTimer;

public class NonHarcodedMF extends AbstractPlayer {
	
	private Mundo m;
	private Busqueda busqueda;
	private Stack<ACTIONS> pasos;
	
	public NonHarcodedMF(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo50(arg0);
		//m.imprimir();
		
		busqueda = new Busqueda50(m);
		
		pasos = busqueda.pensar();
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		if (!pasos.isEmpty()) {
			return pasos.pop();
		}
		
		return ACTIONS.ACTION_NIL;
	}
}
