package si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones;

import si2023.josemariagonzalez1alu.p02.ia.mef.Condicion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;

public class TripaNoLlena implements Condicion {

	public TripaNoLlena() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean seCumple(Mundo m) {
		Condicion c = new TripaLlena();
		return !c.seCumple(m);
	}

}
