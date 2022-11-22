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
    private int scanMano;
    private int scanMaleta[];
    private int perros[], tipoPerros[];
    private int cuidador;
    
    public mCanvas() {
        pasMaleta = new ArrayList<>();
        pasMano = new ArrayList<>();
        scanMano = -1;
        scanMaleta = new int [] {-1, -1};
        perros = new int [] {-1, -1};
        /*
         * Codigos de perros
         * -1 -> libre
         * 0 -> comiendo
         * != de 0, -1 -> escaneando
        */
        tipoPerros = new int[] {-1, -1};
        cuidador = -1;
        
        try {
            imgCuida = ImageIO.read(new File("cuida.png")).getScaledInstance(80, 80, 0);
            imgPerro = ImageIO.read(new File("perro.png")).getScaledInstance(80, 80, 0);
            imgPasMaleta = ImageIO.read(new File("pasajeroMaleta.png")).getScaledInstance(80, 80, 0);
            imgPasMano = ImageIO.read(new File("pasajeroMano.png")).getScaledInstance(80, 80, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void addPasMaleta(int id) {
        pasMaleta.add(id);
        repaint();
    }
    
    public synchronized void addPasMano(int id) {
        pasMano.add(id);
        repaint();
    }
    
    public synchronized boolean esPrimeroPasMano(int id) {
        return pasMano.get(0) == id;
    }
    
    public synchronized boolean esPrimeroPasMaleta(int id) {
        return pasMaleta.get(0) == id;
    }
    
    public synchronized void entraScanMano(int id) {
        if (!pasMano.isEmpty())
            pasMano.remove(0);
        scanMano = id;
        repaint();
    }
    
    public synchronized void saleScanMano() {
        scanMano = -1;
        repaint();
    }
    
    public synchronized int entraScanMaleta(int id) {
        pasMaleta.remove(0);
        int i = 0;
        while (i < 2 && scanMaleta[i] != -1) i++;
        scanMaleta[i] = id;
        repaint();
        return i;
    }
    
    public synchronized void saleScanMaleta(int p) {
        scanMaleta[p] = -1;
        repaint();
    }
    
    public synchronized int entraPerros(int id, int tipo) {
        int i = 0;
        while (i < 2 && perros[i] != -1) i++;
        perros[i] = id;
        tipoPerros[i] = tipo;
        repaint();
        return i;
    }
    
    public synchronized int cuidaPerro(int id) {
        int i = 0;
        while (i < 2 && perros[i] != 0) i++;
        cuidador = id;
        repaint();
        return i;
    }
    
    public synchronized void perroLibre(int p) {
        cuidador = -1;
        perros[p] = -1;
        repaint();
    }
    
    // Cuando un pasajero sale de perros, el perro que le ha escaneado tiene que comer
    public synchronized void salePerros(int p) {
        perros[p] = 0;
        repaint();
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
        
        // Pinar escaneres
        // Escaner pasajeros de mano
        og.setColor(Color.RED);
        og.fillRect(margenX + xRayos, margenY, 100, 100);
        if (scanMano != -1) {
            pintarPasMano(og, margenX + xRayos, margenY, scanMano);
        }
        // Escaneres pasajeros de maleta
        for (int i = 0; i < 2; i++) {
            og.setColor(Color.GREEN);
            og.fillRect(margenX + xRayos, margenY + 110 + 110*i, 100, 100);
            if (scanMaleta[i] != -1) {
                pintarPasMaleta(og, margenX + xRayos, margenY + 110 + 110*i, scanMaleta[i]);
            }
        }
        
        // Pintamos los perros
        for (int i = 0; i < perros.length; i++) {
            if (perros[i] != 0) {
                pintarPerro(og, margenX + 110, margenY + 110*i);
                if (perros[i] != -1)
                    if (tipoPerros[i] == 1) {
                        pintarPasMano(og, margenX + 220, margenY + 110*i, perros[i]);
                    } else if (tipoPerros[i] == 2) {
                        pintarPasMaleta(og, margenX + 220, margenY + 110*i, perros[i]);
                    } else {
                        System.exit(1234567890);
                    }
            }
        }
        // Pintamos el cuidador
        if (cuidador != -1) {
            pintarCuidador(og, margenX, margenY);
        }
        
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    private void pintarCuidador(Graphics g, int x, int y) {
        g.drawImage(imgCuida, x, y, null);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.BLUE);
        g.drawString("" + cuidador, x, y + 20);
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
    
    private void pintarPerro(Graphics g, int x, int y) {
        g.drawImage(imgPerro, x, y, null);
    }
    
}
