package jff.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.tabbedpane.JFFTabbedPane;

@SuppressWarnings("serial")
public class JFFShowOptions extends AbstractAction {

	private JFFTabbedPane TabbedPane;
	
	
	public JFFShowOptions(JFFTabbedPane p, JFFBundledItems items){
		super(items.S.showOptions(),new ImageIcon("img/showoptions.png"));
	
		TabbedPane=p;
		
		putValue(SHORT_DESCRIPTION, items.S.showOptionsDescription());  // Will appear as tooltip text.
        
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		TabbedPane.extendPane();
		
		
		
	}

}