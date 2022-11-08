package amc.practica1a.model;

public class Nodo<E> {
	private E valor;
	private Punto punto;
	
	public Nodo(E valor, Punto punto) {
		this.valor = valor;
		this.punto = punto;
	}

	public E getValor() {
		return valor;
	}

	public void setValor(E valor) {
		this.valor = valor;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}
	
}
