package br.com.negociation.simulator;

import br.com.negociation.simulator.agentsPanel.IniciadorPanel;

/**
 * CreateSimulator
 */
public class CreateSimulator {

    public static void createCotainer() {
        IniciadorPanel panel = IniciadorPanel.getInstance();
        panel.alterStatus();
        
    }

    public static void removeContainer() {
        IniciadorPanel panel = IniciadorPanel.getInstance();
        panel.alterStatus();
    }
}