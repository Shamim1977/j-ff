package jff.utility;

/**
 * A JFFParser take a text line and extrapolates the content described into it.
 * this is the format of a content line:<br /><br />
 * tag-separator-spaces-data-spaces<br /><br />
 * 
 * the tag is the name of the data contained in the line<br />
 * the separator is the string specified in the {@link #Separator}<br />
 * spaces are optional spaces<br />
 * data is the information that should be get with the methods get....()
 * 
 * @version %I%
 *  
 *  
 * @author Francesco Fornasini
 *
 */
public interface JFFParser {

	/**
	 * The Separator between tag and data
	 */
	public final static String Separator=":"; 
		
	/**
	 * Checks if the String contain some data 
	 * 
	 * @return true if the string is empty or null, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Checks if the String has tag==s
	 * 
	 * @param s the tag to search
	 * @return true if the string has tag==s, false otherwise
	 */
	public boolean find(String s);

	/**
	 * Gets the data of the String, if data don't contains Separator
	 * 
	 * @see #getStringWithSeparators()
	 * 
	 * @return the data contained in the String, parsed as a String
	 * 
	 */
	public String getString();
	
	/**
	 * Gets the data of the String, if you don't know if the data
	 * should contain also the Separator use this method, otherwise use {@link #getString()}
	 * 
	 * @return the data contained in the String, parsed as a String
	 */
	public String getStringWithSeparators();
	
	/**
	 * Gets the data of the String
	 * 
	 * @return the data contained in the String, parsed as a Boolean
	 */
	public boolean getBoolean();

	/**
	 * Gets the data of the String
	 * 
	 * @return the data contained in the String, parsed as a int
	 */
	public int getInt();

}
