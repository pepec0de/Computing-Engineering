package pcd.practica8;

import java.util.Random;
import java.util.concurrent.Callable;
import tiendabici.CanvasTienda;

/**
 *
 * @author Pepe
 */
public class Reparador implements Callable<Integer> {

    private CanvasTienda cv;
    private Random r;
    
    public Reparador(CanvasTienda c) {
        cv = c;
        r = new Random(System.currentTimeMillis());
    }
    
    @Override
    public Integer call() throws Exception {
        long id = Thread.currentThread().getId();
        int t = r.nextInt(Const.TMIN, Const.TMAX);
        
        
        try {
            Thread.sleep(t*1000);
        } catch(InterruptedException e) {
            System.out.println(id + " : " + e.getMessage());
        }
        
        
        
        return t;
    }
    
}
