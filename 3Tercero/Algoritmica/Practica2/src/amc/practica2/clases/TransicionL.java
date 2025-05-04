package amc.practica2.clases;

import java.util.List;

public class TransicionL {
	private Estado e1;
	private List<Estado> e2;

	public TransicionL(Estado e1, List<Estado> e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

	public Estado getEstadoOrigen() {
		return e1;
	}

	public List<Estado> getEstadosDestino() {
		return e2;
	}
	
	
}
