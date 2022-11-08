package amc.practica1.model;

public class Arista<E, F> {
	private F peso;
	private Nodo<E> origen;
	private Nodo<E> destino;
	
	public Arista() {
		
	}
	
	public F getPeso() {
		return peso;
	}
	
	public void setPeso(F peso) {
		this.peso = peso;
	}
	
	public Nodo<E> getOrigen() {
		return origen;
	}
	
	public void setOrigen(Nodo<E> origen) {
		this.origen = origen;
	}
	
	public Nodo<E> getDestino() {
		return destino;
	}
	
	public void setDestino(Nodo<E> destino) {
		this.destino = destino;
	}

}
