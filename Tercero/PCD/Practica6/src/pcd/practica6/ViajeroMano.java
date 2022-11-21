package pcd.practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class ViajeroMano implements Runnable {

    private Semaphore rayos, perros, cuida;
    private mCanvas canvas;
    private int id;
    
    public ViajeroMano(Semaphore rayos, Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.rayos = rayos;
        this.perros = perros;
        this.cuida = cuida;
        canvas = cv;
    }
    
    @Override
    public void run() {
        id = (int) Thread.currentThread().getId();
        canvas.addPasMano(id);
        
        while (!canvas.esPrimeroPasMano(id)) {}
        
        try {
            rayos.acquire();
            canvas.salirColaPasMano();
            System.out.println("ViajeroMano " + Thread.currentThread().getId() + " pasa a rayosMano");
            //rayos.release();
            
            perros.acquire();
            rayos.release();
            System.out.println("ViajeroMano " + Thread.currentThread().getId() + " entra a perros");
            // perros
            cuida.release();
            
            System.out.println("ViajeroMaleta " + Thread.currentThread().getId() + " sale");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
