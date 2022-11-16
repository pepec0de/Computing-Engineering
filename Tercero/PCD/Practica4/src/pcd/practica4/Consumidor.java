package pcd.practica4;

import java.util.Random;

/**
 *
 * @author Pepe
 */

public class Consumidor implements Runnable {
    
    private ColaLenta lacola;
    
    public Consumidor(ColaLenta cola) {
        lacola = cola;
    }
    
    @Override
    public void run() {
        consumir();
    }
    
    public void consumir() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        
        try {
            for (int i = 0; i < 15; i++) {
                Thread.sleep(r.nextInt(1, 4)*1000);
                System.out.println("Consumidor " + Thread.currentThread().getId() + " extrae(" + i + "): " + lacola.Desacola());
            }
        } catch (Exception ex) {
            // Excepcion lanzada desde Desacola cuando se producen 3 intentos fallidos
            System.out.println("Consumidor " + Thread.currentThread().getId() + " 3 intentos");
            // Detenemos hilo y programa
        }
        System.out.println("Consumidor " + Thread.currentThread().getId() + " FINALIZANDO EJECUCION");
        System.exit(0);
    }
}
