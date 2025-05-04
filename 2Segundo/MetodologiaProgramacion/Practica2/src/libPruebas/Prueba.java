package libPruebas;

public class Prueba {
	Punto p;
	public Prueba() {
		p = new Punto(0);
		setPunto(p);
		System.out.println(p.x);
	}
	
	void setPunto(Punto p) {
		p = new Punto(3);
	}
	
	public static void main(String[] args) {
		Prueba prue = new Prueba();
	}
}
