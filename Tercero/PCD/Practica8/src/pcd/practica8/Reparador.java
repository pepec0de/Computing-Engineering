package pcd.practica8;

import java.util.Random;
import java.util.concurrent.Callable;
import tiendabici.CanvasTienda;

/**
 *
 * @author Pepe
 */
public class Reparador implements Callable<Integer> {

    private Tienda tienda;
    private CanvasTienda cv;
    private Random r;
    
    public Reparador(Tienda t, CanvasTienda c) {
        tienda = t;
        cv = c;
        r = new Random(System.currentTimeMillis());
    }
    
    @Override
    public Integer call() throws Exception {
        int id = (int) Thread.currentThread().getId();
        int t = r.nextInt(Const.TMIN, Const.TMAX);
        
        cv.inserta('R', id);
        tienda.entraReparador();
        cv.quita('R', id);
        
        System.out.println("Reparador " + Thread.currentThread().getId() + " entra");
        cv.repara('M', id);
        
        try {
            Thread.sleep(t*1000);
        } catch(InterruptedException e) {
            System.out.println(id + " : " + e.getMessage());
        }
        
        tienda.saleReparador();
        cv.finalizado('M', id);
        System.out.println("Reparador " + Thread.currentThread().getId() + " sale");
        
        return t;
    }
    
}
