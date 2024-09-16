package pcd.practica4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;


/**
 *
 * @author Pepe
 */
public class CanvasCola extends Canvas {
    
    private int head, tail, capacidad, numelementos;
    private Object datos[];
    private String mensaje;
    
    public CanvasCola(int capacidad) {
        this.capacidad = capacidad;
        this.head = 0;
        this.tail = 0;
        setBackground(Color.LIGHT_GRAY);
    }
    
    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        // Definimos el lado que va a tener cada cuadro de la rejilla y margenes
        int lado = 60, margenY = 50, margenX = 20;
        
        og.setFont(new Font("Arial", 0, 40));
        
        og.setColor(Color.RED);
        
        // Imprimimos avisos y mensajes
        if (mensaje != null) {
            og.drawString("Aviso: " + mensaje, margenX, margenY + 200);
            mensaje = null;
        }
        
        og.setColor(Color.ORANGE);
        og.fillRect(margenX, margenY + 250, 30, 30);
        og.setColor(Color.GREEN);
        og.fillRect(margenX, margenY + 300, 30, 30);
        og.setColor(Color.BLUE);
        og.drawString("> Head pointer", margenX + 40, margenY + 280);
        og.drawString("> Tail pointer", margenX + 40, margenY + 330);
        
        // Imprimimos rejilla
        og.setColor(Color.BLACK);
        for (int i = 0; i < capacidad; i++) {
            og.drawRect(margenX + lado*i, margenY, lado, lado);
        }
        
        // Imprimimos datos en rejilla
        if (datos != null) {
            for (int i = head, n = 0; n < numelementos; i = (i+1) % capacidad, n++) {
                og.drawString(datos[i].toString(), margenX + 10 + lado*n, margenY + 45);
            }
            
            // Imprimimos puntero head y tail
            og.setColor(Color.ORANGE);
            og.fillRect(margenX + lado*head + 15, margenY + 100, 30, 30);
            og.setColor(Color.GREEN);
            og.fillRect(margenX + lado*tail + 15, margenY + 70, 30, 30);

        }
        
        g.drawImage(offscreen, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public void avisa(String mensaje) {
        this.mensaje = mensaje;
        repaint();
    }
    
    public void representa(Object buf[], int head, int tail, int numele) {
        this.datos = buf;
        this.head = head;
        this.tail = tail;
        this.numelementos = numele;
        repaint();
    }

}
