package amc.practica2.clases;

public class AFND implements Cloneable, IAutomata {

	private int[] estadosFinales;
	
	@Override
	public boolean esFinal(int estado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reconocer(String cadena) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return null;
	}
	
	public Object clone() {
		return null;
	}
}
