package jff.task;

import java.util.Collection;
import java.util.Vector;

public interface FFGroupTask extends Runnable {

	public FFSingleTask get(int i);
	public int size();
	public void add(FFSingleTask t);
	public boolean isEmpty();
	public float progressPercent();
	public Vector<FFSingleTask> tasks();
	public void remove(FFSingleTask r);
	
	public boolean isRunning();
	
}
