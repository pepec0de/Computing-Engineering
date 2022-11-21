package pcd.practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class Cuidador extends Thread {
    
    private Semaphore perros, cuida;
    private mCanvas canvas;
    
    public Cuidador(Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.perros = perros;
        this.cuida = cuida;
        this.canvas = cv;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                cuida.acquire();
                canvas.cuidaPerro();
                System.out.println("Cuidador " + getId() + " da galleta");
                // galleta
                perros.release();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
