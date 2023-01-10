/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pcd.examen21.e3;

import pcd.examen21.e1.*;

/**
 *
 * @author Pepe
 */
public class Examen21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Tunel tunel = new Tunel();
        Thread[] vehiculos = new Thread[7];
        int nCoches = 4, nFurgos = 3, i;
        
        for (i = 0; i < nCoches; i++) {
            vehiculos[i] = new Thread(new Coche(i, tunel));
            vehiculos[i].start();
        }
        
        for (i = nCoches; i < nCoches+nFurgos; i++) {
            vehiculos[i] = new Thread(new Furgoneta(i, tunel));
            vehiculos[i].start();
        }
        
        for (i = 0; i < 7; i++)
            vehiculos[i].join();
        
        System.exit(0);
        
    }
    
}
