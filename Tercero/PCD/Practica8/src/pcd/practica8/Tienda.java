package pcd.practica8;

/**
 *
 * @author Pepe
 */
public class Tienda {
    
    private int nCompraEsperando;
    private boolean vendedorOcupado;
    private boolean mecanicoOcupado;
    
    public Tienda() {
        nCompraEsperando = 0;
        vendedorOcupado = false;
        mecanicoOcupado = false;
    }
    
    public synchronized char entraComprador() throws InterruptedException {
        char vendedor = 'V';
        
        nCompraEsperando++;

        while (vendedorOcupado && (nCompraEsperando <= 2 || mecanicoOcupado)) {
            System.out.println("Comprador " + Thread.currentThread().getId() + " espera");
            wait();
        }
        
        if (!vendedorOcupado) {
            vendedorOcupado = true;
        } else if (!mecanicoOcupado) {
            vendedor = 'M';
            mecanicoOcupado = true;
        } else {
            throw new InterruptedException("Error en comprador " + Thread.currentThread().getId());
        }
        
        nCompraEsperando--;
        
        return vendedor;
    }
    
    public synchronized void entraReparador() throws InterruptedException {
        
        if (mecanicoOcupado) {
            wait();
        }
        
        mecanicoOcupado = true;
    }
    
    public synchronized void saleComprador(char p) {
        if (p == 'V') {
            vendedorOcupado = false;
        } else if (p == 'M') {
            mecanicoOcupado = false;
        }
        notifyAll();
    }
    
    public synchronized void saleReparador() {
        mecanicoOcupado = false;
        notifyAll();
    }
    
    
}
