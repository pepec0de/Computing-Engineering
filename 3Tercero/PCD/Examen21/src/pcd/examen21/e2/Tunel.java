package pcd.examen21.e2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Pepe
 */
public class Tunel {

    private boolean libre[];
    final Lock mutex = new ReentrantLock();
    final Condition cCoches = mutex.newCondition(),
            cFurgos = mutex.newCondition();
    private int nCochesEspera;

    public Tunel() {
        libre = new boolean[]{true, true, true};
        nCochesEspera = 0;
    }

    public void entraCoche() {
        mutex.lock();
        
        nCochesEspera++;
        try {
            while (!libre[0]) {
                cCoches.await();
            }
            libre[0] = false;
            nCochesEspera--;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }

    public void entraFurgo() {
        mutex.lock();
        try {
            while (!libre[0] || nCochesEspera > 0) {
                cFurgos.await();
            }

            libre[0] = false;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }

    public void lavar() {
        mutex.lock();
        try {
            libre[0] = true;
            libre[1] = false;
            cCoches.signal();
            cFurgos.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void secar() {
        mutex.lock();
        try {
            libre[1] = true;
            libre[2] = false;
        } finally {
            mutex.unlock();
        }
    }

    public void saleCoche() {
        mutex.lock();
        try {
            libre[2] = true;
        } finally {
            mutex.unlock();
        }
    }

    public void saleFurgo() {
        mutex.lock();
        try {
            libre[2] = true;
        } finally {
            mutex.unlock();
        }
    }

}
