package amc.practica2.clases;

public class TransicionL {
	private Estado e1, e2;

	public TransicionL(Estado e1, Estado e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

	public Estado getEstadoOrigen() {
		return e1;
	}

	public Estado getEstadoDestino() {
		return e2;
	}
	
	
}
