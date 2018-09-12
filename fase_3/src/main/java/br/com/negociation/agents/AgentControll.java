package br.com.negociation.agents;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import br.com.negociation.Tabuleiro;
import br.com.negociation.Tabuleiro_1;
import br.com.negociation.Tela1;
import br.com.negociation.Utils;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class AgentControll extends Agent {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AgentControll.class);

	protected void setup() {
		this.addBehaviour(new OneShotBehaviour() {
			private static final long serialVersionUID = 1L;

			public void action() {
				String log = "\n";
				Tela1.getInstance().setVisible(true);
				for (String agents : Utils.allAgents()) {
					if (agents != null)
						log += "\n|" + agents + "|\n";
				}

				logger.info("\nThe following agents have been created ->>>>>>>" + log);

				

				JPanel contentPane = (JPanel) Tela1.getInstance().getContentPane();

				contentPane.removeAll();
				Tabuleiro retangulo = new Tabuleiro();				
				retangulo.newRectangle(1,1,100,100);
				retangulo.newRectangle(100,100,100,100);
				retangulo.newRectangle(1,100,100,100);
				retangulo.newRectangle(100,1,100,100);
				retangulo.newRectangle(200,1,100,100);
				retangulo.newRectangle(200,100,100,100);
				contentPane.add(retangulo);
				contentPane.repaint();
				contentPane.validate();
			}

		});

	}
}
