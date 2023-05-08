package si2023.josemariagonzalez1alu.p05;

import java.util.LinkedList;
import java.util.Queue;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.mente.Mundo04;
import si2023.josemariagonzalez1alu.p05.agente04.strips.STRIPS04;
import tools.ElapsedCpuTimer;

public class Agente007 extends AbstractPlayer {
	
	private Mundo04 m;
	private Queue<ACTIONS> acciones;
	private STRIPS04 strips;
	
	public Agente007(StateObservation arg0, ElapsedCpuTimer arg1) {
		m = new Mundo04(arg0);
		m.imprimir();
		strips = new STRIPS04(m);
		
		acciones = new LinkedList<>();
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_LEFT);
	}

	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		m.actualizarMundo(arg0);
		//m.imprimir();
		//return acciones.poll();
		return null;
	}

}
