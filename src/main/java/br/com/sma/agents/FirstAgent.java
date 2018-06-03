package br.com.sma.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class FirstAgent extends Agent {
	private static final long serialVersionUID = 1L;
	protected void setup(){
		this.addBehaviour(new OneShotBehaviour() {			
			private static final long serialVersionUID = 1L;
			public void action() {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(new AID("first-agent",AID.ISLOCALNAME));
				msg.setLanguage("Português");
				msg.setOntology("TEMPO");
				msg.setContent("Hello i'm the first agent.");
				send(msg);				
				System.out.println("Agente: "+myAgent.getName()+"  "+msg.getContent());
			}
		}); 
		
	}
}
