/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd.practica5;

import java.util.Random;

/**
 *
 * @author Pepe
 */
public class Generador {
    
    public static void main(String[] args) {
        int n = 10;
        Thread vehiculos[] = new Thread[n];
        Random r = new Random(System.currentTimeMillis());
        Tunel tunel = new Tunel();
        CanvasTunel cv = new CanvasTunel();
        Ventana ventana = new Ventana(cv);
        ventana.setVisible(true);
        cv.repaint();
        
        for (int i = 0; i < n; i++) {
            if (r.nextInt(0, 2) == 0) {
                vehiculos[i] = new Coche(tunel, cv);
            } else {
                vehiculos[i] = new Thread(new Furgo(tunel, cv));
            }
        }
        
        for (int i = 0; i < n; i++)
            vehiculos[i].start();
        
        for (int i = 0; i < n; i++)
            try {
                vehiculos[i].join();
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
    }
}
