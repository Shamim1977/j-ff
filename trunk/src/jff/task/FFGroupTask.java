package jff.task;

import java.io.BufferedWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

import jff.translation.JFFStrings;

public interface FFGroupTask extends Runnable, Serializable {

	public FFSingleTask get(int i);
	public int size();
	public void add(FFSingleTask t);
	public boolean isEmpty();
	public float progressPercent();
	public Vector<FFSingleTask> tasks();
	public void remove(FFSingleTask r);
	
	public boolean isRunning();
	public void setOutputDebugInfo(BufferedWriter bw);
	
	
	public final static String T="    ";
	public void setLanguage(JFFStrings strings);
	
}
