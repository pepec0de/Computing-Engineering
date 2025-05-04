package si2023.josemariagonzalez1alu.p03.ia.ad;

import si2023.josemariagonzalez1alu.p03.ia.mente.Mundo;

public abstract class Decision extends NodoArbol {

	protected Condicion pregunta;
	protected NodoArbol nodoSi, nodoNo;
	
	public Decision() {}
	
	@Override
	public NodoArbol decide(Mundo m) {
		if (pregunta.seCumple(m)) {
			return nodoSi.decide(m);
		}
		return nodoNo.decide(m);
	}

}
