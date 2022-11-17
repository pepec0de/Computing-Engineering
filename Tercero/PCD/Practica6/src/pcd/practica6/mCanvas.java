package pcd.practica6;

import java.awt.Canvas;
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
    
    public mCanvas() {
        pasMaleta = new ArrayList<>();
        pasMano = new ArrayList<>();
        
        try {
            imgCuida = ImageIO.read(new File("cuida.png"));
            imgPerro = ImageIO.read(new File("perro.png"));
            imgPasMaleta = ImageIO.read(new File("pasajeroMaleta.png"));
            imgPasMano = ImageIO.read(new File("pasajeroMano.png"));
        } catch (IOException e) {
            
        }
    }
    
    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        
        
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
}
