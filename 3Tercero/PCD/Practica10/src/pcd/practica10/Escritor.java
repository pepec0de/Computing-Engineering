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
public class Escritor implements Runnable {
    
    private int id;
    private mCanvas cv;
    private ReentrantReadWriteLock L;
    
    public Escritor(int i, mCanvas c, ReentrantReadWriteLock l) {
        id = i;
        cv = c;
        L = l;
    }
    
    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        
        while (true) {
            try {
                cv.setEscritor(id, 0);
                Thread.sleep((r.nextInt(3)+1)*1000);
                cv.setEscritor(id, 1);
                
                // protocolo entrada escritor
                L.writeLock().lock();
                try {
                    cv.setEscritor(id, 2);
                    Thread.sleep((r.nextInt(2)+2)*1000);
                } finally {
                    L.writeLock().unlock();
                }
                
            } catch (InterruptedException e) {
                System.err.println(id + ": " + e.getMessage());
            }
        }
    }
}
