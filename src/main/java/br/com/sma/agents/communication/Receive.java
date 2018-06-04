package br.com.sma.agents.communication;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Receive extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void setup() {
		addBehaviour(new CyclicBehaviour() {			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void action() {
				ACLMessage recebido = receive();
				if (recebido != null) {
					//Receive
					System.out.println(
							"==============================================================================\n"
							+"    Agent: "+ myAgent.getName()+ "\n"							
							+ "   Receive:\n   " 
							+     recebido.getContent() + "   \n"
							+ "   from      " 
							+     recebido.getSender().getName()
							+ "\n==============================================================================\n");
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					
					//Response
					msg.setContent(myAgent.getLocalName() +"\n      #####I'm fine!!! And you?#######");
					msg.addReceiver(new AID("send", AID.ISLOCALNAME));
					send(msg);
				}
				block();
			}
		});

	}
	
}

