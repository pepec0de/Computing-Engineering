/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pcd.practica6;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class Generador extends javax.swing.JFrame {

    /**
     * Creates new form Generador
     */
    public Generador() {
        setTitle("Practica 6 - Sincronización con semáforos");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Semaphore rayosMano = new Semaphore(1),
                perros = new Semaphore(2),
                rayosMaleta = new Semaphore(2),
                cuida = new Semaphore(0);
        Random r = new Random(System.currentTimeMillis());
        mCanvas cv = new mCanvas();
        Generador mf = new Generador();
        mf.setSize(1500, 450);
        cv.setSize(mf.getWidth(), mf.getHeight());
        mf.add(cv);
        mf.setVisible(true);
        Cuidador cuidador = new Cuidador(perros, cuida, cv);
        Thread hilos[] = new Thread[20];
        for (int i = 0; i < hilos.length; i++) {
            if (r.nextInt(1, 11) > 3) {
                hilos[i] = new ViajeroMaleta(rayosMaleta, perros, cuida, cv);
            } else {
                hilos[i] = new Thread(new ViajeroMano(rayosMano, perros, cuida, cv));
            }
        }
        cuidador.start();
        for (Thread hilo : hilos) {
            hilo.start();
        }
        
        System.out.println("Hilos lanzados");
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Generador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Generador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Generador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Generador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        cv.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
