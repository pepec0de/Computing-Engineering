package si2023.josemariagonzalez1alu.p05.agente04.strips;

public class Posicion {

	public int x, y;

	public Posicion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Posicion() {}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		Posicion pos = (Posicion) o;
		return pos.x == x && pos.y == y;
	}
	
	@Override
	public int hashCode() {
		return (int) x*31 + y;
	}
	
	public String toString() {
		return x + ", " + y;
	}
}
