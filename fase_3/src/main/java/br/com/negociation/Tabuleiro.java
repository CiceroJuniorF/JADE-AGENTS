package br.com.negociation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Tabuleiro extends JPanel {
	private List<Rectangle2D.Double> rs;

	public Tabuleiro() {
		this.rs = new ArrayList<>();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g.create();
		Color color = Color.BLUE;
		Line2D.Double l;
		for (Rectangle2D.Double r : rs) {
			g2.setPaint(color);
			g2.fill(r);
			l = new Line2D.Double();			
			l.x1 = r.x;
			l.x2 = r.x+100;
			l.y1 = r.y;
			l.y2 = r.y+100;			
			g2.setPaint(Color.BLACK);
			g2.draw(l);
			l.y1 = r.x;
			l.y2 = r.x+100;
			l.x1 = r.y;
			l.x2 = r.y+100;			
			g2.setPaint(Color.BLACK);
			g2.draw(l);
			color = (color == Color.BLUE)? Color.RED: Color.BLUE;			
		}		
		g2.dispose();
	}

	public void newRectangle(double x, double y, double w, double h) {
		this.rs.add(new Rectangle2D.Double(x, y, w, h));
	}

}