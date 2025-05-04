package si2023.josemariagonzalez1alu.m16.ia.mente;

import core.game.StateObservation;

public interface Mundo {
	
	public void actualizarMundo(StateObservation so);
	public StateObservation getMundo();
	
}
