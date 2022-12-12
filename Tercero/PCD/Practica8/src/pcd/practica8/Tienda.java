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
        char p = 0;
        
        nCompraEsperando++;
        
        if (nCompraEsperando > 2) {
            tecnicoVendiendo = true;
        }
        
        if (vendedorOcupado) {
            if (!tecnicoVendiendo)
                hCompra.wait();
            else if (tecnicoOcupado) {
                hCompra.wait();
            } else {
                tecnicoOcupado = true;
            }
        } else {
            vendedorOcupado = true;
        }
        
        nCompraEsperando--;
        
        return p;
    }
    
    public synchronized void entraReparador() throws InterruptedException {
        
        if (tecnicoOcupado) {
            hRepara.wait();
        }
        
        
    }
    
    public synchronized void saleComprador() {
        vendedorOcupado = false;
        if (tecnicoVendiendo && tecnicoOcupado) {
            tecnicoOcupado = false;
        }
        hCompra.notify();
    }
    
    public synchronized void saleReparador() {
        tecnicoOcupado = false;
        hRepara.notify();
    }
    
    
}
