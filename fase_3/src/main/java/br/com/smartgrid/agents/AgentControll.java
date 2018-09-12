package br.com.smartgrid.agents;

import org.apache.log4j.Logger;

import br.com.smartgrid.Utils;
import br.com.smartgrid.containers.MainContainer;
import br.com.smartgrid.simulator.MainScreen;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class AgentControll extends Agent {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AgentControll.class);
	private boolean isActive = false;

	protected void setup() {
		this.addBehaviour(new OneShotBehaviour() {
			private static final long serialVersionUID = 1L;
			
			public void action() {
				String log = "\n";
				MainScreen.getInstance().setVisible(true);
				MainScreen.getInstance().repaint();
				MainScreen.getInstance().validate();
				for (String agents : Utils.allAgents()) {
					if (agents != null)
						log += "\n|" + agents + "|\n";
				}

				logger.info("\nThe following agents have been created ->>>>>>>" + log);

				// JPanel contentPane = (JPanel) Tela1.getInstance().getContentPane();

				// contentPane.removeAll();
				// Tabuleiro retangulo = new Tabuleiro();
				// contentPane.add(retangulo);
				// contentPane.repaint();
				// contentPane.validate();
				AgentController rma = null;
				try {
					rma = MainContainer.getInstance().createNewAgent("iniciador", "br.com.smartgrid.agents.AgentIniciador", new Object[0]);
					rma.start();
				} catch (StaleProxyException e) {
					e.printStackTrace();
				}
			}

		});

	}
}
