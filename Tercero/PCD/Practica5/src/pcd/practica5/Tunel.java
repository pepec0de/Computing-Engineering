package pcd.practica5;

/**
 *
 * @author Pepe
 */
public class Tunel {
    private int libres;
    private int nfurgos;
    private boolean hayFurgoCentro;
    
    public Tunel() {
        libres = 3;
        nfurgos = 0;
    }
    
    public synchronized char entraCoche() throws InterruptedException {
        while (libres == 0) {
            wait();
        }
        libres--;
        return 0;
    }
    
    public synchronized char entraFurgo() throws InterruptedException {
        while (libres == 0 || hayFurgoCentro || nfurgos == 2) {
            wait();
        }
        nfurgos++;
        libres--;
        return 0;
    }
    
    public synchronized void saleFurgo() {
        if (hayFurgoCentro) {
            hayFurgoCentro = false;
        }
        libres++;
        nfurgos--;
        notifyAll();
    }
    
    public synchronized void saleCoche() {
        libres++;
        notifyAll();
    }
}
