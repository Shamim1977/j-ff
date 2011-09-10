package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFSelectableVideoFileImpl;

@SuppressWarnings("serial")
public class JFFQuit extends AbstractAction {

	private JFFBundledItems Items;

	public JFFQuit(JFFBundledItems items){
		super("Esci",new ImageIcon("img/quit.png"));
		
		Items=items;
		
		putValue(SHORT_DESCRIPTION, "salva lo stato dell'applicazione ed esce");  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}