package amc.practica2.clases;

public class Transicion {

	private Estado estadoOrigen, estadoDestino;
	private char simbolo;
	
	public Transicion(Estado estadoOrigen, char simbolo, Estado estadoDestino) {
		this.estadoOrigen = estadoOrigen;
		this.estadoDestino = estadoDestino;
		this.simbolo = simbolo;
	}
	
	public Estado getEstadoOrigen() {
		return estadoOrigen;
	}

	public Estado getEstadoDestino() {
		return estadoDestino;
	}

	public char getSimbolo() {
		return simbolo;
	}
	
	
}
