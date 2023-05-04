package practica8;

import java.util.ArrayList;

public class Operador implements Apilable {
	
	private String nombre;
	public ArrayList<Character> precondiciones, adiciones, supresiones;

	public Operador(String nombre) {
		this.nombre = nombre;
		precondiciones = new ArrayList<>();
		adiciones = new ArrayList<>();
		supresiones = new ArrayList<>();
	}

	public boolean ejecutable(Estado e) {
		return e.abiertos.containsAll(precondiciones);
	}

	public String toString() {
		return nombre;
	}

	@Override
	public boolean esMultiMeta() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esMeta() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esOperador() {
		// TODO Auto-generated method stub
		return true;
	}

}
