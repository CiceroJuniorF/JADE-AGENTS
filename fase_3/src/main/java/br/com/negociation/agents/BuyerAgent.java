package br.com.negociation.agents;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.negociation.Tela1;
import br.com.negociation.Utils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Buyer
 */
public class BuyerAgent extends Agent {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(BuyerAgent.class);

	private static String getHash() {

		return "cfp";
	}

	public BuyerAgent() {
		logger.debug("New Agent(Buyer)Create");
	}

	protected void setup() {
		this.addBehaviour(new TickerBehaviour(this, 3000) {
			private static final long serialVersionUID = 1L;
			// Menssagem Template de do CFP.
			private MessageTemplate mt;
			// Count de receivers.
			private int receivers = 0;
			// Guarda o vendedor da melhor proposta.
			private String bestMerchant;
			// Guarda o melhor preço de proposta.
			private Double bestPrice;
			// Usado para quando a negociação acabar não se iniciar uma nova.
			private boolean isOrderSucess = false;
			// Pega todos os agentes Merchant do arquivo jada-main-container.properties logo
			// na inicializão do behaviour
			private List<String> allMerchants = Utils.agentsMerchant();
			// Verifica se é a primeira proposta.
			private boolean isFirstPropose = true;
			// Define qual o produto que o agente deseja negociar.
			private String productOrder = "Acucar";

			public void onTick() {
				logger.debug("Agent [" + Utils.prefixAgent(myAgent.getName()) + "] start action. ");
				ACLMessage msg = myAgent.receive(mt);
				if (msg != null) {

					if (msg.getPerformative() == ACLMessage.REFUSE) {
						logger.debug("REFUSE: " + Utils.prefixAgent(msg.getSender().getName()));
						logger.info("REFUSE: " + msg.toString());
						receivers++;
					}
					// Verifica se a menssagem recebida é uma proposta.
					if (msg.getPerformative() == ACLMessage.PROPOSE) {
						logger.info("PROPOSE: " + msg.toString());
						receivers++;
						// Compara se o preço qual produto é mais barato
						if (isFirstPropose || Double.parseDouble(msg.getContent()) < bestPrice) {
							logger.info("Best propose " + msg.getContent() + " from "
									+ Utils.prefixAgent(msg.getSender().getName()));
							this.bestPrice = Double.parseDouble(msg.getContent());
							this.bestMerchant = Utils.prefixAgent(msg.getSender().getName());
							isFirstPropose = false;
						}
						// Verifica se os todos os agents ja reponder.
						if (receivers == allMerchants.size()) {
							// Envia a rejeição para todos os agentes que enviaram suas propostas
							for (String merchant : allMerchants) {
								if (!merchant.equals(bestMerchant)) {
									ACLMessage reject = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
									reject.addReceiver(new AID(merchant, AID.ISLOCALNAME));
									reject.setContent(productOrder);
									reject.setConversationId(reject.getContent().substring(0, 2) + getHash()
											+ System.currentTimeMillis());
									reject.setReplyWith(getHash() + System.currentTimeMillis());
									logger.debug(
											Utils.prefixAgent(myAgent.getName()) + " SEND REJECT! FOR:" + merchant);
									logger.info("SEND REJECT: " + reject.toString());
									myAgent.send(reject);
								}
							}
							// Envia a Accept para o agente que lhe enviou a melhor proposta
							ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
							order.addReceiver(new AID(bestMerchant, AID.ISLOCALNAME));
							order.setContent(productOrder);
							order.setConversationId(
									order.getContent().substring(0, 2) + getHash() + System.currentTimeMillis());
							order.setReplyWith(getHash() + System.currentTimeMillis());
							// Envia o Confirmação da proposta.
							logger.debug(Utils.prefixAgent(myAgent.getName()) + " SEND ACCEPT! FOR: " + bestMerchant);
							logger.info("SEND ACCEPT: " + order.toString());
							myAgent.send(order);
							// Template para definir assunto direto com o vendedor para efetuar a compra
							mt = MessageTemplate.and(MessageTemplate.MatchConversationId(order.getConversationId()),
									MessageTemplate.MatchInReplyTo(order.getReplyWith()));
						}
					} else if (msg.getPerformative() == ACLMessage.INFORM) {
						if (!msg.getContent().equals("DONE!")) {
							// Recebe o pedido.
							logger.debug("Agent " + Utils.prefixAgent(myAgent.getName()) + " BUY: " + msg.getContent()
									+ " FROM: " + Utils.prefixAgent(msg.getSender().getName()));

							isOrderSucess = true;
							logger.info("INFORM RESULT:" + msg.toString());
							block();
						} else {
							logger.info("INFORM DONE:" + msg.toString());
						}
					}

				} else if (!isOrderSucess) {
					// Montando menssagem CFP;
					ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
					// Adicionando Receivers
					for (String agent : allMerchants) {
						logger.debug("Add receiver => " + agent);
						cfp.addReceiver(new AID(agent, AID.ISLOCALNAME));
					}
					// Adionando conteudo da menssagem - no caso o produto
					cfp.setContent(productOrder);
					// Cria o Hash da Conversa.
					cfp.setConversationId(cfp.getContent().substring(0, 2) + getHash() + System.currentTimeMillis());
					cfp.setReplyWith(getHash() + System.currentTimeMillis());

					mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
							MessageTemplate.MatchInReplyTo(getHash() + System.currentTimeMillis()));
					// Enviado a menssage

					logger.debug("SEND:" + "\nAgent: " + Utils.prefixAgent(myAgent.getName()) + "\nCFP CONTENT => "
							+ cfp.getContent() + "\n ID:" + cfp.getConversationId());
					logger.info("CFP: " + cfp.toString());
					send(cfp);
				}
			}
		});

	}
}