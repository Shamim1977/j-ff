package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.translation.JFFStrings;

@SuppressWarnings("serial")
public class JFFShowCredits extends AbstractAction {

	
	private JFFStrings S;
	
	private Component Parent;
	
	public JFFShowCredits(Component p, JFFBundledItems items){
		super(items.S.showCredits(),new ImageIcon("img/info.png"));
	
		Parent=p;
		S=items.S;
		putValue(SHORT_DESCRIPTION, items.S.showCreditsDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JOptionPane.showMessageDialog(Parent,
			    S.credits(),S.creditsDialogName(),JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon("img/infobig.png"));

		
	}

}