package pcd.practica9;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import tiendabici.CanvasTienda;
/**
 *
 * @author Pepe
 */
public class Tienda extends UnicastRemoteObject implements ICliente {
    
    private int nCompraEsperando;
    private boolean vendedorOcupado;
    private boolean tecnicoOcupado, tecnicoVendiendo;
    private CanvasTienda cv;
    
    public Tienda(CanvasTienda c) throws RemoteException {
        nCompraEsperando = 0;
        vendedorOcupado = false;
        tecnicoOcupado = false;
        tecnicoVendiendo = false;
        cv = c;
    }
    
    @Override
    public synchronized char entraComprador(int id) throws InterruptedException {
        cv.inserta('C', id);
        nCompraEsperando++;        
        tecnicoVendiendo = nCompraEsperando > 2;

        if (vendedorOcupado || tecnicoVendiendo && tecnicoOcupado) {
            System.out.println("Comprador " + Thread.currentThread().getId() + " espera");
            wait();
        }
        char vendedor = 'V';
        
        if (!vendedorOcupado) {
            vendedorOcupado = true;
        } else if (tecnicoVendiendo && !tecnicoOcupado) {
            tecnicoOcupado = true;
            vendedor = 'M';
        } else {
            System.out.println("Comprador " + Thread.currentThread().getId() + " Vendedor = " + vendedorOcupado + ", Tecnico vendiendo = " + tecnicoVendiendo + ", Tecnico ocupado = " + tecnicoOcupado);
            throw new InterruptedException("Error en comprador " + Thread.currentThread().getId());
        }
        nCompraEsperando--;
        
        cv.quita('C', id);
        cv.compra(vendedor, id);
        
        return vendedor;
    }
    
    @Override
    public synchronized void entraReparador(int id) throws InterruptedException {
        cv.inserta('R', id);
        if (tecnicoVendiendo || tecnicoOcupado) {
            wait();
        }
        tecnicoOcupado = true;
        
        cv.quita('R', id);
        cv.repara('M', id);
    }
    
    @Override
    public synchronized void saleComprador(char vendedor, int id) {
        if (vendedorOcupado)
            vendedorOcupado = false;
        else if (tecnicoVendiendo && tecnicoOcupado) {
            tecnicoOcupado = false;
        }
        notify();
        
        cv.finalizado(vendedor, id);
    }
    
    @Override
    public synchronized void saleReparador(int id) {
        tecnicoOcupado = false;
        notify();
        
        cv.finalizado('M', id);
    }
    
}
