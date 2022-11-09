package pcd.practica5;

import java.util.Random;

/**
 *
 * @author Pepe
 */
public class Coche extends Thread {
    
    private Tunel tunel;
    private CanvasTunel canvas;
    
    public Coche(Tunel tunel, CanvasTunel canvas) {
        this.tunel = tunel;
        this.canvas = canvas;
    }
    
    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        
        canvas.meterCoche((int) getId());
        
        while (canvas.PrimerCoche() != getId()) {
            // esperamos
        }
        
        try {
            tunel.entraCoche();
            System.out.println("Coche " + getId() + " ENTRA");
            Thread.sleep(r.nextInt(1, 4) * 1000);
            System.out.println("Coche " + getId() + " SALE");
            tunel.saleCoche();
            
        } catch (InterruptedException ex) {
            
        }
        
    }
    
}
