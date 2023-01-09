package amc.practica2.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AFD implements Cloneable, IAutomata {
	
	private List<Estado> estados;
	private List<Integer> estadosFinales; // indica el indice de los estados que son Finales
	private List<Transicion> transiciones; // transiciones del AFD
	private Estado inicial;

	public AFD() {
		estados = new ArrayList<>();
		estadosFinales = new ArrayList<>();
		transiciones = new ArrayList<>();
	}

	public void agregarTransicion(int e1, char simbolo, int e2) {
		transiciones.add(new Transicion(e1, simbolo, e2));
	}

	public int transicion(int estado, char simbolo) {
		// Buscamos la Transicion que corresponda con estado y simbolo
		for (Transicion transicion : transiciones) {
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
				afd.inicial = new Estado(line.substring(lInic.length(), line.length()));
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
				String[] transicion = line.split(" ");
				char simbolo = transicion[1].replaceAll("\'", "").charAt(0);
				int s1, s2;
				s1 = Integer.parseInt(transicion[0].replaceAll("[^0-9]", ""));
				s2 = Integer.parseInt(transicion[2].replaceAll("[^0-9]", ""));
				boolean ok1 = false, ok2 = false;
				
				// Comprobamos que los estados esten en la lista de estados
				for (Estado state : afd.estados) {
					if (state.getId() == s1) {
						ok1 = true;
					} else if (state.getId() == s2) {
						ok2 = true;
					}
				}
				// Si no estan devolvemos error
				if (!ok1 || !ok2) {
					return null;
				}
				afd.transiciones.add(new Transicion(s1, simbolo, s2));
			}
			else if (line.indexOf(lFin) != -1) {
				transic = false;
			}
		}
		return afd;
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	@Override
	public Object clone() {
		AFD afd = new AFD();
		
		afd.inicial = (Estado) inicial.clone();
		
		for (Estado est : estados) {
			afd.estados.add(est);
		}
		
		for (int est : estadosFinales) {
			afd.estadosFinales.add(est);
		}
		
		for (Transicion trans : transiciones) {
			afd.transiciones.add(trans);
		}
		
		return afd;
	}
}