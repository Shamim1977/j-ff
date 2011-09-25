package jff.gui;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;

public class JFFTutorialFrameImpl extends JFrame implements JFFTutorialFrame {

	private JScrollPane Panel;
	
	public JFFTutorialFrameImpl(JFFBundledItems items){
		super(items.S.tutorialFrameName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        JTextPane textPane = new JTextPane(); // creates an empty text pane
        textPane.setContentType("text/html"); // lets Java know it will be HTML                  
        textPane.setEditable(false);
      
        
        try {
			textPane.setPage("file://"+new File("tutorial/help-"+items.S.lang()+".html").getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Panel=new JScrollPane(textPane);
        
		
        add(Panel);
        
        this.setPreferredSize(new Dimension(640,480));
	    
        pack();
        setLocationRelativeTo(null);
	      
        setVisible(true);
	}
}
