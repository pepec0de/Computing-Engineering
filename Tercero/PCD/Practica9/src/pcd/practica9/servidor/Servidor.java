package pcd.practica9.servidor;

import java.io.IOException;
import java.rmi.*;
import pcd.practica9.remoto.Ejemplo;
import java.rmi.registry.*;
/**
 *
 * @author Pepe
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, IOException {
        Ejemplo e = new Ejemplo(0);
        int v = e.incrementa("main", 7);
        System.out.println("Incremento ejemplo = " + v);
        
        Registry reg = LocateRegistry.createRegistry(2022);
        reg.bind("oremoto",e);
        
        
        System.out.println("Pulsa Intro para finalizar...");
        System.in.read();
        System.exit(0);
    }
    
}
