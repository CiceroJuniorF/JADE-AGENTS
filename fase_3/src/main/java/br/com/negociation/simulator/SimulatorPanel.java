package br.com.negociation.simulator;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import br.com.negociation.simulator.agentsPanel.IniciadorPanel;

/**
 * SimulatorPane
 */
public class SimulatorPanel extends JPanel {
    static SimulatorPanel _ISTANCE;

    public SimulatorPanel() {
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setSize(1300, 600);
        this.setLocation(30, 120);
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.startAgents();
    }

    private void startAgents() {
        this.add(IniciadorPanel.getInstance());        
    }

    /**
     * Retorna a istancia unica da tela para ser possível alterar em qualquer
     * momento na aplicação
     * 
     * @return
     */
    public static SimulatorPanel getInstance() {
        if (_ISTANCE == null) {
            _ISTANCE = new SimulatorPanel();
        }
        return _ISTANCE;
    }

}