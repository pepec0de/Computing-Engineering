package amc.practica2.clases;

public interface IAutomata {

	public abstract boolean esFinal(int estado);
	public abstract boolean reconocer(String cadena);
	public abstract String toString();
}
