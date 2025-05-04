package libClases;

import java.util.Scanner;

public class Empresa implements Cloneable, Proceso {
	
	private Cliente clientes[];
	private int nClientes;
	private final int INCREMENTO = 2;
	
	private int buscarCliente(String NIF) {
		int i = 0;
		while (i < nClientes && !clientes[i].getNif().equals(NIF)) i++;
		return i == nClientes ? -1 : i;
	}
	
	public Empresa() {
		clientes = new Cliente[INCREMENTO];
		nClientes = 0;
	}
	
	public int getN() {
		return nClientes;
	}
	
	public int nClienteMovil() {
		int n = 0;
		for (int i = 0; i < nClientes; i++) if (clientes[i] instanceof ClienteMovil) n++;
		return n;
	}
	
	public void alta() {
		System.out.println("Alta del cliente. Introduzca los datos: ");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("DNI: ");
		String NIF = sc.nextLine();
		
		int pos = buscarCliente(NIF);
		if (pos != -1) {
			System.out.println("Ya existe un Cliente con ese dni:\n" + clientes[pos] + "\n");
		} else {
			System.out.print("Nombre: ");
			String nombre = sc.nextLine();
			
			System.out.print("Fecha de nacimiento: ");
			Fecha fNac = Fecha.pedirFecha();
			
			System.out.print("Fecha de alta: ");
			Fecha fAlta = Fecha.pedirFecha();
			
			System.out.print("Minutos hablados: ");
			float minutosHablados = sc.nextFloat();
			
			int opc;
			do {
				System.out.print("Indique tipo de cliente (1.- Movil, 2.- Tarifa Plana): ");
				opc = sc.nextInt();
			} while(opc != 1 && opc != 2);
			
			Cliente c;
			if (opc == 1) {
				System.out.print("Precio por minuto: ");
				float precioMin = sc.nextFloat();
				
				System.out.print("Fecha fin permanencia: ");
				Fecha fPermanencia = Fecha.pedirFecha();
				
				c = new ClienteMovil(NIF, nombre, fNac, fAlta, fPermanencia, minutosHablados, precioMin);
			} else {
				System.out.print("Nacionalidad: ");
				String nacionalidad = sc.next();
				
				c = new ClienteTarifaPlana(NIF, nombre, fNac, fAlta, minutosHablados, nacionalidad);
			}
			alta(c);
		}
	}
	
	public void alta(Cliente c) {
		if (c == null || buscarCliente(c.getNif()) != -1) return;
		if (nClientes >= clientes.length) {
			Cliente tmp[] = new Cliente[clientes.length + INCREMENTO];
			for (int i = 0; i < clientes.length; i++) tmp[i] = clientes[i];
			clientes = tmp;
		}
		clientes[nClientes++] = c;
	}
	
	public void baja() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduzca nif cliente a dar de baja: ");
		int pos = buscarCliente(sc.next());
		if (pos == -1) {
			System.out.println("No se ha encontrado un cliente con el NIF introducido.\n");	
		} else {
			Cliente c = clientes[pos];
			System.out.println(c);
			String opc;
			do {
				System.out.print("¿Seguro que desea eliminarlo (s/n)? ");
				opc = sc.next();
			} while (!opc.equals("s") && !opc.equals("n"));
			
			if (opc.equals("s")) {
				baja(c.getNif());
				System.out.println("El cliente " + c.getNombre() + " con nif " + c.getNif() + " ha sido eliminado.\n");
			} else {
				System.out.println("El cliente con nif " + c.getNif() + " no se elimina.\n");
			}
		}
	}
	
	public void baja(String nif) {
		if (nif.equals("")) return;
		int pos = buscarCliente(nif);
		if (pos != -1) {
			nClientes--;
			for (int i = pos; i < nClientes; i++) clientes[i] = clientes[i + 1];
		}
		
		if (clientes.length - INCREMENTO > nClientes) {
			Cliente tmp[] = new Cliente[clientes.length - INCREMENTO];
			for (int i = 0; i < tmp.length; i++) tmp[i] = clientes[i];
			clientes = tmp;
		}
	}
	
	public float factura() {
		float result = 0;
		for (int i = 0; i < nClientes; i++) {
			result += clientes[i].factura();
		}
		return result;
	}
	
	public void descuento(int porcen) {
		float descuento =  (float) (100 - porcen / 100.0);
		for (int i = 0; i < nClientes; i++) {
			if (clientes[i] instanceof ClienteMovil) {
				((ClienteMovil)clientes[i]).setPrecioMinuto( ((ClienteMovil)clientes[i]).getPrecioMinuto() * descuento);
			}
		}
	}
	
	
	@Override
	public Object clone() {
		Empresa result = null;
		try {
			result = (Empresa) super.clone();
			result.clientes = clientes.clone();
			for (int i = 0; i < nClientes; i++) result.clientes[i] = (Cliente) clientes[i].clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		for (int i = 0; i < nClientes; i++)
			result += clientes[i] + "\n";
		
		return result;
	}

	@Override
	public void ver() {
		System.out.println(this);
	}
	
}
