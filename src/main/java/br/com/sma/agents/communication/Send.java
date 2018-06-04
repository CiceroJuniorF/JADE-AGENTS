package br.com.sma.agents.communication;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Send extends Agent {
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
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setContent(myAgent.getLocalName() +"\n     #####Hello, how are you?#### ");
				msg.addReceiver(new AID("receive", AID.ISLOCALNAME));
				send(msg);
				ACLMessage recebido = receive();
				if (recebido != null) {
					System.out.println(
							"==============================================================================\n"
							+"    Agent: "+ myAgent.getName()+ "\n"							
							+ "   Receive:\n   " 
							+     recebido.getContent() + "   \n"
							+ "   from      " 
							+     recebido.getSender().getName()
							+ "\n==============================================================================\n");
					ACLMessage reply = recebido.createReply();
					reply.setPerformative(ACLMessage.INFORM);
					reply.setContent(myAgent.getLocalName() +"   \n     #####I fine too!!####");
					send(reply);
					
					
				}
				block();
			}
		});
		
	}
}

