package pcd.examen21.e1;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Pepe
 */
public class mCanvas extends Canvas {
    
    private ArrayList<Integer> colaCoches;
    private ArrayList<Integer> colaFurgos;
    
    public mCanvas() {
        colaCoches = new ArrayList<>();
        colaFurgos = new ArrayList<>();
    }
    
    private void llegaCoche(int id) {
        colaCoches.add(id);
        repaint();
    }
    
    private void llegaFurgo(int id) {
        colaFurgos.add(id);
        repaint();
    }
    
    private void prelavarCoche(int id) {
        colaCoches.remove((Object) id);
        
    }
    
    private void saleFurgo(int id) {
        
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        
        og.drawImage(offscreen, 0, 0, null);
        g.drawImage(offscreen, 0, 0, null);
    }
    
}
