package pcd.examen21.e1;

/**
 *
 * @author Pepe
 */
public class Tunel {

    private boolean libre[];
    private boolean hayFurgoSig;
    
    public Tunel() {
        libre = new boolean[] {true, true, true};
        hayFurgoSig = false;
    }
    
    public synchronized void entraCoche() throws InterruptedException {
        while (!libre[0]) {
            wait();
        }
        
        libre[0] = false;
    }
    
    public synchronized void entraFurgo() throws InterruptedException {
        while (!libre[0] || hayFurgoSig) {
            wait();
        }
        
        libre[0] = false;
        hayFurgoSig = true;
    }

    public synchronized void lavar() {
        libre[0] = true;
        libre[1] = false;
        notifyAll();
    }
    
    public synchronized void secar(boolean furgo) {
        libre[1] = true;
        if (furgo)
            hayFurgoSig = false;
        libre[2] = false;
    }
    
    public synchronized void saleCoche() {
        libre[2] = true;
    }
    
    public synchronized void saleFurgo() {
        libre[2] = true;
    }
    
}
