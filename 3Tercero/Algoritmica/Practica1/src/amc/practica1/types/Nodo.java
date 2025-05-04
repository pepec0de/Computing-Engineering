package amc.practica1.types;

public class Nodo<E> {
	private E valor;
	private Punto punto;
	
	public Nodo(E valor) {
		this.valor = valor;
	}
	
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
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (!(o instanceof Nodo))
			return false;
		
		Nodo<E> nodo = (Nodo<E>) o;
		return getValor().equals(nodo.getValor()) && getPunto().equals(nodo.getPunto());
	}
}
