/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd.examen19;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pepe
 */
public class Generador {

    public static void main(String[] args) {
        Piscina p = new Piscina();

        Thread[] hilos = new Thread[10];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            if (r.nextInt(10) < 6) {
                hilos[i] = new Ninyo(p, i+1);
                hilos[i].start();
            } else {
                hilos[i] = new Thread(new Adulto(p, i+1));
                hilos[i].start();
            }
            try {
                Thread.sleep(r.nextInt(1, 2)*1000);
            } catch (InterruptedException ex) {
            }
        }
        
        for (Thread t : hilos) {
            try {
                t.join();
            } catch (InterruptedException ex) {
            }
        }
        
        System.exit(0);
    }
}
