package amc.practica1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import amc.practica1.types.Nodo;
import amc.practica1.view.DijkstraView;

public class DijkstraControl {
	
	private Controller control;
	private DijkstraView view;
	private int idx;
	private ArrayList<Integer> listaActuales; 
	private ArrayList<ArrayList<Integer>> listaNodos;
	private ArrayList<int[]> listaDist;
	
	public DijkstraControl(Controller c) {
		control = c;
		view = new DijkstraView();
		initActions();
	}
	
	public void abrir() {
		view.setVisible(true);
	}
	
	private void initActions() {
		view.btnCalc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (datosCorrectos()) {
					// Guardamos el fichero
					JFileChooser ch = new JFileChooser();
					File archivo;
					ch.setCurrentDirectory(new File(System.getProperty("user.dir")));
					int r = ch.showSaveDialog(view);
					if (r == JFileChooser.APPROVE_OPTION) {
						archivo = ch.getSelectedFile();
						System.out.println(archivo.getAbsolutePath());
						idx = (int) view.spinOrigen.getValue();
						listaActuales = new ArrayList<>();
						listaNodos = new ArrayList<>();
						listaDist = new ArrayList<>();
						control.getAlgGrafo().DijkstraPasos(control.getGrafo(), idx, listaActuales, listaNodos, listaDist);
						int n = control.getGrafo().getSize();
						int solucion = 0;
						
						// Escribir archivo
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
							String s = "NAME: " + archivo.getName() + "\nTYPE: TOUR" + "\nDIMENSION: " + String.valueOf(n)
										+ "\nSOLUTION: " + String.valueOf(solucion) + "\nTOUR_SECTION\n";
							
							s += "-1\n";
							s += "EOF";
							bw.write(s);
							bw.close();
						} catch (IOException e1) {
							control.getDialogs().showError(e1.getMessage());
						}
						
						writeTables();
					}
				} else {
					control.getDialogs().showError("Índice incorrecto");
				}
			}
		});
		
	}
	
	private void writeTables() {
		int filas = listaDist.size();
		Object pasos[][] = new Object[filas][3];
		String columnsPasos[] = {"paso", "V", "C"};
		
		for (int i = 0; i < filas; i++) {
			pasos[i][0] = i;
			if (i == 0) {
				pasos[0][1] = "-"; 
			} else {
				pasos[i][1] = listaActuales.get(i-1);
			}
			Object conjunto = "{";
			for (int j = 0; j < listaNodos.get(i).size(); j++) {
				conjunto += listaNodos.get(i).get(j).toString() + (j == listaNodos.get(i).size()-1 ? "" : ", ");
			}
			conjunto += "}";
			pasos[i][2] = conjunto; 
		}
		view.tablaPasos.setModel(new DefaultTableModel(pasos, columnsPasos));
		
		Object distancias[][] = new Object[filas][listaDist.get(0).length];
		Object columnsDist[] = new Object[listaDist.get(0).length];
		for (int i = 0; i < control.getGrafo().getSize(); i++) {
			if (i != idx) {
				columnsDist[i] = control.getGrafo().getNodoAt(i).getValor();
			}
		}
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < listaDist.get(0).length; j++) {
				distancias[i][j] = listaDist.get(i)[j];
			}
		}
		
		view.tablaDistancias.setModel(new DefaultTableModel(distancias, columnsDist));
	}
	
	private boolean datosCorrectos() {
		return (int)view.spinOrigen.getValue() >= 0 || (int)view.spinOrigen.getValue() < control.getPuntos().length;
	}
	
}
