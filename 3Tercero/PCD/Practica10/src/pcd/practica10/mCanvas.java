package pcd.practica10;

import java.awt.*;

/**
 *
 * @author Pepe
 */
public class mCanvas extends Canvas {

    private int lectores[] = {0, 0, 0, 0, 0, 0};
    private int escritores[] = {0, 1, 0};
    
    private int posxl[] = {50, 100, 150, 200, 250, 300};
    private int posyl[] = {100, 100, 100, 100, 100, 100};

    public mCanvas(int ancho, int alto) {
        super();
        setSize(ancho, alto);
        setBackground(Color.WHITE);

    }
    
    public synchronized void setLugarL(int id, int posx, int posy) {
        posxl[id] = posx;
        posyl[id] = posy;
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();

        og.setFont(new Font("Arial", Font.PLAIN, 20));
        og.setColor(Color.red);
        og.drawString("Practica 10", 20, 20);

        // Pintamos estados i
        for (int e = 0; e < 5; e++) {

            if (e == 4) {
                og.setColor(Color.red);
                for (int i = 0; i < lectores.length; i++) {
                    if (lectores[i] == e) {
                        og.fillOval(50 * (i + 1), 300, 20, 20);
                    }
                }
            } else {
                og.setColor(Color.blue);
                for (int i = 0; i < lectores.length; i++) {
                    if (lectores[i] == e) {
                        og.fillOval(posxl[i], posyl[i], 20, 20);
                    }
                }

                og.setColor(Color.red);
                for (int i = 0; i < escritores.length; i++) {
                    if (escritores[i] == e) {
                        og.fillOval((lectores.length + 1) * 50 + 50 * i, 100 + 100 * e, 20, 20);
                    }
                }

                if (e == 1) {
                    og.setColor(Color.black);
                    og.drawLine(0, 220, getWidth(), 220);
                }
            }
        }

        g.drawImage(img, 0, 0, null);
    }

    public synchronized void setLector(int id, int estado) {
        lectores[id] = estado;
        repaint();
    }

    public synchronized void setEscritor(int id, int estado) {
        escritores[id] = estado;
        repaint();
    }
}
