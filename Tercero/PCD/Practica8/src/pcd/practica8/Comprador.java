package pcd.practica8;

import java.util.Random;
import java.util.concurrent.Callable;
import tiendabici.CanvasTienda;

/**
 *
 * @author Pepe
 */
public class Comprador implements Callable<Integer> {

    private CanvasTienda cv;
    private Tienda tienda;
    private Random r;
    
    public Comprador(Tienda t, CanvasTienda c) {
        tienda = t;
        cv = c;
        r = new Random(System.currentTimeMillis());
    }
    
    @Override
    public Integer call() throws Exception {
        int id = (int) Thread.currentThread().getId();
        int t = r.nextInt(Const.TMIN, Const.TMAX);
        
        cv.inserta('C', id);
        char vendedor = tienda.entraComprador();
        cv.quita('C', id);
        
        System.out.println("Comprador " + id + " entra");
        cv.compra(vendedor, id);
        
        try {
            Thread.sleep(t*1000);
        } catch(InterruptedException e) {
            System.out.println(id + " : " + e.getMessage());
        }
        
        System.out.println("Comprador " + id + " sale");
        tienda.saleComprador();
        cv.finalizado(vendedor, id);
        
        return t;
    }
    
}