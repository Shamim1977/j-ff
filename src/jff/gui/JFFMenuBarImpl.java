package jff.gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jff.action.JFFAddFiles;
import jff.action.JFFAddTask;
import jff.action.JFFDeleteAllTasks;
import jff.action.JFFDeleteFiles;
import jff.action.JFFDeleteGroupTask;
import jff.action.JFFHideFiles;
import jff.action.JFFHideOptions;
import jff.action.JFFPauseTasks;
import jff.action.JFFPlayFile;
import jff.action.JFFQuit;
import jff.action.JFFShowCredits;
import jff.action.JFFShowFiles;
import jff.action.JFFShowOptions;
import jff.action.JFFShowTutorial;
import jff.action.JFFStartTasks;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.tabbedpane.JFFTabbedPane;
import jff.gui.table.JFFTable;
import jff.gui.treetable.JFFTreeTable;

@SuppressWarnings("serial")
public class JFFMenuBarImpl extends JMenuBar implements JFFMenuBar {

	private Component Parent;
	
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
	
	
	public JFFMenuBarImpl(Component c, JFFTable b,JFFTabbedPane p, JFFTreeTable tb, JFFBundledItems items){
		super();
		
		Parent=c;
		
		Table=b;
		TreeTable=tb;
		TabbedPane=p;
		
		File=new JMenu(items.S.fileMenu()); 
		Options=new JMenu(items.S.optionMenu());
		Task=new JMenu(items.S.taskMenu());
		Info=new JMenu(items.S.infoMenu()); 
        
        //Build the menus.
		add(File);
		add(Options);
		add(Task);
		add(Info);
        
        
		AddFile=new JMenuItem(new JFFAddFiles(Parent,Table,items));
		DeleteFile=new JMenuItem(new JFFDeleteFiles(Table,items));
		ShowFiles=new JMenuItem(new JFFShowFiles(Parent,items));
		HideFiles=new JMenuItem(new JFFHideFiles(Parent,items));
		
        Quit=new JMenuItem(new JFFQuit(items));
		PlayVLC=new JMenuItem(new JFFPlayFile(Table, items));
        
		ShowOptions=new JMenuItem(new JFFShowOptions(Parent,items));
        HideOptions=new JMenuItem(new JFFHideOptions(Parent,items));
        AddTask=new JMenuItem(new JFFAddTask(Table,TreeTable,items));
        DeleteTask=new JMenuItem(new JFFDeleteGroupTask(TreeTable,items));
        ClearTasks=new JMenuItem(new JFFDeleteAllTasks(TreeTable,items));
        PlayTasks=new JMenuItem(new JFFStartTasks(items));
        PauseTasks=new JMenuItem(new JFFPauseTasks(items));
        
        
        Help=new JMenuItem(new JFFShowTutorial(items));
        Credits=new JMenuItem(new JFFShowCredits(this.getParent(),items));
        
        File.add(AddFile);
        File.add(DeleteFile);

        File.addSeparator();
        File.add(HideFiles);
        File.add(ShowFiles);

        File.addSeparator();
        File.add(PlayVLC);
        File.addSeparator();
        File.add(Quit);
        
        Options.add(ShowOptions);
        Options.add(HideOptions);
        
        
        Task.add(AddTask);
        Task.add(DeleteTask);
        Task.addSeparator();
        Task.add(PlayTasks);
        Task.add(PauseTasks);
        Task.add(ClearTasks);
        
        
        Info.add(Help);
        Info.add(Credits);
	}
	
}
