package jff.item;

import java.io.File;
import java.io.Serializable;

public interface VideoFile extends Serializable {

	public void checkData();
	
	public File file(); 
	public int width() throws ValueNotFoundException;
	public int height() throws ValueNotFoundException;
	public String PAR() throws ValueNotFoundException;
	public String DAR() throws ValueNotFoundException;
	public int Duration() throws ValueNotFoundException;
	
	public String detailedToString();
	
	@SuppressWarnings("serial")
	public class ValueNotFoundException extends Exception{}
}
