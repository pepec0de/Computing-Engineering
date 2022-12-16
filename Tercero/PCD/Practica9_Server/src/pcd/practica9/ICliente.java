/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pcd.practica9;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Pepe
 */
public interface ICliente extends Remote {
    
    public char entraComprador(int id) throws RemoteException, InterruptedException;
    public void entraReparador(int id) throws RemoteException, InterruptedException;
    public void saleComprador(char vendedor, int id) throws RemoteException;
    public void saleReparador(int id) throws RemoteException;
    
}
