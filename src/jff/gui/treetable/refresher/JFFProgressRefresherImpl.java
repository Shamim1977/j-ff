package jff.gui.treetable.refresher;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.task.FFMultipleGroupTask;

public class JFFProgressRefresherImpl extends Thread implements JFFProgressRefresher {

	private JFFTreeTable TreeTable;
	private FFMultipleGroupTask Tasks;
	private long SleepingMillisecs=1000;
	
	public JFFProgressRefresherImpl(JFFTreeTable tt, JFFBundledItems items){
		TreeTable=tt;
		Tasks=items.Tasks;
	}
	
	private void sleepThenRefresh(){
		
		System.out.println("I'm refreshin\' it");
		
		try {
			Thread.sleep(SleepingMillisecs);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
			if (TreeTable!=null)
				((JFFTreeTableImpl)TreeTable).repaint();
		
	}
	
	@Override
	public void run() {
		//shitty solution... will try a better one with Tasks.wait()
		while (Tasks.isRunning()&&!Thread.currentThread().isInterrupted())
			sleepThenRefresh();
		
		Thread.interrupted();
		
		sleepThenRefresh();	
		
		
	}

}
