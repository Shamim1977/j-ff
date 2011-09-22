package jff.action;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.item.JFFGroupSelectableVideoFile;

@SuppressWarnings("serial")
public class JFFPlayFile extends AbstractAction {

	private JFFGroupSelectableVideoFile Files;
	private JFFTable Table;
	
	public JFFPlayFile(JFFTable t, JFFBundledItems items){
		super(items.S.playFile(),new ImageIcon("img/playvlc2.png"));
		
		Files=items.Files;
		Table=t;
		
		putValue(SHORT_DESCRIPTION, items.S.playFileDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		/**** Oold implementation 
		if (((JFFTableImpl)Table).getSelectedRow()!=-1) {
			Vector<String> commandLine=new Vector<String>();
		
			commandLine.add("vlc");
		//	commandLine.add("-vvv");

			commandLine.add(Files.get(((JFFTableImpl)Table).getSelectedRow()).file().getAbsolutePath());
		
		
			ProcessBuilder pb = new ProcessBuilder(commandLine);
	    
			try {
				Process p=pb.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		*************** */
		
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        if( desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

            try {
				desktop.open(Files.get(((JFFTableImpl)Table).getSelectedRow()).file());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        
        }
		
	}

}