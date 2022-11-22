package pcd.practica7;

import java.util.Random;

/**
 *
 * @author Pepe
 */
public class Coche extends Thread {

    private Tunel tunel;
    private CanvasTunel canvas;

    public Coche(Tunel tunel, CanvasTunel canvas) {
        this.tunel = tunel;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());

        canvas.addCoche((int) getId());

        while (canvas.PrimerCoche() != getId()) {
            // esperamos
            
        }
        canvas.repaint();

        try {
            int p;
            p = tunel.entraCoche();
            canvas.meterVehiculo('c', p);
            System.out.println("Coche " + getId() + " ENTRA " + p);
            Thread.sleep(r.nextInt(1, 4) * 1000);
            System.out.println("Coche " + getId() + " SALE " + p);
            tunel.saleCoche(p);
            canvas.sacarVehiculo(p);

        } catch (InterruptedException ex) {

        }

    }

}
