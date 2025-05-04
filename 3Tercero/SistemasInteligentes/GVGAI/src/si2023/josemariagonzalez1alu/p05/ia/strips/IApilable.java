package si2023.josemariagonzalez1alu.p05.ia.strips;

public interface IApilable {

	public default boolean esMultiMeta() {return false;}
	public default boolean esMeta() {return false;}
	public default boolean esOperador() {return false;}
}
