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
        
        canvas.addFurgo((int) Thread.currentThread().getId());
        
        while (canvas.PrimerFurgo() != Thread.currentThread().getId()) {
            // esperamos
        }
        
        try {
            int p;
            p = tunel.entraFurgo();
            canvas.meterVehiculo('f', p);
            System.out.println("Furgo " + Thread.currentThread().getId() + " ENTRA " + p);
            Thread.sleep(r.nextInt(1, 4) * 1000);
            System.out.println("Furgo " + Thread.currentThread().getId() + " SALE " + p);
            tunel.saleFurgo(p);
            canvas.sacarVehiculo(p);
            
        } catch (InterruptedException ex) {
            
        }
    }
    
}
