package pcd.practica4;

import java.util.Random;

/**
 *
 * @author Pepe
 */
public class Productor extends Thread {

    private ColaLenta lacola;
    private int id;

    public Productor(ColaLenta cola) {
        super();
        lacola = cola;
    }

    @Override
    public void run() {
        producir();
    }

    public void producir() {
        Random r = new Random();
        r.setSeed(System.nanoTime());

        int num;
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(r.nextInt(1, 4) * 1000);
                num = r.nextInt(10, 100);
                
                System.out.println("Productor " + getId() + " introduce(" + i + "): " + num);
                lacola.Acola(num);
            } catch (Exception ex) {
                // Excepcion lanzada desde Desacola cuando se producen 3 intentos fallidos
                System.out.println("Productor " + getId() + " 3 intentos FINALIZANDO");
                stop();
            }
        }
    }

}
