package amc.practica2.interfaz;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import amc.practica2.clases.*;


public class MainVentana extends JFrame {
	
	private String cadena;
	private AFD automata;
	private List<Estado> pasos;
	private int idx;
	private boolean resultado;
	
	public MainVentana() {
		
		setTitle("Práctica 2");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initJMenu();
		initComponents();
		initActions();
		
		setVisible(true);
	}
	
	private void initActions() {
		// Abrir
		itemAbrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser();
				ch.setCurrentDirectory(new File(System.getProperty("user.dir")));
				
				int r = ch.showOpenDialog(null);
				
				if (r == JFileChooser.APPROVE_OPTION) {
					File f = ch.getSelectedFile().getAbsoluteFile();
					// Leer archivo
					try {
						automata = AFD.readAFDFile(f);
						areaAutomata.setText(automata.toString());
					} catch (IOException ex) {
						showMessage(-1, "No se pudo abrir el fichero: " + ex.getMessage());
					}
				}
			}
		});
		
		// Simular automata
		btnSimular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (automata == null) {
					showMessage(-1, "Abra un autómata");
				} else if (txtSecuencia.getText().equals("")){
					showMessage(-1, "Indique una secuencia de simbolos");
				} else {
					idx = 0;
					cadena = txtSecuencia.getText();
					resultado = automata.reconocer(cadena);
					pasos = automata.getPasos();
					updateEstado();
				}
			}
		});
		
		btnPasoSig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idx == cadena.length()-1) {
					updateEstado();
					txtResultado.setText(resultado ? "ACEPTADA" : "RECHAZADA");
				} else {
					idx++;
					updateEstado();
				}
			}
		});
		
		btnPasoAnt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idx != 0) {
					idx--;
					updateEstado();
				}
			}
		});
		
		btnTerminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pasos != null) {
					idx = pasos.size() - 1;
					updateEstado();
					txtResultado.setText(resultado ? "ACEPTADA" : "RECHAZADA");
				}
			}
		});
	}
	
	private void updateEstado() {
		txtEstado.setText(idx > pasos.size()-1 ? "" : pasos.get(idx).getLabel());
		txtSimbolo.setText(idx > cadena.length() - 1 ? "" : String.valueOf(cadena.charAt(idx)));
		txtResultado.setText("");
	}
	
	private void initJMenu() {
		bar = new JMenuBar();
		mAutomata = new JMenu("Autómata");
		itemAbrir = new JMenuItem("Abrir archivo...");
		
		setJMenuBar(bar);
		bar.add(mAutomata);
		mAutomata.add(itemAbrir);
	}
	
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        areaAutomata = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtSecuencia = new javax.swing.JTextField();
        btnPasoSig = new javax.swing.JButton();
        btnPasoAnt = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnSimular = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        txtEstado.setEditable(false);
        jLabel4 = new javax.swing.JLabel();
        txtSimbolo = new javax.swing.JTextField();
        txtSimbolo.setEditable(false);
        jLabel5 = new JLabel();
        txtResultado = new JTextField();
        txtResultado.setEditable(false);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        areaAutomata.setColumns(20);
        areaAutomata.setRows(5);
        jScrollPane1.setViewportView(areaAutomata);

        jLabel1.setText("Secuencia:");

        btnPasoSig.setText(">");

        btnPasoAnt.setText("<");

        jLabel2.setText("Pasos");

        btnSimular.setText("Simular Autómata");

        btnTerminar.setText("Terminar secuencia");

        jLabel3.setText("Estado actual");

        jLabel4.setText("Símbolo actual");

        jLabel5.setText("Resultado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSecuencia)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimular, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPasoAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPasoSig, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTerminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEstado)
                                    .addComponent(txtSimbolo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(txtResultado))))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnSimular)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPasoAnt)
                            .addComponent(jLabel2)
                            .addComponent(btnTerminar)
                            .addComponent(btnPasoSig)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }
    
	private JMenuBar bar;
	private JMenu mAutomata;
	private JMenuItem itemAbrir;
	
	private JScrollPane jScrollPane1;
	private JTextArea areaAutomata;
	private JLabel jLabel1;
	private JTextField txtSecuencia;
	private JButton btnPasoSig;
	private JButton btnPasoAnt;
	private JLabel jLabel2;
	private JButton btnSimular;
	private JButton btnTerminar;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JTextField txtEstado;
	private JTextField txtSimbolo;
	private JLabel jLabel5;
	private JTextField txtResultado;
	
	public void showMessage(int type, String msg) {
        switch (type) {
            case -1:
                JOptionPane.showMessageDialog(null, msg, "ERROR", TrayIcon.MessageType.ERROR.ordinal());
                break;
            
            default:
                JOptionPane.showMessageDialog(null, msg, "WARNING", TrayIcon.MessageType.INFO.ordinal());
        }
    }
	
}
