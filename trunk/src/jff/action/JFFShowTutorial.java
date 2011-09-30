package jff.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrame;
import jff.gui.JFFMainFrameImpl;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;

@SuppressWarnings("serial")
public class JFFShowTutorial extends AbstractAction {

	private JFFBundledItems Items;
	
	public JFFShowTutorial(JFFBundledItems items){
		super(items.S.showTutorial(),new ImageIcon("img/tutorial.png"));
		
		Items=items;
		
		putValue(SHORT_DESCRIPTION, items.S.showTutorialDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			
		 	java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

	        if( desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

                try {
					desktop.browse(new File("tutorial/help-"+Items.S.lang()+".html").toURI());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            
	        }
	        
	}

}