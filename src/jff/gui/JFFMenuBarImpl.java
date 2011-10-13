package jff.gui;

import java.awt.Component;

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
import jff.action.JFFInvertSelection;
import jff.action.JFFPauseTasks;
import jff.action.JFFPlayFile;
import jff.action.JFFQuit;
import jff.action.JFFSelectAll;
import jff.action.JFFSelectNone;
import jff.action.JFFShowCredits;
import jff.action.JFFShowFiles;
import jff.action.JFFShowOptions;
import jff.action.JFFShowTutorial;
import jff.action.JFFStartTasks;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.tabbedpane.JFFTabbedPane;
import jff.gui.table.JFFTable;
import jff.gui.treetable.JFFTreeTable;

/**
 * The implementation of the menu bar of the application.<br />
 * It consists of 5 menus with these menu items:<br />
 * <ul>
 * 
 * <li>
 * File
 * <ul>
 * <li>AddFile</li>
 * <li>DeleteFile</li>
 * <li>ShowFiles</li>
 * <li>HideFiles</li>
 * <li>PlayFile</li>
 * <li>Quit</li>
 * </ul>
 * </li>
 * <li>
 * Select
 * <ul>
 * <li>SelectAll</li>
 * <li>SelectNone</li>
 * <li>InvertSelection</li>
 * </ul>
 * </li>
 * <li>
 * Options
 * <ul>
 * <li>ShowOptions</li>
 * <li>HideOptions</li>
 * </ul>
 * </li>
 * <li>
 * Tasks
 * <ul>
 * <li>AddTask</li>
 * <li>DeleteTask</li>
 * <li>PlayTasks</li>
 * <li>PauseTasks</li>
 * <li>ClearTasks</li>
 * </ul>
 * </li>
 * <li>
 * Info
 * <ul>
 * <li>Help</li>
 * <li>Credits</li>
 * </ul>
 * </li>
 * 
 * </ul>
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
@SuppressWarnings("serial")
public class JFFMenuBarImpl extends JMenuBar implements JFFMenuBar {

	/**
	 * the parent frame
	 */
	private Component Parent;
	
	/**
	 * the files table
	 */
	private JFFTable Table;
	
	/**
	 * the task table
	 */
	private JFFTreeTable TreeTable;
	
	private JMenu File;
	private JMenu Select;
	private JMenu Options;
	private JMenu Task;
	private JMenu Info;
	
	
	private JMenuItem AddFile;
	private JMenuItem DeleteFile;
	private JMenuItem ShowFiles;
	private JMenuItem HideFiles;
	private JMenuItem Quit;
	private JMenuItem SelectAll;
	private JMenuItem SelectNone;
	private JMenuItem InvertSelection;
	private JMenuItem ShowOptions;
	private JMenuItem HideOptions;
	private JMenuItem AddTask;
	private JMenuItem DeleteTask;
	private JMenuItem PlayTasks;
	private JMenuItem PauseTasks;
	private JMenuItem ClearTasks;
	private JMenuItem Help;
	private JMenuItem Credits;
	private JMenuItem PlayFile;
	
	
	/**
	 * Create the menu bar connecting the menu voices to their respective actions
	 * 
	 * @param c the parent frame
	 * @param b the files table
	 * @param p the options tabbed pane
	 * @param tb the tasks table
	 * @param items the application items
	 */
	public JFFMenuBarImpl(Component c, JFFTable b,JFFTabbedPane p, JFFTreeTable tb, JFFBundledItems items){
		super();
		
		Parent=c;
		
		Table=b;
		TreeTable=tb;
		
		File=new JMenu(items.S.fileMenu()); 
		Select=new JMenu(items.S.selectMenu()); 
		Options=new JMenu(items.S.optionMenu());
		Task=new JMenu(items.S.taskMenu());
		Info=new JMenu(items.S.infoMenu()); 
        
        //Build the menus.
		add(File);
		add(Select);
		add(Options);
		add(Task);
		add(Info);
        
        
		AddFile=new JMenuItem(new JFFAddFiles(Parent,Table,items));
		DeleteFile=new JMenuItem(new JFFDeleteFiles(Table,items));
		ShowFiles=new JMenuItem(new JFFShowFiles(Parent,items));
		HideFiles=new JMenuItem(new JFFHideFiles(Parent,items));
		
        Quit=new JMenuItem(new JFFQuit(items));
		PlayFile=new JMenuItem(new JFFPlayFile(Table, items));
        
		SelectAll=new JMenuItem(new JFFSelectAll(Table,items));
        SelectNone=new JMenuItem(new JFFSelectNone(Table,items));
        InvertSelection=new JMenuItem(new JFFInvertSelection(Table,items));
        
		
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
        File.add(ShowFiles);
        File.add(HideFiles);

        File.addSeparator();
        File.add(PlayFile);
        File.addSeparator();
        File.add(Quit);
        
        Select.add(SelectAll);
        Select.add(SelectNone);
        Select.add(InvertSelection);
        
        Options.add(ShowOptions);
        Options.add(HideOptions);
        
        
        Task.add(AddTask);
        Task.addSeparator();
        Task.add(PlayTasks);
        Task.add(PauseTasks);
        Task.addSeparator();
        Task.add(DeleteTask);
        Task.add(ClearTasks);
        
        
        Info.add(Help);
        Info.add(Credits);
	}
	
}
