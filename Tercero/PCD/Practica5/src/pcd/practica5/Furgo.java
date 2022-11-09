/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd.practica5;

/**
 *
 * @author Pepe
 */
public class Furgo implements Runnable {

    private Tunel tunel;
    private CanvasTunel canvas;
    
    public Furgo(Tunel tunel, CanvasTunel canvas) {
        this.tunel = tunel;
        this.canvas = canvas;
    }
    
    @Override
    public void run() {
        
    }
    
}
