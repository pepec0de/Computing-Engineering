/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pcd.practica9;

import java.net.MalformedURLException;
import java.rmi.*;

/**
 *
 * @author Pepe
 */
public class Comprador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException, MalformedURLException {
        ICliente cliente = (ICliente) Naming.lookup("rmi://localhost:1234/tienda");
        int id = (int) ProcessHandle.current().pid();
        
        System.out.println("Comprador " + id + " entra");
        char p = cliente.entraComprador(id);
        
        System.out.println("Comprador " + id + " repara");
        Thread.sleep(3000);
        
        cliente.saleComprador(p, id);
        System.out.println("Comprador " + id + " sale");
    }
    
}
