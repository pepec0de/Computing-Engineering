package pcd.practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pepe
 */
public class ViajeroMaleta extends Thread {
    
    private Semaphore rayos, perros, cuida;
    private mCanvas canvas;
    private int id;
    
    public ViajeroMaleta(Semaphore rayos, Semaphore perros, Semaphore cuida, mCanvas cv) {
        this.rayos = rayos;
        this.perros = perros;
        this.cuida = cuida;
        this.canvas = cv;
        this.id = (int) getId();
    }
    
    @Override
    public void run() {
        canvas.addPasMaleta(id);
        
        while (!canvas.esPrimeroPasMaleta(id)) {}
        /*
        try {
           
            //sleep(5000);
            rayos.acquire();
            System.out.println("ViajeroMaleta " + getId() + " pasa rayosMaleta");
            //int p = canvas.entraScanMaleta(id);
            //rayos.release();
            // espera
            perros.acquire();
            //canvas.entraPerrosMaleta(id);
            rayos.release();
            // Tiempo espera
            //canvas.salir();
            
            System.out.println("ViajeroMaleta " + getId() + " entra a perros");
            
            cuida.release();
            
            System.out.println("ViajeroMaleta " + getId() + " sale");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }*/
    }
}
