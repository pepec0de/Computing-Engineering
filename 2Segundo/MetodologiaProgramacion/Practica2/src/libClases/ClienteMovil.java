package libClases;

public class ClienteMovil extends Cliente {
	// DUDA: minutosHablados puede estar en Cliente
	private float minutosHablados, precioMinuto;
	private Fecha fechaPermanencia;
	
	public ClienteMovil(String nif, String nom, Fecha fNac, Fecha fAlta, Fecha fPermanencia, float minutosHablados, float precioMinuto) {
		super(nif, nom, fNac, fAlta);
		fechaPermanencia = (Fecha)fPermanencia.clone();
		this.minutosHablados = minutosHablados;
		this.precioMinuto = precioMinuto;
	}
	
	public ClienteMovil(String nif, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, float precioMinuto) {
		this(nif, nom, fNac, fAlta, new Fecha(fAlta.getDia(), fAlta.getMes(), fAlta.getAnio() + 1), minutosHablados, precioMinuto);
	}
	
	public ClienteMovil(String nif, String nom, Fecha fNac, float minutosHablados, float precioMinuto) {
		this(nif, nom, fNac, getFechaPorDefecto(), minutosHablados, precioMinuto);
	}
	
	public ClienteMovil(ClienteMovil c) {
		this(c.getNif(), c.getNombre(), c.getFechaNac(), c.getFechaAlta(), c.getFPermanencia(), c.getMinutos(), c.getPrecioMinuto());
	}

	public float getMinutos() {
		return minutosHablados;
	}

	public void setMinutos(float minutosHablados) {
		this.minutosHablados = minutosHablados;
	}

	public float getPrecioMinuto() {
		return precioMinuto;
	}

	public void setPrecioMinuto(float precioMinuto) {
		this.precioMinuto = precioMinuto;
	}

	public Fecha getFPermanencia() {
		return fechaPermanencia;
	}

	public void setFPermanencia(Fecha fechaPermanencia) {
		this.fechaPermanencia = fechaPermanencia;
	}
	
	@Override
	public float factura() {
		return minutosHablados * precioMinuto;
	}

	@Override
	public Object clone() {
		return new ClienteMovil(getNif(), getNombre(), getFechaNac(), getFechaAlta(), fechaPermanencia, minutosHablados, precioMinuto);
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof ClienteMovil && getNif().equals( ((Cliente) o).getNif()));
	}

	@Override
	public String toString() {
		return super.toString() + " " + fechaPermanencia + " " + minutosHablados + " x " + precioMinuto + " --> " + factura();
	}

}
