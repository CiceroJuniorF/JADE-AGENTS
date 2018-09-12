package br.com.negociation.simulator;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Tela principal do simulador da Smart Grid
 */
public class MainScreen extends JFrame {
    private static MainScreen _ISTANCE = null;

    /**
     * Handler Control
     */
    HandlerControl handler;

    /**
     * Components
     */
    private JButton start, stop;
    private JPanel controlPanel;

    /**
     * Construtor
     */
    public MainScreen() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Smart Grid Simulator");     
        this.controlPanel = new JPanel();        
        controlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        controlPanel.setLayout(null);       
        this.mountScreen();        
        setContentPane(controlPanel);
        SwingUtilities.updateComponentTreeUI(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mountScreen() {
        //Criando os botões
        start = new JButton("START SIMULATION");
        stop = new JButton("STOP SIMULATION");
        handler = new HandlerControl(start,stop);
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(Color.white);
        panelMenu.setSize(1300,100);
        panelMenu.setLocation(30,10);
        panelMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelMenu.add(start);
        start.addActionListener(handler);
        panelMenu.add(start);
        stop.addActionListener(handler);
        panelMenu.add(stop);
        controlPanel.add(panelMenu);
        controlPanel.add(SimulatorPanel.getInstance());
    }

    /**
     * Retorna a istancia unica da tela para ser possível alterar em qualquer
     * momento na aplicação
     * 
     * @return
     */
    public static MainScreen getInstance() {
        if (_ISTANCE == null) {
            _ISTANCE = new MainScreen();
        }
        return _ISTANCE;
    }

    /**
     * Retorna o Frame principal
     * @return
     */
    public JPanel getControlPanel(){
        return controlPanel;
    }

}