package br.com.negociation.agents;

import org.apache.log4j.Logger;

import br.com.negociation.Utils;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class AgentControll extends Agent {
	private static final long serialVersionUID = 1L;

	private static  Logger logger = Logger.getLogger(AgentControll.class);

	protected void setup(){
		this.addBehaviour(new OneShotBehaviour() {			
			private static final long serialVersionUID = 1L;
			public void action() {
				String log = "\n";
				for( String agents : Utils.allAgents()){
					if(agents != null)
						log +="          |"+ agents+"|\n";
				}
				logger.info("\nThe following agents have been created ->>>>>>>"+ log);			
			}
		}); 
		
	}
}
