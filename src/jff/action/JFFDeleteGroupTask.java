package jff.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.item.FFCommandLineImpl;
import jff.item.FFOptions;
import jff.item.JFFGroupSelectableVideoFile;
import jff.task.FFGroupTask;
import jff.task.FFGroupTaskImpl;
import jff.task.FFMultipleGroupTask;
import jff.task.FFSingleTask;
import jff.task.FFSingleTaskImpl;

@SuppressWarnings("serial")
public class JFFDeleteGroupTask extends AbstractAction {

	private JFFTreeTable TreeTable;

	private FFMultipleGroupTask Tasks;
	
	public JFFDeleteGroupTask(JFFTreeTable tb, JFFBundledItems items){
		super(items.S.deleteGroupTask(),new ImageIcon("img/deletetask.png"));
		
		TreeTable=tb;
		Tasks=items.Tasks;
		
		putValue(SHORT_DESCRIPTION,items.S.deleteGroupTaskDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if (((JFFTreeTableImpl)TreeTable).tree().getSelectionPath()!=null){
			
			Object obj=((JFFTreeTableImpl)TreeTable).tree().getSelectionPath().getLastPathComponent();
		
			if (obj instanceof FFGroupTask){
				
				Tasks.remove(((FFGroupTask)obj));
			}		
				
				
		}
				
		
			
		((JFFTreeTableImpl)TreeTable).rebuild();

	}

}


