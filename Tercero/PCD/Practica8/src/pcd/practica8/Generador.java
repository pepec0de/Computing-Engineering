package pcd.practica8;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiendabici.CanvasTienda;

/**
 *
 * @author Pepe
 */
public class Generador {
    
    public static void main(String[] args) {
        CanvasTienda cv = null;
        try { cv = new CanvasTienda(Const.W, Const.H); } catch (Exception e) {}
        Ventana v = new Ventana(cv);
        ExecutorService hCompra = Executors.newFixedThreadPool(10);
        ExecutorService hRepara = Executors.newFixedThreadPool(10);
        ArrayList<Future<Integer>> vCompra = new ArrayList<>();
        ArrayList<Future<Integer>> vRepara = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());
        
        for (int i = 0; i < 50; i++) {
            if (r.nextInt(1, 11) > 5) {
                vCompra.add(hCompra.submit(new Comprador()));
            } else {
                vRepara.add(hRepara.submit(new Reparador()));
            }
            
            try {Thread.sleep(500);} catch (Exception e) {e.printStackTrace();}
        }
        System.out.println("FIN");
    }
}
