package jff.task;

import jff.item.FFCommandLine;

public interface FFSingleTask extends Runnable {

	float progressPercentOfTheSecondPass();
	float progressPercentOfTheFirstPass();
	float progressPercentOfTheTask();

	boolean isDone();
	boolean isRunning();
	
	FFCommandLine commandLine();
}
