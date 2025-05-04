package pcd.practica6;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class Cuidador extends Thread {
    
    private Semaphore perros, cuida;
    private mCanvas canvas;
    private Random r;
    
    public Cuidador(Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.perros = perros;
        this.cuida = cuida;
        this.canvas = cv;
        r = new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run() {
        int id = (int) getId();
        while (true) {
            try {
                cuida.acquire();
                int p = canvas.cuidaPerro(id);
                System.out.println("Cuidador " + id + " da galleta");
                // galleta
                sleep(r.nextInt(1, 4)*1000);
                canvas.perroLibre(p);
                perros.release();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
