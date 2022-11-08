package amc.practica1.model;

public class Punto {
	
	private double x, y;
	
	public Punto() {
		this.x = this.y = 0;
	}
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getDistancia(Punto p) {
		return Math.sqrt( Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2));
	}
	

}
