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
    private boolean mecanicoOcupado, mecanicoVendiendo;
    private CanvasTienda cv;
    
    public Tienda(CanvasTienda c) throws RemoteException {
        nCompraEsperando = 0;
        vendedorOcupado = false;
        mecanicoOcupado = false;
        mecanicoVendiendo = false;
        cv = c;
    }
    
    @Override
    public synchronized char entraComprador(int id) throws RemoteException, InterruptedException {
        char vendedor = 'V';
        cv.inserta('C', id);
        nCompraEsperando++;        

        if (vendedorOcupado && mecanicoVendiendo && mecanicoOcupado) {
            System.out.println("Comprador " + Thread.currentThread().getId() + " espera");
            wait();
        }
        
        if (!mecanicoOcupado && nCompraEsperando > 2)
            mecanicoVendiendo = true;
        
        if (!vendedorOcupado) {
            vendedorOcupado = true;
        } else if (mecanicoVendiendo && !mecanicoOcupado) {
            mecanicoOcupado = true;
            vendedor = 'M';
        } else {
            throw new InterruptedException("Error en comprador " + Thread.currentThread().getId());
        }
        nCompraEsperando--;
        
        cv.quita('C', id);
        cv.compra(vendedor, id);
        
        return vendedor;
    }
    
    @Override
    public synchronized void entraReparador(int id) throws RemoteException, InterruptedException {
        cv.inserta('R', id);
        if (mecanicoVendiendo || mecanicoOcupado) {
            wait();
        }
        mecanicoOcupado = true;
        
        cv.quita('R', id);
        cv.repara('M', id);
    }
    
    @Override
    public synchronized void saleComprador(char vendedor, int id) throws RemoteException {
        if (vendedorOcupado)
            vendedorOcupado = false;
        else if (mecanicoVendiendo && mecanicoOcupado) {
            mecanicoOcupado = false;
        }
        notify();
        
        cv.finalizado(vendedor, id);
    }
    
    @Override
    public synchronized void saleReparador(int id) throws RemoteException{
        mecanicoOcupado = false;
        notify();
        
        cv.finalizado('M', id);
    }
    
}
