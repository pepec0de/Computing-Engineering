/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd.practica10;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Pepe
 */
public class Lector extends Thread {

    private int id;
    private mCanvas cv;
    private ReentrantReadWriteLock L;

    public Lector(int i, mCanvas c, ReentrantReadWriteLock l) {
        id = i;
        cv = c;
        L = l;
    }

    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis() + id);

        while (true) {
            try {
                cv.setLector(id, 0);
                for (int i = 0; i < 300; i++) {
                    cv.setLugarL(id, 50 + id*50 + r.nextInt(10) - 5, 100 + r.nextInt(10) - 5);
                    sleep(10);
                }
                cv.setLector(id, 1);
                cv.setLugarL(id, 50 + id*50, 200);
                
                L.readLock().lock();
                try {
                    cv.setLector(id, 2);
                    sleep((r.nextInt(2) + 2) * 1000);
                    
                    if (r.nextInt(100) < 25) {
                        // escritor
                        L.readLock().unlock();
                        L.writeLock().lock();
                        cv.setLector(id, 4);
                        sleep(2000);
                        L.readLock().lock();
                        L.writeLock().unlock();
                        cv.setLector(id, 2);
                    } else { // lector
                        sleep(2000);
                    }
                } finally {
                    L.readLock().unlock();
                }

            } catch (InterruptedException e) {
                System.err.println(id + ": " + e.getMessage());
            }
        }
    }
}
