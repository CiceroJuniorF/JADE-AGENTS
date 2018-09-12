package br.com.negociation.agents;

import com.google.gson.Gson;

import org.apache.log4j.Logger;

import br.com.negociation.Utils;
import br.com.negociation.products.Product;
import br.com.negociation.services.MerchantService;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Merchant
 */
public class MerchantAgent extends Agent {
	private static final long serialVersionUID = 1L;
	private static MerchantService service = new MerchantService();
	private static Logger logger = Logger.getLogger(MerchantAgent.class);

	protected void setup() {
		this.addBehaviour(new TickerBehaviour(this, 1500) {
			private static final long serialVersionUID = 1L;

			public void onTick() {
				// Menssagem recebida
				ACLMessage msg = receive();
				if (msg != null) {			

					// Criando reposta
					ACLMessage reply = msg.createReply();					
					// Usado para serealizar o objeto em json;
					Gson gson = new Gson();
					if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
						//Informa que terminou o processo.
						reply.setPerformative(ACLMessage.INFORM);
						reply.setContent("DONE!");
						logger.debug("Agent " + Utils.prefixAgent(myAgent.getName()) + " INFORM_DONE - > "
								+ reply.getContent());
						myAgent.send(reply);
						// Retorna um produto com proposta.
						reply.setPerformative(ACLMessage.INFORM);
						//Pega o produto negociado para venda.
						Product product = service.getProduct(msg.getContent(), Utils.prefixAgent(myAgent.getName()));
						//Coloca o produto no corpo da menssagem
						reply.setContent(gson.toJson(product));
						logger.debug("Agent " + Utils.prefixAgent(myAgent.getName()) + " INFORM_RESULT - > "
								+ reply.getContent() + " \nOrder Sucess!");
					} else if (msg.getPerformative() == ACLMessage.CFP) {
						// Log da menssagem recebida - para CFP
						logger.debug("Agent " + Utils.prefixAgent(myAgent.getName()) + " received the menssage \n" + "-"
						+ msg.getContent() + "\nFOR: " + Utils.prefixAgent(msg.getSender().getName()) + "\nID:"
						+ msg.getConversationId());
						logger.info("CFP RECEIVE:" + msg.toString());
						//Procura os produtos que este tem disponível
						Product product = service.getProduct(msg.getContent(), Utils.prefixAgent(myAgent.getName()));
						//Faz a verificação se os produtos se tem o produto solicitado para negoicar
						if (product != null) {
							// Retorna um produto com proposta.
							reply.setPerformative(ACLMessage.PROPOSE);
							reply.setContent(product.getPrice().toString());
							logger.debug("Agent " + Utils.prefixAgent(myAgent.getName()) + " PROPOSE - > "
									+ reply.getContent());
						} else {
							// Retorna um recusa devido não ter o produto.
							reply.setPerformative(ACLMessage.REFUSE);
						}
					}
					myAgent.send(reply);

				}
			}
		});

	}

}