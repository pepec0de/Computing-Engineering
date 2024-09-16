package si2023.josemariagonzalez1alu.p02.agente89.mente;

import ontology.Types.ACTIONS;

public class Camino {
	
	public static ACTIONS DarPaso(int xo, int yo, int xf, int yf) {
		int dx = xf - xo;
		int dy = yf - yo;
		
		if (dx == 0 && dy == 0)
			return ACTIONS.ACTION_NIL;
		

		if (dx == 0) {
			if (dy < 0)
				return ACTIONS.ACTION_UP;
			else
				return ACTIONS.ACTION_DOWN;
		}
		
		if (dy == 0) {
			if (dx < 0)
				return ACTIONS.ACTION_LEFT;
			else
				return ACTIONS.ACTION_RIGHT;
			
		}
		
		int adx = Math.abs(dx);
		int ady = Math.abs(dy);
		
		if (ady <= adx) {
			if (dy > 0)
				return ACTIONS.ACTION_DOWN;
			else
				return ACTIONS.ACTION_UP;
		} else {
			if (dx > 0)
				return ACTIONS.ACTION_RIGHT;
			else
				return ACTIONS.ACTION_LEFT;
		}
	}
}
