package pcd.practica8;

import java.util.concurrent.ExecutorService;


/**
 *
 * @author Pepe
 */
public class Tienda {
    
    private ExecutorService hCompra, hRepara;
    private int nCompraEsperando;
    private boolean vendedorOcupado;
    private boolean tecnicoOcupado, tecnicoVendiendo;
    
    public Tienda(ExecutorService hCompra, ExecutorService hRepara) {
        this.hCompra = hCompra;
        this.hRepara = hRepara;
        nCompraEsperando = 0;
        vendedorOcupado = false;
        tecnicoOcupado = false;
        tecnicoVendiendo = false;
    }
    
    public synchronized char entraComprador() throws InterruptedException {
        char vendedor = 'V';
        
        nCompraEsperando++;        
        tecnicoVendiendo = nCompraEsperando > 2;
        
        if (vendedorOcupado || tecnicoVendiendo && tecnicoOcupado) {
            System.out.println("Comprador " + Thread.currentThread().getId() + " espera");
            wait();
        }
        
        if (!vendedorOcupado) {
            vendedorOcupado = true;
        } else if (tecnicoVendiendo && !tecnicoOcupado) {
            vendedor = 'M';
            tecnicoOcupado = true;
        } else {
            System.out.println("Comprador " + Thread.currentThread().getId() + " Vendedor = " + vendedorOcupado + ", Tecnico vendiendo = " + tecnicoVendiendo + ", Tecnico ocupado = " + tecnicoOcupado);
            throw new InterruptedException("AYAYAYAYAYAY no");
        }
        
        nCompraEsperando--;
        
        return vendedor;
    }
    
    public synchronized void entraReparador() throws InterruptedException {
        
        if (tecnicoVendiendo || tecnicoOcupado) {
            wait();
        }
        
        tecnicoOcupado = true;
    }
    
    public synchronized void saleComprador() {
        if (vendedorOcupado)
            vendedorOcupado = false;
        else if (tecnicoVendiendo && tecnicoOcupado) {
            tecnicoOcupado = false;
        }
        notify();
    }
    
    public synchronized void saleReparador() {
        tecnicoOcupado = false;
        notify();
    }
    
    
}
