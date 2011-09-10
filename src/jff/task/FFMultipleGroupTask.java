package jff.task;

import java.util.Vector;

public interface FFMultipleGroupTask extends Runnable {

	void add(FFGroupTask t);

	FFGroupTask get(int i);

	boolean isEmpty();
	boolean isRunning();


	int sizeInTasks();
	int sizeInFiles();
	
	int runningTasks();
	int pausedTasks();

	Vector<FFGroupTask> groupTasks();

	public void remove(FFGroupTask r);
	public void removeAll();

}
