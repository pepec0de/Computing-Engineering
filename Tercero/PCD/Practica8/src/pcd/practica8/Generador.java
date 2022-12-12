package pcd.practica8;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import tiendabici.CanvasTienda;

/**
 *
 * @author Pepe
 */
public class Generador {

    public static void main(String[] args) {
        CanvasTienda cv = null;
        try {
            cv = new CanvasTienda(Const.W, Const.H);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        Ventana v = new Ventana(cv);
        ExecutorService hCompra = Executors.newFixedThreadPool(10);
        ExecutorService hRepara = Executors.newFixedThreadPool(10);
        ArrayList<Future<Integer>> vCompra = new ArrayList<>();
        ArrayList<Future<Integer>> vRepara = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < 50; i++) {
            if (r.nextInt(1, 11) > 5) {
                vCompra.add(hCompra.submit(new Comprador(cv)));
            } else {
                vRepara.add(hRepara.submit(new Reparador(cv)));
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("FIN");
        
        System.exit(0);
    }
}
