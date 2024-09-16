package amc.practica2.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AFND implements Cloneable, IAutomata {

	private Estado inicial;
	private List<Estado> estados;
	private List<Estado> estadosFinales;
	private List<Transicion> transiciones;
	private List<TransicionL> transicionesL;
	private List<int[]> pasos;

	public AFND() {
		estados = new ArrayList<>();
		estadosFinales = new ArrayList<>();
		transiciones = new ArrayList<>();
		transicionesL = new ArrayList<>();
		pasos = new ArrayList<>();
	}

	private int transicion(int estado, char simbolo) {
		for (Transicion t : transiciones) {
			if (t.getEstadoOrigen().getId() == estado && t.getSimbolo() == simbolo) {
				return t.getEstadoDestino().getId();
			}
		}
		return -1;
	}

	public int[] transicion(int[] macroestado, char simbolo) {
		
		for (int i = 0; i < macroestado.length; i++) {
			macroestado[i] = transicion(macroestado[i], simbolo);
		}
		
		return L_clausura(macroestado);
	}
	
	@Override
	public boolean esFinal(int estado) {
		if (estado == -1)
			return false;
		
		for (int i = 0; i < estadosFinales.size(); i++) {
			if (estadosFinales.get(i).getId() == estado)
				return true;
		}
		return false;
	}

	public boolean esFinal(int[] macroestado) {
		for (int est : macroestado) {
			if (esFinal(est))
				return true;
		}
		return false;
	}

	private int[] L_clausura(int[] macroestado) {
		List<Integer> resultado = new ArrayList<>();
		boolean hayLambda = false;
		for (int i = 0; i < macroestado.length; i++) {
			resultado.add(macroestado[i]);
			for (TransicionL t : transicionesL) {
				if (t.getEstadoOrigen().getId() == macroestado[i]) {
					hayLambda = true;
					for (Estado est : t.getEstadosDestino()) {
						resultado.add(est.getId());
					}
				}
			}
		}
		
		if (!hayLambda)
			return macroestado;
		
		int arr[] = new int[resultado.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = resultado.get(i);
		}
		return arr;
	}

	@Override
	public boolean reconocer(String cadena) {
		char[] simbolo = cadena.toCharArray();
		int[] estado = { inicial.getId() };// El estado inicial es el 0
		int[] macroestado = L_clausura(estado);
		pasos.clear();
		
		for (int i = 0; i < simbolo.length; i++) {
			pasos.add(macroestado.clone());
			macroestado = transicion(macroestado, simbolo[i]);
		}
		return esFinal(macroestado);
	}

	public static AFND readAFNDFile(File f) throws IOException {
		AFND afnd = new AFND();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		String lEstados = "ESTADOS:";
		String lInic = "INICIAL:";
		String lFinales = "FINALES:";
		String lTransic = "TRANSICIONES:";
		String lFin = "FIN";
		boolean transic = false;
		while ((line = br.readLine()) != null && !line.equals(lFin)) {
			if (line.indexOf(lEstados) != -1) {
				String[] strEstados = line.substring(lEstados.length(), line.length()).split(" ");
				for (String est : strEstados) {
					if (!est.strip().equals("")) {
						afnd.estados.add(new Estado(est));
					}
				}
			}
			else if (line.indexOf(lInic) != -1) {
				// Estado inicial
				afnd.inicial = new Estado(line.substring(lInic.length(), line.length()));
			}
			else if (line.indexOf(lFinales) != -1) {
				String[] estados = line.substring(lEstados.length(), line.length()).split(" ");
				for (String est : estados) {
					if (!est.strip().equals("")) {
						afnd.estadosFinales.add(new Estado(est));
					}
				}
			}
			else if (line.indexOf(lTransic) != -1) {
				transic = true;
			} 
			else if (line.indexOf(lFin) != -1) {
				transic = false;
			}
			else if (transic) {
				
				// Detectar tipo de transicion
				if (line.indexOf("L") == -1) {
					// Transicion normal
					String[] transicion = line.split(" ");
					char simbolo = transicion[1].replaceAll("\'", "").charAt(0);
					int s1, s2;
					s1 = Integer.parseInt(transicion[0].replaceAll("[^0-9]", ""));
					s2 = Integer.parseInt(transicion[2].replaceAll("[^0-9]", ""));
					boolean ok1 = false, ok2 = false;
					
					// Comprobamos que los estados esten en la lista de estados
					for (Estado state : afnd.estados) {
						if (state.getId() == s1) {
							ok1 = true;
						}
						if (state.getId() == s2) {
							ok2 = true;
						}
					}
					// Si no estan devolvemos error
					if (!ok1 || !ok2) {
						return null;
					}
					afnd.transiciones.add(new Transicion(afnd.estados.get(s1), simbolo, afnd.estados.get(s2)));
				} else {
					// Transicion lambda
					int idxDestino = line.indexOf("[");
					String origen = line.substring(0, idxDestino).split(" ")[0];
					
					Estado o = new Estado(origen.strip());
					boolean ok = false;
					for (Estado e : afnd.estados) {
						if (o.getId() == e.getId()) {
							ok = true;
							break;
						}
					}
					if (!ok)
						return null;
					
					String destinos[] = line.substring(idxDestino+1, line.indexOf("]")).split(",");
					List<Estado> estadosDestino = new ArrayList<>();
					for (String s : destinos) {
						Estado ne = new Estado(s.strip());
						
						ok = false;
						for (Estado e : afnd.estados) {
							if (ne.getId() == e.getId()) {
								ok = true;
								break;
							}
						}
						
						if (!ok)
							return null;
						else
							estadosDestino.add(ne);
					}
					afnd.transicionesL.add(new TransicionL(o, estadosDestino));
				}
			}
		}
		return afnd;
	}
	
	@Override
	public String toString() {
		String result = "ESTADOS: ";
		for (Estado est : estados) {
			result += est.getLabel() + " ";
		}
		result += "\nINICIAL: " + inicial.getLabel();
		result += "\nFINALES:";
		for (Estado est : estadosFinales) {
			result += est.getLabel() + " ";
		}
		result += "\nTRANSICIONES:\n";
		for (Transicion trans : transiciones) {
			result += trans.getEstadoOrigen().getLabel() + " \'" + trans.getSimbolo() + "\' " + trans.getEstadoDestino().getLabel() + "\n";
		}
		
		for (TransicionL trans : transicionesL) {
			result += trans.getEstadoOrigen().getLabel() + " \'L\' [";
			for (int i = 0; i < trans.getEstadosDestino().size(); i++) {
				result += trans.getEstadosDestino().get(i).getLabel() + (i == trans.getEstadosDestino().size()-1 ? "]\n" : ", "); 
			}
		}
		
		return result;
	}

	public Object clone() {
		AFND r = new AFND();
		r.estados = new ArrayList<>(estados);
		r.estadosFinales = new ArrayList<>(estadosFinales);
		r.transiciones = new ArrayList<>(transiciones);
		r.transicionesL = new ArrayList<>(transicionesL);
		return r;
	}

	public List<int[]> getPasos() {
		
		return pasos;
	}
}
