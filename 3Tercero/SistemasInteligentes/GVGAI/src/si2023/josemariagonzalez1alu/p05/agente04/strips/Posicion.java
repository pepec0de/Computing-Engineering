package si2023.josemariagonzalez1alu.p05.agente04.strips;

public class Posicion {

	public int x, y;
	
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Posicion(Posicion p) {
		this.x = p.x;
		this.y = p.y;
	}

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
	
	public static Posicion getPosicion(Posicion p, Direccion d) {
		switch (d) {
		case UP:
			return new Posicion(p.x, p.y - 1);
			
		case DOWN:
			return new Posicion(p.x, p.y + 1);

		case RIGHT:
			return new Posicion(p.x + 1, p.y);
			
		case LEFT:
			return new Posicion(p.x - 1, p.y);
			
		default:
			return new Posicion(p);
		}
	}
}
