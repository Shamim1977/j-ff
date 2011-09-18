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



@SuppressWarnings("serial")
public class JFFMainFrameImpl extends JFrame implements JFFMainFrame {

		
	private JFFMenuBar MenuBar;
	private JFFToolBar ToolBar;
	private JFFTable Table;
	private JFFTreeTable TreeTable;
	private JFFTabbedPane TabbedPane; 
	
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
		
		public JFFGroupSelectableVideoFile Files;
		public FFMultipleGroupTask Tasks;
		public FFOptions Options;
		public RestartableThread Executor;
		public RestartableThread Refresher;
		
		public JFFBundledItems(String filePath) {
			
			try {
				
				FileReader fstream=new FileReader(filePath);
				BufferedReader b=new BufferedReader(fstream);
			
				Files=new JFFGroupSelectableVideoFileImpl(b);
			
				b.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Tasks=new FFMultipleGroupTaskImpl("Tutti i processi"); 
			
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
			
			Files=new JFFGroupSelectableVideoFileImpl();
	        Tasks=new FFMultipleGroupTaskImpl("Tutti i processi"); 
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
	        JScrollPane scrollpane = new JScrollPane((JTable)Table);
	        
	        
			scrollpane.setBorder(BorderFactory.createTitledBorder("Files aperti"));
	        
	      //Create the tabbed pane
	        TabbedPane= new JFFTabbedPaneImpl(Items); 
	       
	      //Create the TreeTable
		    TreeTable = new JFFTreeTableImpl(Items);
		  
		  //Finish to initialize the Bundle .....
		    Items.Refresher=new RestartableThread(new JFFProgressRefresherImpl(TreeTable,Items));//"this" is not complete 
			Items.Refresher.restart();//start the refresher
		    
		    JScrollPane scrollpane2 = new JScrollPane((JTable)TreeTable);

			scrollpane2.setBorder(BorderFactory.createTitledBorder("Processi"));
		    
		  //Create the menu bar.
	        MenuBar = new JFFMenuBarImpl(Table,TabbedPane,TreeTable,Items);
  
	        setJMenuBar((JMenuBar) MenuBar);
		    
	      //Create the ToolBar
	        ToolBar = new JFFToolBarImpl(Table,TreeTable,Items);
	      
	        add((JToolBar) ToolBar);
	        
	       
	      
	        add(scrollpane);
	        
	        //add(Box.createVerticalGlue());
	        
	        JPanel ppp=new JPanel();
			ppp.setLayout(new BoxLayout(ppp,BoxLayout.X_AXIS));
			TitledBorder tb=BorderFactory.createTitledBorder("Opzioni");
			tb.setTitleJustification(TitledBorder.RIGHT);
			ppp.setBorder(tb);
			ppp.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					if (TabbedPane.isReduced())
						TabbedPane.extendPane();
				
				}

				});
			
	        ppp.add((JTabbedPane) TabbedPane);
			ppp.add(Box.createHorizontalGlue());
	        
			add(ppp);
			
	        //add(Box.createVerticalGlue());
	        
	        add(scrollpane2);
	        
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

		

	
	
}
