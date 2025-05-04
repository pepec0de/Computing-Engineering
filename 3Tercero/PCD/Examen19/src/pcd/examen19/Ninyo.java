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
public class Ninyo extends Thread {
    
    private Piscina piscina;
    private int id;
    
    public Ninyo(Piscina p, int id) {
        piscina = p;
        this.id = id;
    }
    
    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        try {
            System.out.println("Niño " + id + " quiere curso");
            piscina.EntraNinyo();
            
            System.out.println("Niño " + id + " entra");
            Thread.sleep(r.nextInt(2, 4)*1000);
            
            piscina.SaleNinyo();
            
            System.out.println("Niño " + id + " sale");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Niño " + id + " no entra a la piscina");
        }
        
    }    
}
