package jff.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.JFFMainFrameImpl.RestartableThread;
import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.task.FFMultipleGroupTask;

@SuppressWarnings("serial")
public class JFFStartTasks extends AbstractAction {


	private FFMultipleGroupTask Tasks;
	private RestartableThread Executor;
	private RestartableThread Refresher;
	
	public JFFStartTasks(JFFBundledItems items){
		super("Inizia conversioni",new ImageIcon("img/playtasks.png"));
		
		Tasks=items.Tasks;
		Executor=items.Executor;
		Refresher=items.Refresher;
		
		putValue(SHORT_DESCRIPTION, "inizia le operazioni di conversione");  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if (!Tasks.isRunning()){
			
			Executor.restart();
			Refresher.restart();
			
		}

	}

}