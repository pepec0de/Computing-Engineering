package si2023.josemariagonzalez1alu.p01.ia.mente;

import core.game.StateObservation;

public interface Mundo {
	
	public void actualizarMundo(StateObservation so);
	public StateObservation getMundo();
	
}
