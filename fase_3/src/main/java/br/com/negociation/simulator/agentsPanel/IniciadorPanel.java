package br.com.negociation.simulator.agentsPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.negociation.containers.MainContainer;
import br.com.negociation.simulator.agentsPanel.status.Of;
import br.com.negociation.simulator.agentsPanel.status.On;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * IniciadorPanel
 */
public class IniciadorPanel extends JPanel {
    private static IniciadorPanel _ISTANCE;

    private JPanel status = new Of();

    public IniciadorPanel() {        
        ImageIcon icon;
        JLabel imagem;
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout());
        icon = new ImageIcon(getClass().getResource("/img/hidreletrica.png"));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        imagem = new JLabel();
        setLocation(20, 100);        
        setSize(300, 100);
        imagem.setIcon(new ImageIcon(newimg));
        add(imagem);
        add(status);
        AgentController rma = null;
        try {
            rma = MainContainer.getInstance().createNewAgent("iniciador", "br.com.negociation.agents.AgentIniciador",
                    new Object[0]);
            rma.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }


    public JPanel getStatus(){
        return status;
    }

    public void alterStatus(){
        this.remove(status);
        if(this.status instanceof On){
            this.status = new Of();
        }else{
            this.status = new On();
        }
        this.add(status);
        this.repaint();
        this.revalidate();
    }

    /**
     * Retorna a istancia unica da tela para ser possível alterar em qualquer
     * momento na aplicação
     * 
     * @return
     */
    public static IniciadorPanel getInstance() {
        if (_ISTANCE == null) {
            _ISTANCE = new IniciadorPanel();
        }
        return _ISTANCE;
    }

}