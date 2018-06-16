package br.com.sma.agents.communication;

import java.util.Date;

import br.com.sma.dictionary.Conversation;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Send extends Agent {
	/**
	 * 
	 */
	private long future = new Date().getTime();
	private static final long serialVersionUID = 1L;

	protected void setup() {
		addBehaviour(new CyclicBehaviour() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void action() {
				if (future < new Date().getTime()) {

					ACLMessage recebido = receive();
					if (recebido != null) {
						System.out.println(
								"==============================================================================\n"
										+ recebido.getSender().getName() + "Talk: " + recebido.getContent() + "   \n"
										+ "\n==============================================================================\n");
						ACLMessage reply = recebido.createReply();
						reply.setPerformative(ACLMessage.INFORM);
						reply.setContent(Conversation.talkKey(recebido.getContent()));
						send(reply);
					} else {
						ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
						msg.addReceiver(new AID("Pedro", AID.ISLOCALNAME));
						msg.setContent("Hello how are you?");
						send(msg);
					}
					block();
					future = new Date().getTime()+2000;
				}
				
			}
		});

	}
}
