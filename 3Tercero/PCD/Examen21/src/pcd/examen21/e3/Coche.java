package pcd.examen21.e3;

import java.util.concurrent.Semaphore;


/**
 *
 * @author Pepe
 */
public class Coche implements Runnable {

    private int id;
    private Semaphore prelavado, lavado, secado;

    public Coche(int id, Semaphore prelavado, Semaphore lavado, Semaphore secado) {
        this.id = id;
        this.prelavado = prelavado;
        this.lavado = lavado;
        this.secado = secado;
    }

    @Override
    public void run() {
        try {
            System.out.println("Coche " + id + " esperando");
            
            prelavado.acquire();
            
            // Prelavado
            System.out.println("Coche " + id + " Pre");
            Thread.sleep(2000);
            
            lavado.acquire();
            prelavado.release();
            
            // Lavado
            System.out.println("Coche " + id + " Lavando");
            Thread.sleep(2000);
            
            secado.acquire();
            lavado.release();
            
            // Secado
            System.out.println("Coche " + id + " Secando");
            Thread.sleep(2000);
            
            secado.release();
            
            System.out.println("Coche " + id + " sale");
        } catch (InterruptedException ex) {
            System.out.println("ERROR: Coche " + id + ex.getMessage());
        }
        
    }
    
}
