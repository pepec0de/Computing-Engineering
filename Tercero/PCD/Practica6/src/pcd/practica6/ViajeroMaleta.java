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
            System.out.println("ViajeroMaleta " + getId() + " pasa rayosMaleta");
            int p = canvas.entraScanMaleta(id);

            sleep(1000);
            
            perros.acquire();
            
            // Liberamos la posicion del escaner antes del release para evitar conflictos
            canvas.saleScanMaleta(p);
            rayos.release();
            System.out.println("ViajeroMaleta " + getId() + " entra a perros");
            p = canvas.entraPerros(id, 2);
            
            Thread.sleep(3000);
            
            canvas.salePerros(p);
            cuida.release();
            
            System.out.println("ViajeroMaleta " + getId() + " sale");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
