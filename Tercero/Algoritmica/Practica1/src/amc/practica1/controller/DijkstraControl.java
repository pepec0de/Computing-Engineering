package amc.practica1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.table.DefaultTableModel;

import amc.practica1.model.Nodo;
import amc.practica1.view.DijkstraView;

public class DijkstraControl {
	
	private Controller control;
	private DijkstraView view;
	private int idx;
	private ArrayList<Nodo<Integer>> listaActuales; 
	private ArrayList<HashSet<Integer>> listaNodos;
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
					idx = (int) view.spinOrigen.getValue();
					control.getAlgGrafo().DijkstraPasos(control.getGrafo(), idx, listaActuales, listaNodos, listaDist);
					writeTables();
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
			for (Integer nodo : listaNodos.get(i)) {
				conjunto += nodo.toString() + ", ";
			}
			conjunto += "}";
			pasos[i][2] = conjunto; 
		}
		view.tablaPasos.setModel(new DefaultTableModel(pasos, columnsPasos));
	}
	
	private boolean datosCorrectos() {
		return (int)view.spinOrigen.getValue() >= 0 || (int)view.spinOrigen.getValue() < control.getPuntos().length;
	}
	
}
