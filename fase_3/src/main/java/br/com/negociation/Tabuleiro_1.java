package br.com.negociation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
public class Tabuleiro_1 extends JPanel {
    
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Rectangle2D.Double r;
		Graphics2D g2 = (Graphics2D) g.create();
	  	g2.setPaint(Color.BLACK);
		r = new Rectangle2D.Double(1,1,100,100);
		g2.fill(r);
	  	// r = new Rectangle2D.Double(100,100,100,100);
	  	// g2.fill(r);
		// g2.setPaint(Color.RED);
		// r = new Rectangle2D.Double(1,100,100,100);
	  	// g2.fill(r);
	  	// r = new Rectangle2D.Double(100,1,100,100);
	  	// g2.fill(r);
	  	g2.dispose();
	}

}