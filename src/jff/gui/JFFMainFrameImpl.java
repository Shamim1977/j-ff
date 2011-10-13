package jff.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import jff.action.JFFQuit;
import jff.gui.tabbedpane.JFFTabbedPane;
import jff.gui.tabbedpane.JFFTabbedPaneImpl;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.gui.treetable.refresher.JFFProgressRefresherImpl;
import jff.item.FFOptions;
import jff.item.FFOptionsImpl;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFGroupSelectableVideoFileImpl;
import jff.task.FFMultipleGroupTask;
import jff.task.FFMultipleGroupTaskImpl;
import jff.translation.JFFStrings;
import jff.translation.JFFStringsImpl;
import jff.utility.JFFPath;



/**
 * An implementation of JFFMainFrame.<br />
 * 
 * The frame is divided in three parts, from up to down:<br />
 * <ul>
 * <li>the files table (a JFFTable)</li>
 * <li>the options tabbed panel (a JFFTabbedPane)</li>
 * <li>the task table (a JFFTreeTable)</li>
 * </ul>
 * 
 * It also provides a menu bar (a JFFMenuBar) and a tool bar (a JFFToolBar)
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
@SuppressWarnings("serial")
public class JFFMainFrameImpl extends JFrame implements JFFMainFrame {

		
	/**
	 * the menu bar of the application
	 */
	private JFFMenuBar MenuBar;
	
	/**
	 * the tool bar of the application
	 */
	private JFFToolBar ToolBar;
	
	/**
	 * the files table of the application
	 */
	private JFFTable Table;
	
	/**
	 * the task table of the application
	 */
	private JFFTreeTable TreeTable;
	
	/**
	 * the option tabbed pane of the application
	 */
	private JFFTabbedPane TabbedPane; 
	
	/**
	 * the scrollpane of the files table
	 */
	private JScrollPane TablePane;
	
	/**
	 * the panel of the options tabbed pane 
	 */
	private JPanel TabbedPanePane;
	
	/**
	 * the scrollpane of the task table
	 */
	private JScrollPane TreeTablePane;
	
	
	/**
	 * the elements to display
	 */
	private JFFBundledItems Items;
	
	
	
	/**
	 * A thread which can be restarted always and at any time 
	 * 
	 *
	 * @version %I%
	 * 
	 * @author Francesco Fornasini
	 *
	 */
	public class RestartableThread {
		
		/**
		 * the runnable item that will be executed at every restart
		 */
		private Runnable R;
		
		/**
		 * the actual executing thread
		 */
		private Thread T;
		
		/**
		 * Create the RestartableThread (without starting it) 
		 * 
		 * @param r the runnable item that will be used to create the thread
		 */
		public RestartableThread(Runnable r){
			R=r;
		}
		
		/**
		 * Restarts the thread (call this to start the thread for the first time too)
		 */
		public void restart(){
			
			if (T!=null)
				T.interrupt();
			
			T=new Thread(R);
			T.start();
		}
		
		/**
		 * interrupts the thread (calling interrupt() on the thread)
		 */
		public void interrupt(){
			T.interrupt();
		}
		/**
		 * returns the current running thread
		 * 
		 * @return the current running thread
		 */
		public Thread getThread(){
			return T;
		}
	}
	
	/**
	 * A convenient group of the application items used to exchange all the data between actions and tables.
	 * 
	 * It contains a:<br />
	 * <ul>
	 * <li>JFFStrings that contains the actual translation for the strings to be displayed</li>
	 * <li>JFFGroupSelectableVideoFile that contains the video files to be showed on the files table</li>
	 * <li>FFMultipleGroupTask that contains the tasks info</li>
	 * <li>FFOptions that contains the options with whom a new task will be created</li>
	 * <li>A RestartableThread that will execute the tasks</li>
	 * <li>A RestartableThread that will refresh the data displayed on the gui</li>
	 * </ul>
	 * 
	 * Beware that the Refresher thread has to be inizialized separately after the construction of the tables.
	 * Otherwise a nullPointerException will be thrown 
	 * 
	 * @version %I%
	 * 
	 * @author Francesco Fornasini
	 *
	 */
	public class JFFBundledItems {
		
		/**
		 * the actual translation
		 */
		public JFFStrings S;
		
		/**
		 * the video files
		 */
		public JFFGroupSelectableVideoFile Files;
		
		/**
		 * the tasks
		 */
		public FFMultipleGroupTask Tasks;
		
		/**
		 * the options with whom a new task will be created
		 */
		public FFOptions Options;
		
		/**
		 * the thread which will execute the tasks
		 */
		public RestartableThread Executor;
		
		/**
		 * the thread which will refresh the gui 
		 */
		public RestartableThread Refresher;
		
		/**
		 * Constructs the items with the directives found in the specified init file 
		 * 
		 * @param filePath the path of the file with the init options
		 */
		public JFFBundledItems(String filePath) {
			
			try {
				
				FileReader fstream=new FileReader(filePath);
				BufferedReader b=new BufferedReader(fstream);
			
				S=new JFFStringsImpl(b);
			
				b.close();
				
				FileReader fstream1=new FileReader(filePath);
				BufferedReader b1=new BufferedReader(fstream1);
			
				Files=new JFFGroupSelectableVideoFileImpl(b1);
			
				b1.close();
				
				FileReader fstream2=new FileReader(filePath);
				BufferedReader b2=new BufferedReader(fstream2);
			
				Tasks=new FFMultipleGroupTaskImpl(S,b2); 
				
				b2.close();
				
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


	/**
	 * Constructs the items with the default parameters 
	 */
	public JFFBundledItems(){
			
			S=new JFFStringsImpl();
			Files=new JFFGroupSelectableVideoFileImpl();
	        Tasks=new FFMultipleGroupTaskImpl(S); 
	        Options=new FFOptionsImpl("iPhone4",new File(System.getProperty("user.dir")),false);
	        Executor=new RestartableThread(Tasks);
			Executor.restart();
			
			   
		}
	}
	
	/**
	 * Creates the items and the gui starting with the file in JFFPath.INITFILE<br />
	 * if the file isnt found then the item will be created with default parameters
	 */
	public JFFMainFrameImpl(){
			super();

	      //Create the app items
		
	        if (new File(JFFPath.INITFILE).isFile())
				Items=new JFFBundledItems(JFFPath.INITFILE);
	        else
				Items=new JFFBundledItems();	        

	        setTitle(Items.S.appFrameName());

	        addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent we) {
					  new JFFQuit(Items).actionPerformed(null);
				  }
				});
	      //Create the Table
	        Table = new JFFTableImpl(Items);
	        ((JFFTableImpl)Table).rebuild();
	        TablePane=new JScrollPane((JTable)Table);
	        
	        
			TablePane.setBorder(BorderFactory.createTitledBorder(Items.S.openedFiles()));
	        
	      //Create the tabbed pane
	        TabbedPane= new JFFTabbedPaneImpl(Items); 
	       
	      //Create the TreeTable
		    TreeTable = new JFFTreeTableImpl(Items);
		  
		  //Finish to initialize the Bundle (you cant start the refresher until the tables are ready to be refreshed)
		    Items.Refresher=new RestartableThread(new JFFProgressRefresherImpl(TreeTable,Items));//Items is not complete yet, but this constructor doesnt use Item.Refresher so this line doesnt throw a nullPointerException 
			Items.Refresher.restart();//start the refresher
		    
			TreeTablePane=new JScrollPane((JTable)TreeTable);

			TreeTablePane.setBorder(BorderFactory.createTitledBorder(Items.S.processes()));
		    
		  //Create the menu bar.
	        MenuBar = new JFFMenuBarImpl(this,Table,TabbedPane,TreeTable,Items);
  
	        setJMenuBar((JMenuBar) MenuBar);
		    
	      //Create the ToolBar
	        ToolBar = new JFFToolBarImpl(this,Table,TreeTable,Items);
	      
	        add((JToolBar) ToolBar);
	        
	      
	        TabbedPanePane=new JPanel();
	        TabbedPanePane.setLayout(new BoxLayout(TabbedPanePane,BoxLayout.X_AXIS));
			TitledBorder tb=BorderFactory.createTitledBorder(Items.S.options());
			tb.setTitleJustification(TitledBorder.RIGHT);
			TabbedPanePane.setBorder(tb);
			
			
			TabbedPanePane.add((JTabbedPane) TabbedPane);
			
			
			//add the panes in the main layout

			add(TablePane);
			add(TabbedPanePane);
			add(TreeTablePane);
			
	        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	        
	        //Display the window.

	        this.setPreferredSize(new Dimension(960,640));
	      
	        pack();
	        setLocationRelativeTo(null);
		      
	        setVisible(true);
	
	        
		}

	@Override
	public void hideOptions(){
		
		TabbedPanePane.setVisible(false);
		pack();
		repaint();
	}	

	@Override
	public void hideFiles(){
		
		TablePane.setVisible(false);
		pack();
		repaint();
	}	

	@Override
	public void showOptions(){
		
		TabbedPanePane.setVisible(true);
		pack();
		repaint();
	}	

	@Override
	public void showFiles(){
		
		TablePane.setVisible(true);
		pack();
		repaint();
	}	

	
	
}
