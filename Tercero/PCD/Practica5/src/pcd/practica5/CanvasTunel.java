/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd.practica5;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Pepe
 */
public class CanvasTunel extends Canvas {
    
    private Image imgTunel, imgCoche, imgFurgo;
    private ArrayList<Integer> colaCoches, colaFurgos;
    
    
    public CanvasTunel() {
        colaCoches = new ArrayList<>();
        colaFurgos = new ArrayList<>();
        
        try {
            imgTunel = ImageIO.read(new File("tunel.png")).getScaledInstance(200, 100, 1);
            imgCoche = ImageIO.read(new File("coche.png")).getScaledInstance(128, 128, 1);
            imgFurgo = ImageIO.read(new File("furgo.png")).getScaledInstance(128, 128, 1);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public synchronized void meterFurgo(int id) {
        colaFurgos.add(id);
    }
    
    public synchronized void meterCoche(int id) {
        colaCoches.add(id);
    }
    
    public synchronized int PrimerCoche() {
        return colaCoches.get(0);
    }
    
    public synchronized int PrimerFurgo() {
        return colaFurgos.get(0);
    }
    
    // Funcion que manda a paint(g) pintar el coche en el tunel de lavado
    public synchronized void lavarCoche() {
        
    }
    public int getNColaCoche() {
        return colaCoches.size();
    }
    
    public int getNColaFurgo() {
        return colaFurgos.size();
    }
    
    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        int margenX = 30, margenY = 30;
        int hTunel = imgTunel.getHeight(null), hCoche = imgCoche.getHeight(null), hFurgo = imgFurgo.getHeight(null);
        
        for (int i = 0; i < 3; i++)
            og.drawImage(imgTunel, margenX, margenY + hTunel*i, null);
        
        og.setColor(Color.CYAN);
        og.fillRect(margenX + 300, margenY, 700, hCoche);
        
        og.setColor(Color.MAGENTA);
        og.fillRect(margenX + 300, margenY + hCoche + 100, 700, hFurgo);
        og.drawImage(imgFurgo, margenX + 36, margenY, null);
        
        g.drawImage(offscreen, 0, 0, null);
    }
}
