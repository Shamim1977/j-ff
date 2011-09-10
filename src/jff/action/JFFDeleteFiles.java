package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.item.JFFGroupSelectableVideoFile;

@SuppressWarnings("serial")
public class JFFDeleteFiles extends AbstractAction {

	private JFFGroupSelectableVideoFile Files;
	private JFFTable Table;
	
	public JFFDeleteFiles(JFFTable t, JFFBundledItems items){
		super("Elimina files",new ImageIcon("img/delete.png"));
		
		Files=items.Files;
		Table=t;
		
		putValue(SHORT_DESCRIPTION, "elimina i files selezionati (solamente dall'applicazione)");  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Files.removeSelected();
		
		((JFFTableImpl)Table).rebuild();
		
	}

}