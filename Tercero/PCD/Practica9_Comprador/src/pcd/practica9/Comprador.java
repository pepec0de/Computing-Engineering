/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pcd.practica9;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Pepe
 */
public class Comprador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
        Registry r = LocateRegistry.getRegistry("localhost", 1234);
        ICliente cliente = (ICliente) r.lookup("tienda");
        int id = (int) ProcessHandle.current().pid();
        
        System.out.println("Comprador " + id + " entra");
        char p = cliente.entraComprador(id);
        
        System.out.println("Comprador " + id + " repara");
        Thread.sleep(3000);
        
        cliente.saleComprador(p, id);
        System.out.println("Comprador " + id + " sale");
    }
    
}
