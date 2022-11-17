package amc.practica1.view;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Set;

import amc.practica1.types.Arista;
import amc.practica1.types.Punto;

public class Grafica extends Canvas {

	private Punto puntos[];
	private Set<Arista<Integer, Integer>> aristas;
	private Color cArista;
	private double offX, offY, scaleX, scaleY, zoomX, zoomY;
	private int pSize = 5;
	private double mult = 0;
	private final int MAXX = 830, MAXY = 735;

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

		if (puntos != null) {
			for (int i = 0; i < puntos.length; i++) {
				drawPoint(og, puntos[i].getX(), puntos[i].getY());
			}
		}
		if (aristas != null) {
			for (Arista<Integer, Integer> a : aristas) {
				og.setColor(cArista);
				drawArista(og, a);
			}
		}

		og.drawImage(offscreen, 0, 0, null);
		g.drawImage(offscreen, 0, 0, null);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	private void drawPoint(Graphics2D g, double x, double y) {
		g.fill(new Rectangle2D.Double(getXScaledCoord(x), getYScaledCoord(y), pSize, pSize));
	}

	private void drawArista(Graphics2D g, Arista<Integer, Integer> a) {
		double x0 = getXScaledCoord(a.getOrigen().getPunto().getX()) + 1,
				y0 = getYScaledCoord(a.getOrigen().getPunto().getY()) + 1,
				x1 = getXScaledCoord(a.getDestino().getPunto().getX()) + 1,
				y1 = getYScaledCoord(a.getDestino().getPunto().getY()) + 1;
		g.draw(new Line2D.Double(x0, y0, x1, y1));
		if (a.getPeso() != null) {
			// Pintamos el peso
			int minX = (int) x0, maxX = (int) x1;
			if (minX > x1) {
				minX = (int) x1;
				maxX = (int) x0;
			}
	
			int minY = (int) y0, maxY = (int) y1;
			if (minY > y1) {
				minY = (int) y1;
				maxY = (int) y0;
			}
	
			int x = ((maxX - minX) / 2) + minX, y = ((maxY - minY) / 2) + minY;
			g.setColor(Color.RED);
			g.drawString(a.getPeso().toString(), x, y);
		}
	}
	
	private double getXScaledCoord(double v) {
		return (v - offX)*scaleX + zoomX - (pSize / 2);
	}
	
	private double getYScaledCoord(double v) {
		return (v - offY)*scaleY + zoomY - (pSize / 2);
	}
	
	public void pintarPuntos(Punto puntos[]) {
		this.puntos = puntos;
		double minX, minY, maxX, maxY;

		maxX = puntos[0].getX();
		maxY = puntos[0].getY();
		minX = puntos[1].getX();
		minY = puntos[1].getY();

		for (int i = 2; i < puntos.length; i++) {
			if (minX > puntos[i].getX()) {
				minX = puntos[i].getX();
			}
			if (minY > puntos[i].getY()) {
				minY = puntos[i].getY();
			}
			if (maxX < puntos[i].getX()) {
				maxX = puntos[i].getX();
			}
			if (maxY < puntos[i].getY()) {
				maxY = puntos[i].getY();
			}
		}

		scaleX = MAXX / (maxX - minX);
		scaleY = MAXY / (maxY - minY);
		offX = minX;
		offY = minY;
		repaint();
	}

	public void pintarAristas(Set<Arista<Integer, Integer>> aristas) {
		this.aristas = aristas;
		repaint();
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
	
	public void setColorArista(Color c) {
		cArista = c;
	}

}
