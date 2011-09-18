package jff.task;

import java.io.BufferedWriter;

import jff.item.FFCommandLine;

public interface FFSingleTask extends Runnable {

	float progressPercentOfTheSecondPass();
	float progressPercentOfTheFirstPass();
	float progressPercentOfTheTask();

	boolean isDone();
	boolean isRunning();
	
	FFCommandLine commandLine();
	
	public final static String S="        ";

	void setOutputDebugInfo(BufferedWriter bw);
}
