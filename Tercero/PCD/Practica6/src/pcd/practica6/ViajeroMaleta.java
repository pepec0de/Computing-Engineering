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
        
        try {
            rayos.acquire();
            canvas.salirColaPasMaleta();
            System.out.println("ViajeroMaleta " + getId() + " pasa rayosMaleta");
            canvas.entraScanMaleta();
            //rayos.release();
                        
            perros.acquire();
            rayos.release();
            canvas.entraPerros();
            // Tiempo espera
            canvas.salir();
            
            System.out.println("ViajeroMaleta " + getId() + " entra a perros");
            
            cuida.release();
            
            System.out.println("ViajeroMaleta " + getId() + " sale");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
