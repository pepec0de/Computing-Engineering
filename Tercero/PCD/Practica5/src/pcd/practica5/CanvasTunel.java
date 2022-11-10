/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd.practica5;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
    private final int margenX = 30, margenY = 30;
    private char[] estadoTunel; // {c, f}
    private int[] vehiTunel;
    
    public CanvasTunel() {
        colaCoches = new ArrayList<>();
        colaFurgos = new ArrayList<>();
        estadoTunel = new char[3];
        vehiTunel = new int[3];
        
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
    
    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
 
        int hTunel = imgTunel.getHeight(null);
        int wCoche = imgCoche.getWidth(null), hCoche = imgCoche.getHeight(null);
        int wFurgo = imgCoche.getWidth(null), hFurgo = imgFurgo.getHeight(null);
        int yColaFurgo = margenY + hCoche + 100;
        int xColas = margenX + 300;
        
        og.setFont(new Font("Arial", 0, 20));
        
        // Tuneles de lavado
        for (int i = 0; i < 3; i++)
            og.drawImage(imgTunel, margenX, margenY + hTunel*i, null);
        
        // Area cola coches
        og.setColor(Color.CYAN);
        og.fillRect(xColas, margenY, wCoche*10, hCoche);
        for (int i = 0; i < colaCoches.size(); i++) {
            pintarCoche(og, colaCoches.get(i), xColas + imgCoche.getWidth(null)*i, margenY);
        }
        
        // Area cola furgos
        og.setColor(Color.MAGENTA);
        og.fillRect(xColas, yColaFurgo, wFurgo*10, hFurgo);
        for (int i = 0; i < colaFurgos.size(); i++) {
            pintarFurgo(og, colaFurgos.get(i), xColas + (imgFurgo.getWidth(null) + 5)*i, yColaFurgo);
        }
        
        for (int i = 0; i < 3; i++) {
            pintarVehiculoTunel(og, i, estadoTunel[i], vehiTunel[i]);
        }
        
        g.drawImage(offscreen, 0, 0, null);
    }
    
    private void pintarCoche(Graphics g, int id, int x, int y) {
        g.drawImage(imgCoche, x, y, null);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(id), x, y + 50);
    }
    
    private void pintarFurgo(Graphics g, int id, int x, int y) {
        g.drawImage(imgFurgo, x, y, null);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(id), x, y + 50);
    }
    
    private void pintarVehiculoTunel(Graphics g, int pos, char v, int id) {
        switch (v) {
            case 'c':
                colaCoches.remove((Object) id);
                pintarCoche(g, id, margenX + 36, margenY + 100*pos);
                break;
            
            case 'f':
                colaFurgos.remove((Object) id);
                pintarFurgo(g, id, margenX + 36, margenY + 100*pos);
                break;
        }
    }
    
    public synchronized void meterVehiculo(char v, int pos) {
        estadoTunel[pos] = v;
        vehiTunel[pos] = (int) Thread.currentThread().getId();
    }
    
    public synchronized void sacarVehiculo(int pos) {
        estadoTunel[pos] = 0;
        vehiTunel[pos] = 0;
    }
}
