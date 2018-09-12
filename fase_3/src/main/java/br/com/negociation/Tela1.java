package br.com.negociation;

import javax.swing.JFrame;

public class Tela1 extends JFrame {
    private static Tela1 _ISTANCE = null;

    public Tela1() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Smart Grid Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Tela1 getInstance() {
        if (_ISTANCE == null) {
            _ISTANCE = new Tela1();            
        }
        return _ISTANCE;
    }

    public static void alterForm(){        
        getInstance().getContentPane().add(new Tabuleiro_1());
        
    }
}