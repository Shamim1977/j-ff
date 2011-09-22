package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;

@SuppressWarnings("serial")
public class JFFShowCredits extends AbstractAction {

	static Icon I=new ImageIcon("img/info.png");
	
	private Component Parent;
	
	public JFFShowCredits(Component p, JFFBundledItems items){
		super(items.S.showCredits(),I);
	
		Parent=p;
		
		putValue(SHORT_DESCRIPTION, items.S.showCreditsDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JOptionPane.showMessageDialog(Parent,
			    "Programmato da:"+System.getProperty("line.separator")+System.getProperty("line.separator")+"Francesco Fornasini",
			    "Info sviluppatori",
			    JOptionPane.INFORMATION_MESSAGE,
			    I);

		
	}

}