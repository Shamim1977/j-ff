package jff.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jff.gui.JFFMainFrame;
import jff.gui.JFFMainFrameImpl;
import jff.translation.JFFStrings;
import jff.translation.JFFStringsImpl;
import jff.utility.JFFPath;

/**
 * The class that starts the application GUI. 
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 *
 */
public class jffgui {

	/**
	 * Sets the Look and feel of the UI, then starts the application GUI.
	 * If the GUI is already running it will launch an error dialogue
	 * 
	 * @param args no params accepted
	 */
	public static void main(String[] args) {
		
		try {//tries to set the system look and feel 
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		/* simple way
		JFFMainFrame f=new JFFMainFrameImpl();
		Thread.currentThread().setName("jff gui");
		*/
	
		/* single instance way */ 
	
		try	{

			//create an object of server socket and bind to port 15486
			new ServerSocket(15486);

			new JFFMainFrameImpl();
			
		} catch (BindException exc){
			createAlreadyRunningDialog();
		}
		  catch (IOException exc){
			createAlreadyRunningDialog();
		}

	}

	
	/**
	 * Creates the error dialogue with the language specified in the file
	 */
	private static void createAlreadyRunningDialog() {
		
		FileReader fstream=null;
		try {
			fstream = new FileReader(new File(JFFPath.INITFILE).getAbsolutePath());
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		BufferedReader b=new BufferedReader(fstream);
	
		JFFStrings s=new JFFStringsImpl(b);
	
		try {
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, s.duplicateAppError(), s.appFrameName(), JOptionPane.ERROR_MESSAGE);

	}

}
