package jff.action;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrame;
import jff.gui.JFFMainFrameImpl;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.JFFTutorialFrame;
import jff.gui.JFFTutorialFrameImpl;

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
		
			/* ****
		
		 	java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

	        if( desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

                java.net.URI uri;
				try {
					uri = new java.net.URI("http://code.google.com/p/j-ff/wiki/HowToInstall");
					desktop.browse( uri );
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            
	        }

	        		**** */
		
		JFFTutorialFrame tf=new JFFTutorialFrameImpl(Items);
	}

}