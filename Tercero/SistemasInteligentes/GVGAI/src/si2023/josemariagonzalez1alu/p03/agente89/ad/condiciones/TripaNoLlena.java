package si2023.josemariagonzalez1alu.p03.agente89.ad.condiciones;

import si2023.josemariagonzalez1alu.p03.ia.ad.Condicion;
import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;

public class TripaNoLlena implements Condicion {
	
	public TripaNoLlena() {}
	
	@Override
	public boolean seCumple(Mundo m) {
		Integer capacidad = m.getMundo().getAvatarResources().get(13);
		if (capacidad != null && capacidad == 8) {
			return false;
		}
		return true;
	}
}
