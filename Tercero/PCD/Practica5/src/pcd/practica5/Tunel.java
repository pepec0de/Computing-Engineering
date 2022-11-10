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
    
    public synchronized int entraCoche() throws InterruptedException {
        while (libres == 0) {
            wait();
        }
        int r = 2;
        if (libres == 3) {
            r = 0; // arriba
        } else if (nfurgos == 2) {
            r = 1; // centro
        } else if (hayFurgoCentro) {
            if (libres == 2) {
                r = 0;
            }
        }
        libres--;
        return r;
    }
    
    public synchronized int entraFurgo() throws InterruptedException {
        while (libres == 0 || hayFurgoCentro || nfurgos == 2) {
            wait();
        }
        
        int r = 0;
        if (nfurgos == 0) {
            r = 1;
            hayFurgoCentro = true;
        } else if (nfurgos == 1 && !hayFurgoCentro) {
            r = 2;
        } 
        nfurgos++;
        libres--;
        return r;
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
