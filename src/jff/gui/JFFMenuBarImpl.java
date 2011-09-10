package jff.gui;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jff.action.JFFAddFiles;
import jff.action.JFFAddTask;
import jff.action.JFFDeleteAllTasks;
import jff.action.JFFDeleteFiles;
import jff.action.JFFDeleteGroupTask;
import jff.action.JFFHideOptions;
import jff.action.JFFPauseTasks;
import jff.action.JFFQuit;
import jff.action.JFFShowCredits;
import jff.action.JFFShowOptions;
import jff.action.JFFShowTutorial;
import jff.action.JFFStartTasks;
import jff.action.JFFVLCPlay;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.tabbedpane.JFFTabbedPane;
import jff.gui.table.JFFTable;
import jff.gui.treetable.JFFTreeTable;

@SuppressWarnings("serial")
public class JFFMenuBarImpl extends JMenuBar implements JFFMenuBar {

	private JFFTable Table;
	private JFFTreeTable TreeTable;
	private JFFTabbedPane TabbedPane;
	
	public JMenu File;
	public JMenu Options;
	public JMenu Task;
	public JMenu Info;
	
	
	public JMenuItem AddFile;
	public JMenuItem DeleteFile;
	public JMenuItem ShowFiles;
	public JMenuItem HideFiles;
	public JMenuItem Quit;
	public JMenuItem ShowOptions;
	public JMenuItem HideOptions;
	public JMenuItem AddTask;
	public JMenuItem DeleteTask;
	public JMenuItem PlayTasks;
	public JMenuItem PauseTasks;
	public JMenuItem ClearTasks;
	public JMenuItem Help;
	public JMenuItem Credits;
	public JMenuItem PlayVLC;
	
	public JFFMenuBarImpl(JFFTable b,JFFTabbedPane p, JFFTreeTable tb, JFFBundledItems items){
		super();
		
		Table=b;
		TreeTable=tb;
		TabbedPane=p;
		
		File=new JMenu("File"); 
		Options=new JMenu("Opzioni");
		Task=new JMenu("Processi");
		Info=new JMenu("Informazioni"); 
        
        //Build the menus.
		add(File);
		add(Options);
		add(Task);
		add(Info);
        
        
		AddFile=new JMenuItem(new JFFAddFiles(this.getParent(),Table,items));
		DeleteFile=new JMenuItem(new JFFDeleteFiles(Table,items));
		
        Quit=new JMenuItem(new JFFQuit(items));
		PlayVLC=new JMenuItem(new JFFVLCPlay(Table, items));
        
		ShowOptions=new JMenuItem(new JFFShowOptions(TabbedPane));
        HideOptions=new JMenuItem(new JFFHideOptions(TabbedPane));
        AddTask=new JMenuItem(new JFFAddTask(Table,TreeTable,items));
        DeleteTask=new JMenuItem(new JFFDeleteGroupTask(TreeTable,items));
        ClearTasks=new JMenuItem(new JFFDeleteAllTasks(TreeTable,items));
        PlayTasks=new JMenuItem(new JFFStartTasks(items));
        PauseTasks=new JMenuItem(new JFFPauseTasks(items));
        
        
        Help=new JMenuItem(new JFFShowTutorial());
        Credits=new JMenuItem(new JFFShowCredits(this.getParent()));
        
        File.add(AddFile);
        File.add(DeleteFile);

        File.addSeparator();
        File.add(PlayVLC);
        File.addSeparator();
        File.add(Quit);
        
        Options.add(ShowOptions);
        Options.add(HideOptions);
        
        
        Task.add(AddTask);
        Task.add(DeleteTask);
        Task.add(ClearTasks);
        Task.addSeparator();
        Task.add(PlayTasks);
        Task.add(PauseTasks);
        
        
        Info.add(Help);
        Info.add(Credits);
	}
	
}
