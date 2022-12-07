package pcd.practica9.cliente;


import java.net.MalformedURLException;
import java.rmi.*;
import pcd.practica9.IRemoto.IEjemplo;
import pcd.practica9.remoto.Ejemplo;
/**
 *
 * @author Pepe
 */
public class Cliente {
    
    public static void main(String[] args) throws NotBoundException, RemoteException, MalformedURLException {
        Remote r = Naming.lookup("rmi://localhost:2022/oremoto");
        //java.net.Inet4Address.getLocalHost().getHostAddress();
        IEjemplo e = (IEjemplo) r;
        e.incrementa("cliente", 18);
        System.out.println(r.toString());
    }
}
