package pcd.practica6;

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
public class mCanvas extends Canvas {
    
    private ArrayList<Integer> pasMaleta, pasMano;
    private Image imgCuida, imgPerro, imgPasMaleta, imgPasMano;
    private boolean scanMano;
    private boolean scanMaleta[] = new boolean[2];
    private boolean perros[] = new boolean[2];
    
    public mCanvas() {
        pasMaleta = new ArrayList<>();
        pasMano = new ArrayList<>();
        
        try {
            imgCuida = ImageIO.read(new File("cuida.png")).getScaledInstance(80, 80, 0);
            imgPerro = ImageIO.read(new File("perro.png")).getScaledInstance(80, 80, 0);
            imgPasMaleta = ImageIO.read(new File("pasajeroMaleta.png")).getScaledInstance(80, 80, 0);
            imgPasMano = ImageIO.read(new File("pasajeroMano.png")).getScaledInstance(80, 80, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addPasMaleta(int id) {
        pasMaleta.add(id);
        repaint();
    }
    
    public void addPasMano(int id) {
        pasMano.add(id);
        repaint();
    }
    
    public synchronized boolean esPrimeroPasMano(int id) {
        return pasMano.get(0) == id;
    }
    
    public synchronized boolean esPrimeroPasMaleta(int id) {
        return pasMaleta.get(0) == id;
    }
    
    public synchronized void salirColaPasMano() {
        pasMano.remove(0);
    }
    
    public synchronized void salirColaPasMaleta() {
        pasMaleta.remove(0);
    }
    
    public synchronized void entraScanMano() {
        
    }
    
    public synchronized void entraScanMaleta() {
        
    }
    
    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        int margenX = 30, margenY = 30, xRayos = 350, xColas = xRayos + 150; 
        
        // Pintar las colas
        og.setColor(Color.CYAN);
        og.fillRect(xColas, margenY, 800, 100);
        for (int i = 0; i < pasMano.size(); i++) {
            pintarPasMano(og, xColas + 90*i, margenY, pasMano.get(i));
        }
        
        og.setColor(Color.CYAN);
        og.fillRect(xColas, margenY + 170, 800, 100);
        for (int i = 0; i < pasMaleta.size(); i++) {
            pintarPasMaleta(og, xColas + 90*i, margenY + 170, pasMaleta.get(i));
        }
        
        og.setColor(Color.RED);
        og.fillRect(margenX + xRayos, margenY, 100, 100);
        og.setColor(Color.BLUE);
        og.fillRect(margenX + xRayos, margenY + 110, 100, 100);
        og.fillRect(margenX + xRayos, margenY + 220, 100, 100);
        
        
        og.drawImage(imgCuida, margenX, margenY, null);
        
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    private void pintarPasMano(Graphics g, int x, int y, Integer id) {
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.BLUE);
        g.drawImage(imgPasMano, x, y, null);
        g.drawString(id.toString(), x, y + 20);
    }
    
    private void pintarPasMaleta(Graphics g, int x, int y, Integer id) {
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.BLUE);
        g.drawImage(imgPasMaleta, x, y, null);
        g.drawString(id.toString(), x, y + 20);
    }
    
}
