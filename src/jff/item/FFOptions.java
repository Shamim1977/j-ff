package jff.item;

import java.io.File;
import java.io.Serializable;

public interface FFOptions extends Serializable {

	public void setOutputFormat(String s);
	
	public boolean twoPasses();
	public boolean pads();
	public FFOutputOptions outputOptions();
	public int threads();
	public File outputFolder();
	public boolean verbose();
	
	public void setTwoPasses(boolean b);
	public void setPads(boolean b);
	public void setThreads(int t);
	public void setOutputFile(File f);
	public void setVerbose(boolean b);

	public FFOptions createDuplicate();

	public boolean smallFiles();
	public void setSmallFiles(boolean b);

	
}
