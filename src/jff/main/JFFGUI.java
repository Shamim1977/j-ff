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
		
		/* *simple way
		JFFMainFrame f=new JFFMainFrameImpl();
		Thread.currentThread().setName("jff gui");
		*** */
	
		/* *single instance way* */ 
	
		try

		{

		//creating object of server socket and bind to some port number

			ServerSocket serverSocket = new ServerSocket(15486);

		////do not put common port number like 80 etc.

		////Because they are already used by system

			JFFMainFrame f=new JFFMainFrameImpl();
			
		}

		catch (BindException exc)

		{
			createAlreadyRunningDialog();

		}

		catch (IOException exc)

		{

			createAlreadyRunningDialog();
		
		}

		
	
	}

	private static void createAlreadyRunningDialog() {
		FileReader fstream=null;
		try {
			fstream = new FileReader(new File("init.txt").getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader b=new BufferedReader(fstream);
	
		JFFStrings s=new JFFStringsImpl(b);
	
		try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, s.duplicateAppError(), s.appFrameName(), JOptionPane.ERROR_MESSAGE);

		
	}

}
