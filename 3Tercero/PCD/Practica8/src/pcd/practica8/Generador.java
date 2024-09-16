package pcd.practica8;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
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
        Tienda tienda = new Tienda();
        ArrayList<Future<Integer>> vCompra = new ArrayList<>();
        ArrayList<Future<Integer>> vRepara = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < 50; i++) {
            if (r.nextInt(10) > 5) {
                vCompra.add(hCompra.submit(new Comprador(tienda, cv, i)));
            } else {
                vRepara.add(hRepara.submit(new Reparador(tienda, cv, i)));
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        
        hCompra.shutdown();
        hRepara.shutdown();
        try {
            hCompra.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            hRepara.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        
        int compra = 0, repara = 0;
        for (Future<Integer> f : vCompra) {
            try {
                compra += f.get();
            } catch (InterruptedException | ExecutionException ex) {
                System.err.println(ex.getMessage());
            }
        }
        for (Future<Integer> f : vRepara) {
            try {
                repara += f.get();
            } catch (InterruptedException | ExecutionException ex) {
                System.err.println(ex.getMessage());
            }
        }
        System.out.println("Total compra = " + compra + "\nTotal repara = " + repara);
        System.out.println("FIN");
        System.exit(0);
    }
}
