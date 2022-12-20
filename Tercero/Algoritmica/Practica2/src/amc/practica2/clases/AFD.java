package amc.practica2.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AFD implements Cloneable, IAutomata {
	
	private List<Estado> estados;
	private List<Integer> estadosFinales; // indica cuales son los estados Finales
	private List<TransicionAFD> transiciones; // indica la lista de transiciones del AFD

	public AFD() {
		estados = new ArrayList<>();
		estadosFinales = new ArrayList<>();
		transiciones = new ArrayList<>();
	}

	public void agregarTransicion(int e1, char simbolo, int e2) {
		transiciones.add(new TransicionAFD(e1, simbolo, e2));
	}

	public int transicion(int estado, char simbolo) {
		// Buscamos la Transicion que corresponda con estado y simbolo
		for (TransicionAFD transicion : transiciones) {
			if (transicion.getEstadoOrigen() == estado && transicion.getSimbolo() == simbolo) {
				return transicion.getEstadoDestino();
			}
		}
		return -1;
	}
	
	@Override
	public boolean esFinal(int estado) {
		for (int i = 0; i < estadosFinales.size(); i++) {
			if (estadosFinales.get(i) == estado)
				return true;
		}
		return false;
	}
	
	@Override
	public boolean reconocer(String cadena) {
		char[] simbolo = cadena.toCharArray();
		int estado = 0; // El estado inicial es el 0
		for (int i = 0; i < simbolo.length; i++) {
			estado = transicion(estado, simbolo[i]);
		}
		return esFinal(estado);
	}
	
	public static AFD readAFDFile(File f) throws IOException {
		AFD afd = new AFD();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		String lEstados = "ESTADOS: ";
		String lInic = "INICIAL: ";
		String lFinales = "FINALES: ";
		String lTransic = "TRANSICIONES:";
		String lFin = "FIN";
		boolean transic = false;
		while ((line = br.readLine()) != null && !line.equals(lFin)) {
			if (line.indexOf(lEstados) != -1) {
				String[] strEstados = line.substring(lEstados.length(), line.length()).split(" ");
				for (String est : strEstados) {
					afd.estados.add(new Estado(est));
				}
			}
			else if (line.indexOf(lInic) != -1) {
				// Estado inicial
			}
			else if (line.indexOf(lFinales) != -1) {
				String[] estados = line.substring(lEstados.length(), line.length()).split(" ");
				for (String est : estados) {
					afd.estadosFinales.add(Integer.parseInt(est.replaceAll("[^0-9]", "")));
				}
			}
			else if (line.indexOf(lTransic) != -1) {
				transic = true;
			} else if (transic) {
				
			}
		}
		return afd;
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	public Object clone() {
		return null;
	}
}