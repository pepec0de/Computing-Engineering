package pcd.practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class Cuidador extends Thread {
    
    private Semaphore perros, cuida;
    
    public Cuidador(Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.perros = perros;
        this.cuida = cuida;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                cuida.acquire();
                // galleta
                perros.release();
            } catch (InterruptedException ex) {
            }
        }
        
    }
}
