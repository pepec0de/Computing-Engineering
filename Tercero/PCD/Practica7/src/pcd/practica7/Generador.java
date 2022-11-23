package pcd.practica7;

import java.util.Random;

/**
 *
 * @author Pepe
 */
public class Generador {

    public static void main(String[] args) {
        int n = 50;
        Thread vehiculos[] = new Thread[n];
        Random r = new Random(System.currentTimeMillis());
        CanvasTunel cv = new CanvasTunel();
        Tunel tunel = new Tunel();
        Ventana ventana = new Ventana(cv);
        ventana.setVisible(true);

        for (int i = 0; i < n; i++) {
//            if (r.nextInt(0, 2) == 0) {
//                vehiculos[i] = new Coche(tunel, cv);
//            } else {
                vehiculos[i] = new Thread(new Furgo(tunel, cv));
            //}
        }
        
        for (int i = 0; i < n; i++) {
            vehiculos[i].start();
            try {
                Thread.sleep(r.nextInt(1, 3)*1000);
            } catch (InterruptedException ex) {ex.printStackTrace();}
        }
        
        for (int i = 0; i < n; i++)
            try {
                vehiculos[i].join();
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        System.out.println("Fin de hilos");
        
    }
    
}
