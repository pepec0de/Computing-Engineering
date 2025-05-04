package pcd.examen21.e3;

import java.util.concurrent.Semaphore;


/**
 *
 * @author Pepe
 */
public class Furgoneta implements Runnable {

    private int id;
    private Semaphore prelavado, lavado, secado;

    public Furgoneta(int id, Semaphore prelavado, Semaphore lavado, Semaphore secado) {
        this.id = id;
        this.prelavado = prelavado;
        this.lavado = lavado;
        this.secado = secado;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Furgo " + id + " esperando");
            
            prelavado.acquire();
            
            // Prelavado
            System.out.println("Furgo " + id + " Pre");
            Thread.sleep(2000);
            
            lavado.acquire();
            prelavado.release();
            
            // Lavado
            System.out.println("Furgo " + id + " Lavando");
            Thread.sleep(2000);
            
            secado.acquire();
            lavado.release();
            
            // Secado
            System.out.println("Furgo " + id + " Secando");
            Thread.sleep(2000);
            
            secado.release();
            
            System.out.println("Furgo " + id + " sale");
        } catch (InterruptedException ex) {
            System.out.println("ERROR: Furgo " + id + ex.getMessage());
        }
    }
    
}
