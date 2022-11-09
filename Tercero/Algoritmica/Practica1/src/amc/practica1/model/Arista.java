package amc.practica1.model;

public class Arista<E, L> {
	private L peso;
	private Nodo<E> origen;
	private Nodo<E> destino;
	
	public Arista() {}
	
	public Arista(Nodo<E> origen, Nodo<E> destino, L peso) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}
	
	public L getPeso() {
		return peso;
	}
	
	public void setPeso(L peso) {
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

	 @Override
	 public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (!(o instanceof Arista))
			return false;
		
		Arista<E, L> a = (Arista<E, L>) o;
		return getOrigen().equals(a.getOrigen()) && getDestino().equals(a.getDestino()) && getPeso().equals(a.getPeso());
	 }
}
