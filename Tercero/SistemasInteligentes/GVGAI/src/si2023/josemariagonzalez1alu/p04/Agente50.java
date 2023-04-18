package si2023.josemariagonzalez1alu.p04;

import java.util.ArrayList;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p04.agente50.busqueda.Busqueda50;
import si2023.josemariagonzalez1alu.p04.agente50.mente.Mundo50;
import tools.ElapsedCpuTimer;

public class Agente50 extends AbstractPlayer {

	private Mundo50 m;
	private Busqueda50 busqueda;
	private ArrayList<ACTIONS> pasos;
	
	public Agente50(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo50(arg0);
		m.imprimir();
		
		busqueda = new Busqueda50(m);
		
		pasos = busqueda.pensar();
	}
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		if (!pasos.isEmpty()) {
			return pasos.remove(0);
		}
		
		return ACTIONS.ACTION_NIL;
	}

}
