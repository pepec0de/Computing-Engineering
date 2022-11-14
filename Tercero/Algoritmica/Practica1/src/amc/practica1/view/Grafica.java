package amc.practica1.view;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Double;

public class Grafica extends Canvas {
	
	private double puntos[][];
	private String pesos[];
	private int aristas[][];
	private double offX, offY, scaleX, scaleY, zoomX, zoomY;
	private int pSize = 5;
	private double mult = 0;
	
	public Grafica() {
		zoomX = 0;
		zoomY = 0;
	}
	
	@Override
	public void paint(Graphics g) {
		
		Image offscreen = createImage(getWidth(), getHeight());
		Graphics2D og = (Graphics2D) offscreen.getGraphics();
		
//		int x0 = getWidth() / 2, y0 = getHeight() / 2;
//		og.setColor(Color.BLACK);
//		og.drawLine(x0, 0, x0, getHeight());
//		og.drawLine(0, y0, getWidth(), y0);
		
		og.setColor(Color.DARK_GRAY);
		double tamPixel = 6.5;
		
		if (puntos != null) {
			for (int i = 0; i < puntos.length; i++) {
				drawPoint(og, puntos[i][0], puntos[i][1]);
			}
			
			//drawLine(og, puntos[0][0], puntos[0][1], puntos[6][0], puntos[6][1]);
		}

		g.drawImage(offscreen, 0, 0, null);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	public void pintarPuntos(double puntos[][]) {
		this.puntos = puntos;
		double minX, minY, maxX, maxY;
		
		maxX = puntos[0][0];
		maxY = puntos[0][1];
		minX = puntos[1][0];
		minY = puntos[1][1];
		
		for (int i = 2; i < puntos.length; i++) {
			if (minX > puntos[i][0]) {
				minX = puntos[i][0];
			}
			if (minY > puntos[i][1]) {
				minY = puntos[i][0];
			}
			if (maxX < puntos[i][0]) {
				maxX = puntos[i][0];
			}
			if (maxY < puntos[i][1]) {
				maxY = puntos[i][1];
			}
		}
		
		scaleX = getWidth() / (maxX - minX);
		scaleY = getHeight() / (maxY - minY);
		offX = minX;
		offY = minY;
		repaint();
	}
	
	public void pintarAristas(int idxAristas[][], String peso[]) {
		this.aristas = idxAristas;
		this.pesos = peso;
		repaint();
	}
	
	private void drawPoint(Graphics2D g, double x, double y) {
		g.fill(new Rectangle2D.Double( ((x - offX - (pSize/2))*scaleX + zoomX) % getWidth(),
									((y - offY - (pSize/2))*scaleY + zoomY) % getHeight(), pSize, pSize));
		System.out.println("Imprimiendo " + x + ", " + y + " en " + (x - offX - (pSize/2))*scaleX + zoomX + ", " + (y - offY - (pSize/2))*scaleY + zoomY);
	}
	
	private void drawLine(Graphics2D g, double x0, double y0, double x1, double y1) {
		g.draw(new Line2D.Double( (x0 - offX - (pSize/2))*scaleX + zoomX + 1,
									(y0 - offY - (pSize/2))*scaleY + zoomY + 1,
									(x1 - offX - (pSize/2))*scaleX + zoomX + 1,
									(y1 - offY - (pSize/2))*scaleY + zoomY + 1));
	}
	
	
	
    public void zoomIn(double q) {
        this.mult += q;
        this.scaleX *= 1.15;
        this.scaleY *= 1.15;

        repaint();
    }

    public void zoomOut(double q) {
        this.mult -= q;
        this.scaleX *= 0.85;
        this.scaleY *= 0.85;

        repaint();
    }

    public void resetZoom() {
        this.zoomX = 0;
        this.zoomY = 0;
        this.mult = 0;
    }

}
