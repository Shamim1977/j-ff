package jff.gui.tabbedpane;

/**
 * A tabbed panel connected with the options for the application
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
public interface JFFTabbedPane {

	/**
	 * Tests if the panel is visible or reduced
	 * 
	 * @return true if the panel is visible, false otherwise
	 */
	public boolean isReduced();
	
	/**
	 * Reduce the panel, setting it invisible
	 */
	public void reducePane();
	
	/**
	 * Show the panel, setting it visible
	 */
	public void extendPane();
	
}

