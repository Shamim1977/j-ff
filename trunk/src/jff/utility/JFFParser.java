package jff.utility;

public interface JFFParser {

	public final static String Separator=":"; 
	
	
	public boolean isEmpty();

	public boolean find(String s);

	public String getString();
	public String getStringWithSeparators();
	

	public boolean getBoolean();

	public int getInt();

}
