package pcd.practica7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Pepe
 */
public class Tunel {
    
    final Lock mutex = new ReentrantLock();
    final Condition cCoches = mutex.newCondition(), 
                    cFurgos = mutex.newCondition();
    
    int ncoches, nfurgos, libres, ncochesEspera, nfurgosEspera;
    boolean libre[];
    
    public Tunel() {
        ncoches = 0;
        nfurgos = 0;
        ncochesEspera = 0;
        nfurgosEspera = 0;
        libres = 3;
        libre = new boolean []{true, true, true};
    }
    
    public int entraCoche() {
        mutex.lock();
        int p = -1;
        
        try {
            ncochesEspera++;
            while (libres == 0 || ncoches == 2) {
                cCoches.await();
            }
            
            p = 0;
            while (p < 3 && !libre[p]) p++;
            
            libre[p] = false;
            libres--;
            ncoches++;
            ncochesEspera--;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
        return p;
    }
    
    public int entraFurgo() {
        mutex.lock();
        int p = -1;
        
        try {
            nfurgosEspera++;
            while (libres == 0 || (nfurgos == 2 && ncochesEspera > 0)) {
                cFurgos.await();
            }
            
            p = 0;
            while (p < 3 && !libre[p]) p++;
            
            libre[p] = false;
            libres--;
            nfurgos++;
            nfurgosEspera--;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
        return p;
    }
    
    public void saleFurgo(int p) {
        mutex.lock();
        try {
            nfurgos--;
            libres++;
            libre[p] = true;
            
            if (nfurgos < 2 || ncochesEspera == 0) {
                cFurgos.signal();
            } else {
                // y si hay coches esperando
                cCoches.signal();
            }
        } finally {            
            mutex.unlock();
        }
    }
    
    public void saleCoche(int p) {
        mutex.lock();
        try {
            ncoches--;
            libres++;
            libre[p] = true;
            
            if (nfurgos < 2 || ncochesEspera == 0) {
                cFurgos.signal();
            } else {
                cCoches.signal();
            }
            
        } finally {            
            mutex.unlock();
        }
    }
}
