package si2023.josemariagonzalez1alu.m16.ia.busqueda;

public class Posicion {

	public int x, y;

	public Posicion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Posicion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
		Posicion pos = (Posicion) o;
		
		if (pos.x == x && pos.y == y)
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int) Math.sqrt( Math.pow((double) x, 2) + Math.pow((double) y, 2));
	}
}
