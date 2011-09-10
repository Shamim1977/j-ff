package jff.item;

import java.io.File;
import java.util.Vector;

public interface FFCommandLine {

	public VideoFile input();
	public File output();
	public FFOptions options();
	
	public Vector<String> getCommandLine(int pass);// pass 1 or pass 2
}
