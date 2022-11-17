package pcd.practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class ViajeroMano implements Runnable {

    private Semaphore rayos, perros, cuida;
    
    public ViajeroMano(Semaphore rayos, Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.rayos = rayos;
        this.perros = perros;
        this.cuida = cuida;
    }
    
    @Override
    public void run() {
        try {
            rayos.acquire();
            // rayos
            rayos.release();
            
            perros.acquire();
            // perros
            cuida.release();
        } catch (InterruptedException ex) {
        }
    }
    
}
