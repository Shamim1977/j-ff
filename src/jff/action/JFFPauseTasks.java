package jff.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.JFFMainFrameImpl.RestartableThread;
import jff.task.FFMultipleGroupTask;

@SuppressWarnings("serial")
public class JFFPauseTasks extends AbstractAction {


	private FFMultipleGroupTask Tasks;
	private RestartableThread Executor;
	private RestartableThread Refresher;
	
	public JFFPauseTasks(JFFBundledItems items){
		super(items.S.pauseTasks(),new ImageIcon("img/pausetasks.png"));
		
		Tasks=items.Tasks;
		Executor=items.Executor;
		Refresher=items.Refresher;
		
		putValue(SHORT_DESCRIPTION, items.S.pauseTasksDescription());  // Will appear as tooltip text.
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if (Tasks.isRunning()){
			Executor.interrupt();
		}

		try {//this stuff has to be executed on another thread to avoid the gui freeze
			Executor.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Refresher.interrupt();

	}

}
