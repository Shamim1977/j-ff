package jff.item;

import java.io.File;

public interface VideoFile {

	public void checkData();
	
	public File file(); 
	public int width() throws ValueNotFoundException;
	public int height() throws ValueNotFoundException;
	public String PAR() throws ValueNotFoundException;
	public String DAR() throws ValueNotFoundException;
	public int Duration() throws ValueNotFoundException;
	
	
	@SuppressWarnings("serial")
	public class ValueNotFoundException extends Exception{}
}
