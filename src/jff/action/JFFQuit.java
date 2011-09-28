package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
		super(items.S.quit(),new ImageIcon("img/quit.png"));
		
		Items=items;
		
		putValue(SHORT_DESCRIPTION,items.S.quitDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		new JFFPauseTasks(Items).actionPerformed(null);
		
		try {
			
			FileOutputStream fos = new FileOutputStream(new File("tasks"));
			ObjectOutputStream out = new ObjectOutputStream(fos);
		
			out.writeObject(Items.Tasks);
			out.close();
		
		} catch(IOException ex) {
		
			ex.printStackTrace();
		}
		
try {
			
			FileOutputStream fos = new FileOutputStream(new File("videofiles"));
			ObjectOutputStream out = new ObjectOutputStream(fos);
		
			out.writeObject(Items.Files);
			out.close();
		
		} catch(IOException ex) {
		
			ex.printStackTrace();
		}
		
		System.exit(0);
	}

}