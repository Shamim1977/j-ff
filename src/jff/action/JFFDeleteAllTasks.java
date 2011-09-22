package jff.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.JFFMainFrameImpl.RestartableThread;
import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.task.FFGroupTask;
import jff.task.FFMultipleGroupTask;

@SuppressWarnings("serial")
public class JFFDeleteAllTasks extends AbstractAction {

	private JFFTreeTable TreeTable;

	private FFMultipleGroupTask Tasks;
	private RestartableThread Refresher;
	
	public JFFDeleteAllTasks(JFFTreeTable tb, JFFBundledItems items){
		super(items.S.deleteAllTasks(),new ImageIcon("img/deletetasks.png"));
		
		TreeTable=tb;
		Tasks=items.Tasks;
		Refresher=items.Refresher;
		
		putValue(SHORT_DESCRIPTION, items.S.deleteAllTasksDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			if (!Tasks.isRunning()){
				Tasks.removeAll();
				
			}
			
			Refresher.interrupt();
			
		((JFFTreeTableImpl)TreeTable).rebuild();

	}

}



