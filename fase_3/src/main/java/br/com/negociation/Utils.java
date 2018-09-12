package br.com.negociation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.negociation.agents.BuyerAgent;

/**
 * Util
 */
public class Utils {

    private static Logger logger = Logger.getLogger(Utils.class);

    /* Este método retorna o nome do agente de forma limpa, sem o hash em sua
    *   frente.
    */ 
    public static String prefixAgent(String agent) {
        return agent.split("@")[0];
    }

    //Este metódo ira buscar no arquivo de configuração os Agents com Merchant no nome.
    public static List<String> agentsMerchant() throws RuntimeException {
        try {
            // Pega o Arquvi
            Properties properties = new Utils().getProperties();
            // Pega os agentes salvos
            String agents = properties.getProperty("agents");
            // Separa os agente
            String[] agentsFull = agents.split(";");

            // Lista de todos agentes
            List<String> agentsMerchant = new ArrayList<>();
            for (String agent : agentsFull) {
                if (agent.contains("merchant")) {
                    agentsMerchant.add(agent.split(":")[0]);
                }
            }

            return agentsMerchant;

        } catch (Exception e) {
            logger.error("Ocorreu um erro ao carregar o arquivo" + e);
            throw new RuntimeException("Erro ao Carregar o arquivo");
        }
    }

    //Ira buscar no arquivo de configuração todos os agents configurados
    public static List<String> allAgents() throws RuntimeException {
        try {
            // Pega o Arquvi
            Properties properties = new Utils().getProperties();
            // Pega os agentes salvos
            String agents = properties.getProperty("agents");
            // Separa os agente
            String[] agentsFull = agents.split(";");

            // Lista de todos agentes
            List<String> agentsMerchant = new ArrayList<>();
            for (String agent : agentsFull) {
                agentsMerchant.add(agent.split(":")[0]);
            }

            return agentsMerchant;

        } catch (Exception e) {
            logger.error("Ocorreu um erro ao carregar o arquivo" + e);
            throw new RuntimeException("Erro ao Carregar o arquivo");
        }
    }

    // Retorna o arquivo de propriedades
    public Properties getProperties() throws Exception {
        logger.debug("Carregando arquivo de propriedades");
        // carrega o arquivo de propriedades
        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("jade-main-container.properties");
        properties.load(is);
        return properties;
    }

}