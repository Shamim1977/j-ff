package jff.main;

import jff.gui.JFFMainFrame;
import jff.gui.JFFMainFrameImpl;

public class JFFGUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFFMainFrame f=new JFFMainFrameImpl();
		Thread.currentThread().setName("jff gui");
	}

}
