package libClases;

public class ClienteTarifaPlana extends Cliente {
	
	private static float TARIFA = 20;
	private static float LIMITE_MINUTOS = 300;
	private static float SOBRE_COSTE = 0.15f;
	
	private float minutosHablados;
	private String nacionalidad;
	
	public ClienteTarifaPlana(String nif, String nom, Fecha fNac, float minutosHablados, String nacionalidad) {
		super(nif, nom, fNac);
		this.minutosHablados = minutosHablados;
		this.nacionalidad = nacionalidad;
	}

	public ClienteTarifaPlana(String nif, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, String nacionalidad) {
		super(nif, nom, fNac, fAlta);
		this.minutosHablados = minutosHablados;
		this.nacionalidad = nacionalidad;
	}
	
	public ClienteTarifaPlana(ClienteTarifaPlana c) {
		this(c.getNif(), c.getNombre(), c.getFechaNac(), c.getFechaAlta(), c.getMinutos(), c.getNacionalidad());
	}
	
	public float getMinutos() {
		return minutosHablados;
	}
	
	public void setMinutos(float minutosHablados) {
		this.minutosHablados = minutosHablados;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public static float getTarifa() {
		return TARIFA;
	}
	
	public static float getLimite() {
		return LIMITE_MINUTOS;
	}

	public static void setTarifa(int l, float tARIFA) {
		TARIFA = tARIFA;
		LIMITE_MINUTOS = l;
	}
	
	@Override
	public float factura() {
		return TARIFA + (minutosHablados > LIMITE_MINUTOS ? (minutosHablados - LIMITE_MINUTOS) * SOBRE_COSTE : 0);
	}
	
	@Override
	public Object clone() {
		return new ClienteTarifaPlana(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getMinutos(), getNacionalidad());
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof ClienteTarifaPlana && getNif().equals( ((Cliente) o).getNif());
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + nacionalidad + " [" + LIMITE_MINUTOS + " por " + TARIFA + "] " + minutosHablados + " --> " + factura();
	}
}
