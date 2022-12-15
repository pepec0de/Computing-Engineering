package pcd.practica9;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import tiendabici.CanvasTienda;

/**
 *
 * @author Pepe
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        int W = 800, H = 600;
        CanvasTienda cv = null;
        try {
            cv = new CanvasTienda(W, H);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        Ventana v = new Ventana(cv);
        v.setSize(W, H);
        
        Tienda tienda = new Tienda(cv);
        
        Registry r = LocateRegistry.createRegistry(1234);
        r.rebind("tienda", tienda);
    }
    
}
