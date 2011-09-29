package jff.task;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.Vector;

import jff.translation.JFFStrings;

public interface FFMultipleGroupTask extends Runnable, Serializable {

	void add(FFGroupTask t);

	FFGroupTask get(int i);

	boolean isEmpty();
	boolean isRunning();


	int sizeInTasks();
	int sizeInFiles();
	
	

	Vector<FFGroupTask> groupTasks();

	public void remove(FFGroupTask r);
	public void removeAll();
	public void setLanguage(JFFStrings s);

	public int waitingTasks();
	public int runningTasks();
	public int doneTasks();
	
}
