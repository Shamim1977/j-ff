package jff.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.tabbedpane.JFFTabbedPane;

@SuppressWarnings("serial")
public class JFFShowOptions extends AbstractAction {

	private JFFTabbedPane TabbedPane;
	
	
	public JFFShowOptions(JFFTabbedPane p){
		super("Mostra opzioni",new ImageIcon("img/showoptions.png"));
	
		TabbedPane=p;
		
		putValue(SHORT_DESCRIPTION, "mostra il menu\' di modifica delle opzioni");  // Will appear as tooltip text.
        
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		TabbedPane.extendPane();
		
		
		
	}

}