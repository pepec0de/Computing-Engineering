package pcd.practica6;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class ViajeroMano implements Runnable {

    private Semaphore rayos, perros, cuida;
    private mCanvas canvas;
    private int id;
    private Random r;
    
    public ViajeroMano(Semaphore rayos, Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.rayos = rayos;
        this.perros = perros;
        this.cuida = cuida;
        canvas = cv;
        r = new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run() {
        id = (int) Thread.currentThread().getId();
        
        canvas.addPasMano(id);
        
        while (!canvas.esPrimeroPasMano(id)) {}
        
        try {
            rayos.acquire();
            System.out.println("ViajeroMano " + id + " pasa a rayosMano");
            canvas.entraScanMano(id);
            Thread.sleep(r.nextInt(1, 4)*1000);
            
            perros.acquire();
            
            // Los equipajes no podrán ser sacados del equipo de rayos hasta que no esté alguno de los dos perros disponibles
            rayos.release();
            canvas.saleScanMano();
            System.out.println("ViajeroMano " + id + " sale de rayosMano");
            System.out.println("ViajeroMano " + id + " entra a perros");
            int p = canvas.entraPerros(id, 1);
            
            Thread.sleep(r.nextInt(1, 4)*1000);
            
            canvas.salePerros(p);
            cuida.release();
            
            System.out.println("ViajeroMaleta " + id + " sale");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
