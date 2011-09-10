package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.gui.table.JFFTableModel;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFSelectableVideoFile;
import jff.item.JFFSelectableVideoFileImpl;
import jff.item.VideoFile;
import jff.item.VideoFileImpl;

@SuppressWarnings("serial")
public class JFFAddFiles extends AbstractAction {

	private Component Parent;
	private JFileChooser FileChooser;
	private JFFGroupSelectableVideoFile Files;
	private JFFTable Table;
	
	public JFFAddFiles(Component p, JFFTable t, JFFBundledItems items){
		
		super("Aggiungi files",new ImageIcon("img/add.png"));
		
		Parent=p;
		Files=items.Files;
		Table=t;
		
		putValue(SHORT_DESCRIPTION, "apre i files da convertire");  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Create a file chooser
		FileChooser=new JFileChooser();
		FileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileChooser.setMultiSelectionEnabled(true);
		
		//In response to a button click:
		int returnVal =FileChooser.showOpenDialog(Parent);
		
		if ((returnVal==JFileChooser.APPROVE_OPTION)&&(FileChooser.getSelectedFile()!=null)){
			for (int i=0;i<FileChooser.getSelectedFiles().length;i++){
				Files.add(new JFFSelectableVideoFileImpl(FileChooser.getSelectedFiles()[i],true));
				System.out.println(FileChooser.getSelectedFiles()[i].getAbsolutePath());
			}
		} //else
			//Files.add(FileChooser.getCurrentDirectory());
		
		((JFFTableImpl)Table).rebuild();
		
	}

}
