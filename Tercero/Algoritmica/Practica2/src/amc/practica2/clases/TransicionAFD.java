package amc.practica2.clases;

public class TransicionAFD {

	private int estadoOrigen, estadoDestino;
	private char simbolo;
	
	public TransicionAFD(int estadoOrigen, int estadoDestino, char simbolo) {
		this.estadoOrigen = estadoOrigen;
		this.estadoDestino = estadoDestino;
		this.simbolo = simbolo;
	}
	
	public int getEstadoOrigen() {
		return estadoOrigen;
	}
	public void setEstadoOrigen(int estadoOrigen) {
		this.estadoOrigen = estadoOrigen;
	}
	public int getEstadoDestino() {
		return estadoDestino;
	}
	public void setEstadoDestino(int estadoDestino) {
		this.estadoDestino = estadoDestino;
	}
	public char getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	
}
