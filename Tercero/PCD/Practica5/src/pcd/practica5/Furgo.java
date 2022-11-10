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
public class Furgo implements Runnable {

    private Tunel tunel;
    private CanvasTunel canvas;
    
    public Furgo(Tunel tunel, CanvasTunel canvas) {
        this.tunel = tunel;
        this.canvas = canvas;
    }
    
    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        
        canvas.meterFurgo((int) Thread.currentThread().getId());
        
        while (canvas.PrimerFurgo() != Thread.currentThread().getId()) {
            // esperamos
        }
        
        try {
            int p = tunel.entraFurgo();
            canvas.meterVehiculo('v', p);
            System.out.println("Furgo " + Thread.currentThread().getId() + " ENTRA " + p);
            Thread.sleep(r.nextInt(1, 4) * 1000);
            System.out.println("Furgo " + Thread.currentThread().getId() + " SALE " + p);
            tunel.saleCoche();
            
        } catch (InterruptedException ex) {
            
        }
    }
    
}
