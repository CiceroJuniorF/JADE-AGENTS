package br.com.smartgrid.simulator;

import br.com.smartgrid.simulator.agentsPanel.IniciadorPanel;

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