package pcd.practica8;

/**
 *
 * @author Pepe
 */
public class Tienda {
    
    private int nCompraEsperando;
    private boolean vendedorOcupado;
    private boolean mecanicoOcupado, mecanicoVendiendo;
    
    public Tienda() {
        nCompraEsperando = 0;
        vendedorOcupado = false;
        mecanicoOcupado = false;
        mecanicoVendiendo = false;
    }
    
    public synchronized char entraComprador() throws InterruptedException {
        char vendedor = 'V';
        
        nCompraEsperando++;

        if (vendedorOcupado || mecanicoVendiendo && mecanicoOcupado) {
            System.out.println("Comprador " + Thread.currentThread().getId() + " espera");
            wait();
        }
        
        if (!mecanicoOcupado && nCompraEsperando > 2)
            mecanicoVendiendo = true;
        
        if (!vendedorOcupado) {
            vendedorOcupado = true;
        } else if (mecanicoVendiendo && !mecanicoOcupado) {
            System.out.println("Mecanico se pone a vender");
            vendedor = 'M';
            mecanicoOcupado = true;
        } else {
            throw new InterruptedException("Error en comprador " + Thread.currentThread().getId());
        }
        
        nCompraEsperando--;
        
        return vendedor;
    }
    
    public synchronized void entraReparador() throws InterruptedException {
        
        if (mecanicoVendiendo || mecanicoOcupado) {
            wait();
        }
        
        mecanicoOcupado = true;
    }
    
    public synchronized void saleComprador() {
        if (vendedorOcupado)
            vendedorOcupado = false;
        else if (mecanicoVendiendo && mecanicoOcupado) {
            if (nCompraEsperando <= 2) {
                mecanicoVendiendo = false;
            }
            mecanicoOcupado = false;
        }
        notify();
    }
    
    public synchronized void saleReparador() {
        mecanicoOcupado = false;
        notify();
    }
    
    
}
