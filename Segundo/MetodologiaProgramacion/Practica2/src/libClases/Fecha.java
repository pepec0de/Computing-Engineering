package libClases;

import java.util.Scanner;

public final class Fecha implements Cloneable, Proceso {

	private int dia, mes, anio;

	public Fecha(Fecha f) {
		this.dia = f.dia;
		this.mes = f.mes;
		this.anio = f.anio;
	}

	public Fecha(int d, int m, int a) {
		setFecha(d, m, a);
	}

	public void setFecha(int d, int m, int a) {
		dia = d;
		mes = m;
		anio = Math.abs(a);

		int dmax, diasMes[] = { 31, bisiesto() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (mes < 1)
			mes = 1;
		else if (mes > 12)
			mes = 12;

		dmax = diasMes[mes - 1];
		if (dia < 1)
			dia = 1;
		else if (dia > dmax)
			dia = dmax;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public boolean bisiesto() {
		return anio % 400 == 0 || (anio % 4 == 0 && anio % 100 != 0);
	}

	public Fecha diaSig() {
		Fecha result = (Fecha) this.clone();

		result.dia++;
		int dmax, diasMes[] = { 31, bisiesto() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		dmax = diasMes[result.mes - 1];

		if (result.dia > dmax) {
			result.dia = 1;
			result.mes++;
			if (result.mes > 12) {
				result.mes = 1;
				result.anio++;
			}
		}

		return result;
	}

	public static boolean mayor(Fecha f1, Fecha f2) {
		return f1.anio > f2.anio || (f1.anio == f2.anio && f1.mes > f2.mes)
				|| (f1.anio == f2.anio && f1.mes == f2.mes && f1.dia > f2.dia);
	}

	public static Fecha pedirFecha() {
		Fecha result = null;
		boolean valida = false;
		Scanner sc = new Scanner(System.in);
		int dia, mes, anio;
		do {
			System.out.print("Introduzca una fecha (dd/mm/aaaa): ");
			String[] inputFecha = sc.next().split("/");
			try {
				if (inputFecha.length != 3) 
					throw new NumberFormatException();
				dia = Integer.parseInt(inputFecha[0]);
				mes = Integer.parseInt(inputFecha[1]);
				anio = Integer.parseInt(inputFecha[2]);
				result = new Fecha(dia, mes, anio);
				if (result.getDia() != dia || result.getMes() != mes) {
					throw new NumberFormatException();
				}
				valida = true;
			} catch (NumberFormatException e) {
				System.out.println("Fecha no v�lida.");
			}
		} while (!valida);

		return result;
	}

	@Override
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (this == obj)
			iguales = true;
		else if (obj == null)
			iguales = false;
		else if (getClass() != obj.getClass())
			iguales = false;
		else {
			Fecha c = (Fecha) obj;
			iguales = (dia == c.dia & mes == c.mes && anio == c.anio);
		}
		return iguales;
	}

	@Override
	public String toString() {
		return String.format("%02d/%02d/%02d", dia, mes, anio);
	}

	@Override
	public void ver() {
		System.out.println(this);
	}

	public static void main(String[] args) {
		Fecha f1 = new Fecha(29, 2, 2001), f2 = new Fecha(f1), f3 = new Fecha(29, 2, 2004);
		final Fecha f4 = new Fecha(05, 12, 2003); // es constante la referencia f4
		System.out.println("Fechas: " + f1.toString() + ", " + f2 + ", " + f3 + ", " + f4);
		f1 = new Fecha(31, 12, 2016); // 31/12/2016
		f4.setFecha(28, 2, 2008); // pero no es constante el objeto al que apunta
		System.out.println(f1 + " " + f2.toString() + " " + f3 + " " + f4 + " " + f1);
		f1 = new Fecha(f4.getDia() - 10, f4.getMes(), f4.getAnio() - 7); // f1=18/02/2001
		f3 = Fecha.pedirFecha(); // pide una fecha por teclado
		if (f3.bisiesto() && Fecha.mayor(f2, f1))
			System.out.println("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3);
		System.out.print("Fin\n");
	}
}
