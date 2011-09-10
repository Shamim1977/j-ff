package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.item.FFOptions;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFSelectableVideoFileImpl;

@SuppressWarnings("serial")
public class JFFChangeOutputPath extends AbstractAction {

	private Component Parent;
	private JFileChooser FileChooser;
	private FFOptions Options;
	private JLabel Label;
	
	public JFFChangeOutputPath(Component p, FFOptions o, JLabel l){
		super(null,new ImageIcon("img/changeoutputpath.png"));
		
		Parent=p;
		Options=o;
		Label=l;
		
		putValue(SHORT_DESCRIPTION, "cambia cartella di destinazione");  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Create a file chooser
		FileChooser=new JFileChooser();
		FileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		FileChooser.setMultiSelectionEnabled(false);
		
		//In response to a button click:
		int returnVal =FileChooser.showSaveDialog(Parent);//JFFOptionsManagerImpl.this);
		
		if (FileChooser.getSelectedFile()!=null&&(returnVal==JFileChooser.APPROVE_OPTION)){
			
			Options.setOutputFile(FileChooser.getSelectedFile());
			Label.setText(FileChooser.getSelectedFile().getAbsolutePath());
			
		}
		
	}

}

