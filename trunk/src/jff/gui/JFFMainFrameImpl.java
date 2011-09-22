package jff.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import jff.action.JFFAddFiles;
import jff.action.JFFAddTask;
import jff.action.JFFDeleteFiles;
import jff.gui.tabbedpane.JFFTabbedPane;
import jff.gui.tabbedpane.JFFTabbedPaneImpl;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.gui.treetable.refresher.JFFProgressRefresherImpl;
import jff.item.FFCommandLine;
import jff.item.FFCommandLineImpl;
import jff.item.FFOptions;
import jff.item.FFOptionsImpl;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFGroupSelectableVideoFileImpl;
import jff.item.VideoFileImpl;
import jff.task.FFGroupTask;
import jff.task.FFGroupTaskImpl;
import jff.task.FFMultipleGroupTask;
import jff.task.FFMultipleGroupTaskImpl;
import jff.task.FFSingleTask;
import jff.task.FFSingleTaskImpl;
import jff.translation.JFFStrings;
import jff.translation.JFFStringsImpl;



@SuppressWarnings("serial")
public class JFFMainFrameImpl extends JFrame implements JFFMainFrame {

		
	private JFFMenuBar MenuBar;
	private JFFToolBar ToolBar;
	private JFFTable Table;
	private JFFTreeTable TreeTable;
	private JFFTabbedPane TabbedPane; 
	
	private JScrollPane TablePane;
	private JPanel TabbedPanePane;
	private JScrollPane TreeTablePane;
	
	
	private JFFBundledItems Items;
	
	
	
	public class RestartableThread{
		private Runnable R;
		private Thread T;
		public RestartableThread(Runnable r){
			R=r;
		}
		public void restart(){
			
			if (T!=null)
				T.interrupt();
			
			T=new Thread(R);
			T.start();
		}
		
		public void interrupt(){
			T.interrupt();
		}
	}
	
	public class JFFBundledItems {
		
		public JFFStrings S;
		public JFFGroupSelectableVideoFile Files;
		public FFMultipleGroupTask Tasks;
		public FFOptions Options;
		public RestartableThread Executor;
		public RestartableThread Refresher;
		
		public JFFBundledItems(String filePath) {
			
			try {
				
				FileReader fstream=new FileReader(filePath);
				BufferedReader b=new BufferedReader(fstream);
			
				S=new JFFStringsImpl(b);
			
				b.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				
				FileReader fstream=new FileReader(filePath);
				BufferedReader b1=new BufferedReader(fstream);
			
				Files=new JFFGroupSelectableVideoFileImpl(b1);
			
				b1.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Tasks=new FFMultipleGroupTaskImpl(S); 
			
			try {
			
				FileReader fstream3=new FileReader(filePath);
				BufferedReader b3=new BufferedReader(fstream3);
			
						
				Options=new FFOptionsImpl(b3);
	        
				b3.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        
	        Executor=new RestartableThread(Tasks);
			Executor.restart();
			
			   
		}


	public JFFBundledItems(){
			
			S=new JFFStringsImpl();
			Files=new JFFGroupSelectableVideoFileImpl();
	        Tasks=new FFMultipleGroupTaskImpl(S); 
	        Options=new FFOptionsImpl("iPhone4",new File(System.getProperty("user.dir")),false);
	        Executor=new RestartableThread(Tasks);
			Executor.restart();
			
			   
		}
	}
	
	public JFFMainFrameImpl(){
			
			super("j-ff");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      //Create the app items
	        
			
	        if (new File("init.txt").isFile())
				Items=new JFFBundledItems("init.txt");
	        else
				Items=new JFFBundledItems();	        

	      //Create the Table
	        Table = new JFFTableImpl(Items);
	        ((JFFTableImpl)Table).rebuild();
	        TablePane=new JScrollPane((JTable)Table);
	        
	        
			TablePane.setBorder(BorderFactory.createTitledBorder(Items.S.openedFiles()));
	        
	      //Create the tabbed pane
	        TabbedPane= new JFFTabbedPaneImpl(Items); 
	       
	      //Create the TreeTable
		    TreeTable = new JFFTreeTableImpl(Items);
		  
		  //Finish to initialize the Bundle .....
		    Items.Refresher=new RestartableThread(new JFFProgressRefresherImpl(TreeTable,Items));//"this" is not complete 
			Items.Refresher.restart();//start the refresher
		    
			TreeTablePane=new JScrollPane((JTable)TreeTable);

			TreeTablePane.setBorder(BorderFactory.createTitledBorder(Items.S.processes()));
		    
		  //Create the menu bar.
	        MenuBar = new JFFMenuBarImpl(this,Table,TabbedPane,TreeTable,Items);
  
	        setJMenuBar((JMenuBar) MenuBar);
		    
	      //Create the ToolBar
	        ToolBar = new JFFToolBarImpl(this,Table,TreeTable,Items);
	      
	        add((JToolBar) ToolBar);
	        
	       
	      
	        
	        //add(Box.createVerticalGlue());
	        
	        TabbedPanePane=new JPanel();
	        TabbedPanePane.setLayout(new BoxLayout(TabbedPanePane,BoxLayout.X_AXIS));
			TitledBorder tb=BorderFactory.createTitledBorder(Items.S.options());
			tb.setTitleJustification(TitledBorder.RIGHT);
			TabbedPanePane.setBorder(tb);
			
			
			TabbedPanePane.add((JTabbedPane) TabbedPane);
			//TabbedPanePane.add(Box.createHorizontalGlue());
	        
			
			//add the panes in the main layout

			add(TablePane);
			add(TabbedPanePane);
			add(TreeTablePane);
			
	//        myPanelAdder=new PanelAdderImpl(this);
	        
	//        ((JPanel)myPanelAdder).setAlignmentX(LEFT_ALIGNMENT);
	        
	        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	        
	//        getContentPane().add((JPanel) myPanelAdder);
	        
	//        myFFTaskGroup=new FFTaskGroupImpl();
	//        myPanelTaskGroup=new PanelTaskGroupImpl();

	        //Display the window.

	        this.setPreferredSize(new Dimension(960,640));
	      
	        pack();
	        setLocationRelativeTo(null);
		      
	        setVisible(true);
	
	        
		}


	public void hideOptions(){
		
		TabbedPanePane.setVisible(false);
		pack();
		repaint();
	}	

	public void hideFiles(){
		
		TablePane.setVisible(false);
		pack();
		repaint();
	}	

	public void showOptions(){
		
		TabbedPanePane.setVisible(true);
		pack();
		repaint();
	}	

	public void showFiles(){
		
		TablePane.setVisible(true);
		pack();
		repaint();
	}	

	
	
}
