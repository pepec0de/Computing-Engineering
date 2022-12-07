package pcd.practica9.remoto;

import java.rmi.*;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import pcd.practica9.IRemoto.IEjemplo;

/**
 *
 * @author Pepe
 */
public class Ejemplo extends UnicastRemoteObject implements IEjemplo {
    
    int contador;
    
    public Ejemplo(int i) throws RemoteException {
        super();
        contador = i;
    }
    
    @Override
    public int incrementa(String quien, int valor) throws RemoteException {
        contador += valor;

            System.out.println(quien + " incrementa " + valor + " = " + contador + "  " + getRef().toString());
        return contador;
    }
}
