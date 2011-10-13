package jff.gui;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import jff.action.JFFAddFiles;
import jff.action.JFFAddTask;
import jff.action.JFFChangeOutputPath;
import jff.action.JFFDeleteAllTasks;
import jff.action.JFFDeleteFiles;
import jff.action.JFFDeleteGroupTask;
import jff.action.JFFPauseTasks;
import jff.action.JFFPlayFile;
import jff.action.JFFQuit;
import jff.action.JFFStartTasks;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.JFFMainFrameImpl.RestartableThread;
import jff.gui.table.JFFTable;
import jff.gui.treetable.JFFTreeTable;
import jff.item.FFOptions;
import jff.item.JFFGroupSelectableVideoFile;
import jff.task.FFMultipleGroupTask;

/**
 * The implementation of the tool bar of the application.<br />
 * It consists of these buttons:<br />
 * 
 * <ul>
 * <li>Add</li>
 * <li>DeleteFiles</li>
 * <li>PlayFile</li>
 * <li>DeleteTask</li>
 * <li>Play</li>
 * <li>DeleteAllTasks</li>
 * <li>PauseAllTasks</li>
 * <li>ResumePlay</li>
 * </ul>
 * 
 *  
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
@SuppressWarnings("serial")
public class JFFToolBarImpl extends JToolBar implements JFFToolBar {

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
	
	private JButton Add;
	private JButton DeleteFiles;
	private JButton PlayFile;
	
	private JButton DeleteTask;
	private JButton Play;
	
	private JButton DeleteAllTasks;
	private JButton PauseAllTasks;
	private JButton ResumePlay;
	
	
	/**
	 * Create the tool bar connecting the menu voices to their respective actions and setting the bigger image icon
	 * 
	 * @param c the parent frame
	 * @param b the files table
	 * @param tb the tasks table
	 * @param items the application items
	 */
	public JFFToolBarImpl(Component c, JFFTable b, JFFTreeTable tb, JFFBundledItems items){
		super();
		setFloatable(false);
		
		Parent=c;
		
		Table=b;
		TreeTable=tb;
		
		Add=new JButton(new JFFAddFiles(Parent, Table, items));
		Add.setText(null);
		Add.setIcon(new ImageIcon("img/addbig.png"));
		DeleteFiles=new JButton(new JFFDeleteFiles(Table, items));
		DeleteFiles.setText(null);
		DeleteFiles.setIcon(new ImageIcon("img/deletebig.png"));
		PlayFile=new JButton(new JFFPlayFile(Table, items));
		PlayFile.setText(null);
		PlayFile.setIcon(new ImageIcon("img/playfilebig.png"));
		
		DeleteTask=new JButton(new JFFDeleteGroupTask(TreeTable,items));
		DeleteTask.setText(null);
		DeleteTask.setIcon(new ImageIcon("img/deletetaskbig.png"));
		Play=new JButton(new JFFAddTask(Table,TreeTable,items));
		Play.setText(null);
		Play.setIcon(new ImageIcon("img/addtaskbig.png"));
		DeleteAllTasks=new JButton(new JFFDeleteAllTasks(TreeTable, items));
		DeleteAllTasks.setText(null);
		DeleteAllTasks.setIcon(new ImageIcon("img/deletetasksbig.png"));
		PauseAllTasks=new JButton(new JFFPauseTasks(items));
		PauseAllTasks.setText(null);
		PauseAllTasks.setIcon(new ImageIcon("img/pausetasksbig.png"));
		ResumePlay=new JButton(new JFFStartTasks(items));
		ResumePlay.setText(null);
		ResumePlay.setIcon(new ImageIcon("img/playtasksbig.png"));
		
		add(Add);
		add(DeleteFiles);
		add(PlayFile);
		this.addSeparator();
		
		
		add(Play);
		this.addSeparator();
		
		add(DeleteTask);
		add(DeleteAllTasks);
		this.addSeparator();
		
		add(PauseAllTasks);
		add(ResumePlay);
		add(Box.createHorizontalGlue());
		
		
	}
}
