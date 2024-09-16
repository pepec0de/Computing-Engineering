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
public class Adulto implements Runnable {

    private Piscina piscina;
    private int id;
    
    public Adulto(Piscina p, int i) {
        piscina = p;
        id = i;
    }
    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        try {
            System.out.println("Adulto " + id + " quiere curso");
            
            piscina.entraAdulto();
            
            System.out.println("Adulto " + id + " entra");
            Thread.sleep(r.nextInt(3, 6)*1000);
            
            piscina.SaleAdulto();
        
            System.out.println("Adulto " + id + " sale");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
