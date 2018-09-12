package br.com.negociation.agents;

import org.apache.log4j.Logger;

import br.com.negociation.simulator.agentsPanel.IniciadorPanel;
import br.com.negociation.simulator.agentsPanel.status.On;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class AgentIniciador extends Agent {
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(AgentControll.class);

    protected void setup() {
        this.addBehaviour(new CyclicBehaviour() {
            private static final long serialVersionUID = 1L;
            public void action() {
                if(IniciadorPanel.getInstance().getStatus() instanceof On){
                    System.out.println("Ativo");
                }else{
                    System.out.println("Desativado");
                }

            }

        });

    }
}
