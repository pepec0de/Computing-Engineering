package si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones;

import java.util.ArrayList;

import si2023.josemariagonzalez1alu.p02.ia.mef.Condicion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;
import tools.Vector2d;

public class NoHayCivilCayendo implements Condicion {

	@Override
	public boolean seCumple(Mundo m) {
		Condicion c = new HayCivilCayendo(new ArrayList<Vector2d>());
		return !c.seCumple(m);
	}

}
