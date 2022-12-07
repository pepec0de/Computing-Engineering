/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pcd.practica9.IRemoto;

import java.rmi.*;

/**
 *
 * @author Pepe
 */
public interface IEjemplo extends Remote {
    public int incrementa(String quien, int valor) throws RemoteException;
}
