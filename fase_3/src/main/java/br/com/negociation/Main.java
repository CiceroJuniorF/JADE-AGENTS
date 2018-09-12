package br.com.negociation;

import br.com.negociation.containers.MainContainer;
import jade.Boot;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * Main
 */
public class Main extends Boot {

    public static void main(String[] args) {
   
        AgentController rma = null;
        try {
            rma = MainContainer.getInstance().createNewAgent("control", "br.com.negociation.agents.AgentControll", new Object[0]);
            rma.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}