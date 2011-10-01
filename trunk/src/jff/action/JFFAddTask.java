package jff.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.gui.table.JFFTableModel;
import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.gui.treetable.JFFTreeTableModel;
import jff.gui.treetable.JFFTreeTableModelImpl;
import jff.gui.treetable.structure.TreeTableModelAdapter;
import jff.item.FFCommandLineImpl;
import jff.item.FFOptions;
import jff.item.FFOptionsImpl;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFSelectableVideoFileImpl;
import jff.task.FFGroupTask;
import jff.task.FFGroupTaskImpl;
import jff.task.FFMultipleGroupTask;
import jff.task.FFSingleTask;
import jff.task.FFSingleTaskImpl;
import jff.translation.JFFStrings;

@SuppressWarnings("serial")
public class JFFAddTask extends AbstractAction {

	private JFFTable Table;
	private JFFTreeTable TreeTable;

	private JFFGroupSelectableVideoFile Files;
	private FFMultipleGroupTask Tasks;
	private FFOptions Options;
	
	private JFFStrings S;
	
	public JFFAddTask(JFFTable b, JFFTreeTable tb, JFFBundledItems items){
		super(items.S.addTask(),new ImageIcon("img/addtask.png"));
		
		Table=b;
		TreeTable=tb;
		Files=items.Files;
		Tasks=items.Tasks;
		Options=items.Options;
		S=items.S;
		putValue(SHORT_DESCRIPTION,items.S.addTaskDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if (Files.getSelected().size()>0){
		
			FFGroupTask gT=new FFGroupTaskImpl(S);
			FFSingleTask f;
		
			for (int i=0;i<Files.getSelected().size();i++){
			
				f=new FFSingleTaskImpl(
					
						new FFCommandLineImpl(
							Files.getSelected().get(i),
							Options.createDuplicate()
								)
					);
			
				gT.add(f);
			
			}
		

			Tasks.add(gT);
			Files.removeSelected();
		
			((JFFTreeTableImpl)TreeTable).rebuild();
			((JFFTableImpl)Table).rebuild();
		}
	}

}

