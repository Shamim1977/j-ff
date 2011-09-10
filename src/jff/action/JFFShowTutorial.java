package jff.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;

@SuppressWarnings("serial")
public class JFFShowTutorial extends AbstractAction {

	
	public JFFShowTutorial(){
		super("Guida",new ImageIcon("img/tutorial.png"));
		
		putValue(SHORT_DESCRIPTION, "mostra la guida per l\'utilizzo dell\'applicazione");  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}