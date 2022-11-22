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
            System.out.println("ViajeroMano " + Thread.currentThread().getId() + " pasa a rayosMano");
            canvas.entraScanMano(id);
            Thread.sleep(1000);
            
            perros.acquire();
            
            // Los equipajes no podrán ser sacados del equipo de rayos hasta que no esté alguno de los dos perros disponibles
            rayos.release();
            canvas.saleScanMano();
            System.out.println("ViajeroMano " + Thread.currentThread().getId() + " entra a perros");
            int p = canvas.entraPerros(id, 1);
            
            Thread.sleep(3000);
            
            canvas.salePerros(p);
            cuida.release();
            
            System.out.println("ViajeroMaleta " + Thread.currentThread().getId() + " sale");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
