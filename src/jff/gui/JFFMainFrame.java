package jff.gui;

/**
 * The main Frame of the application.<br />
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
public interface JFFMainFrame {

	/**
	 * shows the options tabbed pane
	 */
	public void showOptions();
	
	/**
	 * shows the files table
	 */
	public void showFiles();
	
	/**
	 * hides the options tabbed pane
	 */
	public void hideOptions();
	
	/**
	 * hides the files table
	 */
	public void hideFiles();
	
}
