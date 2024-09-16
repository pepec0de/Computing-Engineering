package pcd.examen21.e3;

import java.util.concurrent.Semaphore;


/**
 *
 * @author Pepe
 */
public class Examen21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Thread[] vehiculos = new Thread[7];
        int nCoches = 4, nFurgos = 3, i;
        Semaphore prelavado = new Semaphore(1), 
                lavado = new Semaphore(1), 
                secado = new Semaphore(1);
        
        for (i = 0; i < nCoches; i++) {
            vehiculos[i] = new Thread(new Coche(i, prelavado, lavado, secado));
            vehiculos[i].start();
        }
        
        for (i = nCoches; i < nCoches+nFurgos; i++) {
            vehiculos[i] = new Thread(new Furgoneta(i, prelavado, lavado, secado));
            vehiculos[i].start();
        }
        
        for (i = 0; i < 7; i++)
            vehiculos[i].join();
        
        System.exit(0);
        
    }
    
}
