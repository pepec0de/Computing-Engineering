package si2023.josemariagonzalez1alu.p01.agente89.motor.condiciones;

import si2023.josemariagonzalez1alu.p01.ia.mente.Mundo;
import si2023.josemariagonzalez1alu.p01.ia.motor.Condicion;

public class TripaLlena implements Condicion {
	
	public TripaLlena() {}
	
	@Override
	public boolean seCumple(Mundo m) {
		Integer capacidad = m.getMundo().getAvatarResources().get(13);
		if (capacidad != null && capacidad == 8) {
			return true;
		}
		return false;
	}
}
