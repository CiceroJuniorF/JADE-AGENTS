package br.com.smartgrid.simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * HandlerControl
 */
public class HandlerControl implements ActionListener {
    private JButton start, stop;

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource() == start){
            CreateSimulator.createCotainer();
            this.start.setEnabled(false);
            this.stop.setEnabled(true);
        }
            
			
		if(evento.getSource() == stop){
            CreateSimulator.removeContainer();
            this.stop.setEnabled(false);
            this.start.setEnabled(true);
        }

    }
	
	public HandlerControl(Object...components){
		if(components[0] instanceof JButton){
            this.start = (JButton)components[0];
        }
        if(components[1] instanceof JButton){
            this.stop = (JButton)components[1];
            this.stop.setEnabled(false);
        }
	}

	
    
    

    
}