package br.com.negociation.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

/**
 * MainContainer
 */
public class MainContainer extends AgentContainer {

    private static AgentContainer _ISTANCE;

    private MainContainer(jade.wrapper.ContainerProxy cp, jade.core.AgentContainer impl, java.lang.String platformName) {
        super(cp,impl,platformName);
    }

    /**
     * Retorna a istancia unica da tela para ser possível alterar em qualquer
     * momento na aplicação
     * 
     * @return
     */
    public static AgentContainer getInstance() {
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        if (_ISTANCE == null) {
            _ISTANCE = rt.createMainContainer(profile);
        }
        return _ISTANCE;
    }

}