package amc.practica2.clases;

public class Transicion {

	private int estadoOrigen, estadoDestino;
	private char simbolo;
	
	public Transicion(int estadoOrigen, char simbolo, int estadoDestino) {
		this.estadoOrigen = estadoOrigen;
		this.estadoDestino = estadoDestino;
		this.simbolo = simbolo;
	}
	
	public int getEstadoOrigen() {
		return estadoOrigen;
	}

	public int getEstadoDestino() {
		return estadoDestino;
	}

	public char getSimbolo() {
		return simbolo;
	}
	
	
}
