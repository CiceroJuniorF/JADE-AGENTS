package br.com.negociation.agents;

import com.google.gson.Gson;

import org.apache.log4j.Logger;

import br.com.negociation.Utils;
import br.com.negociation.products.Product;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Buyer
 */
public class BuyerAgent extends Agent {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(BuyerAgent.class);
	private static final long time = System.currentTimeMillis();

	private static String getHash() {
		return "xjolito";
	}

	public BuyerAgent() {
		logger.debug("New Agent(Buyer)Create");
	}

	protected void setup() {
		this.addBehaviour(new TickerBehaviour(this, 3000) {
			private static final long serialVersionUID = 1L;
			// Menssagem Template de do CFP
			private MessageTemplate mt;
			private int receivers = 0;
			private String bestMerchant;
			private Double bestPrice;
			private boolean isOrderSucess = false;
			private boolean isNegociation = false;
			public void onTick() {
				logger.debug("Agent [" + Utils.prefixAgent(myAgent.getName()) + "] start action. ");
				ACLMessage msg = myAgent.receive(mt);
				if (msg != null) {
					this.isNegociation = true;
					// Verifica se a menssagem recebida é uma proposta.
					if (msg.getPerformative() == ACLMessage.PROPOSE) {						
						//Compara se o preço qual produto é mais barato
						if(receivers == 0 || Double.parseDouble(msg.getContent()) < bestPrice ){
							logger.info("Best propose "+msg.getContent()+ " from "+Utils.prefixAgent(msg.getSender().getName()));
							this.bestPrice = Double.parseDouble(msg.getContent());
							this.bestMerchant = Utils.prefixAgent(msg.getSender().getName());
						}
						//Verifica se os todos os agents ja reponder.
						if (receivers == Utils.agentsMerchant().size()) {							
							ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
							order.addReceiver(new AID(bestMerchant, AID.ISLOCALNAME));
							order.setContent("Feijão");
							order.setConversationId(
									order.getContent().substring(0, 2) + getHash() + System.currentTimeMillis());
							order.setReplyWith(getHash() + System.currentTimeMillis());
							//Envia o Confirmação da proposta.
							logger.info("SEND ORDER!");
							myAgent.send(order);
							// Prepare the template to get the purchase order reply
							mt = MessageTemplate.and(MessageTemplate.MatchConversationId(order.getConversationId()),
									MessageTemplate.MatchInReplyTo(order.getReplyWith()));
						}
						receivers++;
					} else if (msg.getPerformative() == ACLMessage.CONFIRM) {
						//Recebe o pedido.
						logger.info("Agent "+Utils.prefixAgent(myAgent.getName()) + "BUY: "+ msg.getContent()+" FROM: "+ Utils.prefixAgent(msg.getSender().getName()));
						isOrderSucess = true;
						block();
					}

				} else if(!isOrderSucess){
					// Montando menssagem CFP;
					ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
					// Adicionando Receivers
					for (String agent : Utils.agentsMerchant()) {
						logger.info("Add receiver => " + agent);
						cfp.addReceiver(new AID(agent, AID.ISLOCALNAME));
					}
					// Adionando conteudo da menssagem - no caso o produto
					cfp.setContent("Feijão");
					// Cria o Hash da Conversa.
					cfp.setConversationId(cfp.getContent().substring(0, 2) + getHash() + System.currentTimeMillis());
					cfp.setReplyWith(getHash() + System.currentTimeMillis());

					mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
							MessageTemplate.MatchInReplyTo(getHash() + System.currentTimeMillis()));
					// Enviado a menssage

					logger.info("SEND:" + "\nAgent: " + Utils.prefixAgent(myAgent.getName()) + "\nCFP CONTENT => "
							+ cfp.getContent() + "\n ID:" + cfp.getConversationId());
					send(cfp);
				}
			}
		});

	}
}