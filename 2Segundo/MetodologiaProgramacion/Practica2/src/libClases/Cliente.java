package libClases;

public class Cliente implements Cloneable, Proceso {
	private final String nif;
	private final int codCliente;
	private String nombre;
	private final Fecha fechaNac;
	private final Fecha fechaAlta;
	
	private static int cont = 0;
	private static final Fecha FECHA_DEFECTO = new Fecha(1, 1, 2018); // DUDA: buena practica?

	public Cliente(String nif, String nom, Fecha fNac, Fecha fAlta) {
		this.codCliente = ++cont;
		
		this.nif = nif;
		this.nombre = nom;
		this.fechaNac = new Fecha(fNac);
		this.fechaAlta = new Fecha(fAlta);
	}
	
	public Cliente(String nif, String nom, Fecha fNac) {
		this(nif, nom, fNac, FECHA_DEFECTO);
	}
	
	public Cliente(Cliente c) {
		this(c.nif, c.nombre, c.fechaNac, c.fechaAlta);
	}
	
	public String getNif() {
		return nif;
	}
	
	public int getCodCliente() {
		return codCliente;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String val) {
		this.nombre = val;
	}
	
	public Fecha getFechaNac() {
		return (Fecha)fechaNac.clone();
	}
	
	public Fecha getFechaAlta() {
		return (Fecha)fechaAlta.clone();
	}
	
	public void setFechaAlta(Fecha f) {
		fechaAlta.setFecha(f.getDia(), f.getMes(), f.getAnio());
	}
	
	public float factura() {
		throw new UnsupportedOperationException("ERROR! No se puede facturar cliente sin tipo");
	}
	
	public final static Fecha getFechaPorDefecto() {
		return (Fecha)FECHA_DEFECTO.clone();
	}
	
	public final static void setFechaPorDefecto(Fecha f) {
		FECHA_DEFECTO.setFecha(f.getDia(), f.getMes(), f.getAnio());
	}
	
	@Override
	public Object clone() {
		return new Cliente(this);
	}
	
	@Override
	public boolean equals(Object o) {
		return o.getClass() == Cliente.class && nif.equals(((Cliente) o).nif);
	}
	
	@Override
	public String toString() {
		return nif + " " + fechaNac + ": " + nombre + " (" + codCliente + " - " + fechaAlta + ")";
	}
	
	@Override
	public void ver() {
		System.out.println(this);
	}
}
