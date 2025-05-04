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
    private int id;
    
    public Reparador(Tienda t, CanvasTienda c, int i) {
        tienda = t;
        cv = c;
        r = new Random(System.currentTimeMillis());
        id = i;
    }
    
    @Override
    public Integer call() throws Exception {
        int t = r.nextInt(Const.TMIN, Const.TMAX);
        
        cv.inserta('R', id);
        tienda.entraReparador();
        cv.quita('R', id);
        
        System.out.println("Reparador " + id + " entra");
        cv.repara('M', id);
        
        try {
            Thread.sleep(t*1000);
        } catch(InterruptedException e) {
            System.out.println(id + " : " + e.getMessage());
        }
        
        tienda.saleReparador();
        cv.finalizado('M', id);
        System.out.println("Reparador " + id + " sale");
        
        return t;
    }
    
}
