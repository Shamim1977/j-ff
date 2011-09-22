package jff.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jff.gui.JFFMainFrame;
import jff.gui.JFFMainFrameImpl;

/**
 * The class that starts the application GUI. It has only the method {@link #main(String[])} 
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
public class JFFGUI {

	/**
	 * Sets the Look and feel of the UI, then starts the application GUI
	 * 
	 * @param args no params accepted
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFFMainFrame f=new JFFMainFrameImpl();
		Thread.currentThread().setName("jff gui");
	}

}
