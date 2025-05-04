package pcd.practica9;

import java.rmi.*;
import java.rmi.registry.*;
/**
 *
 * @author Pepe
 */
public class Reparador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
        Registry r = LocateRegistry.getRegistry("localhost", 1234);
        String[] oferta = r.list();
        for (int i = 0; i < oferta.length; i++) {
        System.out.println("Elemento " + i + " del registro: " + oferta[i]);
        }
        ICliente cliente = (ICliente) r.lookup("tienda");
        
        int id = (int) ProcessHandle.current().pid();
        
        System.out.println("Reparador " + id + " entra");
        cliente.entraReparador(id);
        
        System.out.println("Reparador " + id + " repara");
        Thread.sleep(3000);
        
        cliente.saleReparador(id);
        System.out.println("Reparador " + id + " sale");
    }
    
}
