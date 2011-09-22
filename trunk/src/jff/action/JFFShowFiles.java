package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.tabbedpane.JFFTabbedPane;

@SuppressWarnings("serial")
public class JFFShowFiles extends AbstractAction {

	private Component Parent;
	
	
	public JFFShowFiles(Component c, JFFBundledItems items){
		super(items.S.showFiles(),new ImageIcon("img/showfiles.png"));
	
		Parent=c;
		
		putValue(SHORT_DESCRIPTION, items.S.showFilesDescription());  // Will appear as tooltip text.
        
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		((JFFMainFrameImpl)Parent).showFiles();
		
		
		
	}

}