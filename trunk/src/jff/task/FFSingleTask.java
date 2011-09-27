package jff.task;

import java.io.BufferedWriter;
import java.io.Serializable;

import jff.item.FFCommandLine;

public interface FFSingleTask extends Runnable, Serializable {

	float progressPercentOfTheSecondPass();
	float progressPercentOfTheFirstPass();
	float progressPercentOfTheTask();

	boolean isDone();
	boolean isRunning();
	
	FFCommandLine commandLine();
	
	public final static String T="        ";

	void setOutputDebugInfo(BufferedWriter bw);
}
