package pcd.practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class ViajeroMaleta extends Thread {
    
    private Semaphore rayos, perros, cuida;
    
    public ViajeroMaleta(Semaphore rayos, Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.rayos = rayos;
        this.perros = perros;
        this.cuida = cuida;
    }
    
    @Override
    public void run() {
        try {
            rayos.acquire();
            System.out.println("ViajeroMaleta " + getId() + " entra a rayos");
            rayos.release();
            
            perros.acquire();
            System.out.println("ViajeroMaleta " + getId() + " entra a perros");
            cuida.release();
            
            System.out.println("ViajeroMaleta " + getId() + " sale");
        } catch (InterruptedException ex) {
            
        }
    }
}
