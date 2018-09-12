package br.com.negociation.simulator.agentsPanel.status;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * On
 */
public class On extends JPanel {

    public On() {
        ImageIcon icon;
        JLabel imagem;
        this.setBackground(Color.white);
        icon = new ImageIcon(getClass().getResource("/img/on.png"));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(20, 40, java.awt.Image.SCALE_SMOOTH);
        imagem = new JLabel();
        setLocation(100, 100);
        setSize(100, 100);
        imagem.setIcon(new ImageIcon(newimg));
        add(imagem);
    }
}