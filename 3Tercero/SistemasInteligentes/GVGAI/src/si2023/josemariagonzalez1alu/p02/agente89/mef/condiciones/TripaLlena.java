package si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones;

import si2023.josemariagonzalez1alu.p02.ia.mef.Condicion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;

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
