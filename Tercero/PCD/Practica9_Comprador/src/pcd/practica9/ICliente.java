/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pcd.practica9;

/**
 *
 * @author Pepe
 */
public interface ICliente {
    
    public char entraComprador(int id) throws InterruptedException;
    public void entraReparador(int id) throws InterruptedException;
    public void saleComprador(char vendedor, int id);
    public void saleReparador(int id);
    
}
