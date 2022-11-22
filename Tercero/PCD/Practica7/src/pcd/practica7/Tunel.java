package pcd.practica7;

/**
 *
 * @author Pepe
 */
public class Tunel {

    private int nfurgos, libres;
    private boolean hayFurgoCentro;
    private boolean libre[];
    
    public Tunel() {
        nfurgos = 0;
        libre = new boolean[3];
        for (int i = 0; i < 3; i++)
            libre[i] = true;
        libres = 3;
    }
    
    public synchronized int entraCoche() throws InterruptedException {
        while (libres == 0) {
            wait();
        }
        
        int r = 0;
        while (r < 3 && !libre[r]) r++;
        libre[r] = false;
        libres--;
        
        return r;
    }
    
    public synchronized int entraFurgo() throws InterruptedException {
        while (libres == 0 || hayFurgoCentro || nfurgos == 2) {
            wait();
        }
        
        int r = 0;
        if (nfurgos == 1) {
            if (libre[0]) {
                r = 0;
            } else {
                r = 2;
            }
        } else { // nfurgos == 0
            while (r < 3 && !libre[r]) r++;
        }
        
        if (r == 1)
            hayFurgoCentro = true;
        
        libre[r] = false;
        nfurgos++;
        libres--;
        return r;
    }
    
    public synchronized void saleFurgo(int p) {
        if (p == 1) {
            hayFurgoCentro = false;
        }
        libre[p] = true;
        libres++;
        nfurgos--;
        notify();
    }
    
    public synchronized void saleCoche(int p) {
        libre[p] = true;
        libres++;
        notify();
    }
}
