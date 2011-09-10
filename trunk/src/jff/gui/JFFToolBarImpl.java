package jff.gui;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.Box;
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
import jff.action.JFFQuit;
import jff.action.JFFStartTasks;
import jff.action.JFFVLCPlay;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.JFFMainFrameImpl.RestartableThread;
import jff.gui.table.JFFTable;
import jff.gui.treetable.JFFTreeTable;
import jff.item.FFOptions;
import jff.item.JFFGroupSelectableVideoFile;
import jff.task.FFMultipleGroupTask;

@SuppressWarnings("serial")
public class JFFToolBarImpl extends JToolBar implements JFFToolBar {

	private JFFTable Table;
	private JFFTreeTable TreeTable;
	
	private JButton Add;
	private JButton DeleteFiles;
	private JButton PlayVLC;
	
	private JButton DeleteTask;
	private JButton Play;
	
	private JButton DeleteAllTasks;
	private JButton PauseAllTasks;
	private JButton ResumePlay;
	
	
	public JFFToolBarImpl(JFFTable b, JFFTreeTable tb, JFFBundledItems items){
		super();
		setFloatable(false);
		
		
		Table=b;
		TreeTable=tb;
		
		Add=new JButton(new JFFAddFiles(this.getParent(), Table, items));
		Add.setText(null);
		DeleteFiles=new JButton(new JFFDeleteFiles(Table, items));
		DeleteFiles.setText(null);
		PlayVLC=new JButton(new JFFVLCPlay(Table, items));
		PlayVLC.setText(null);
		
		DeleteTask=new JButton(new JFFDeleteGroupTask(TreeTable,items));
		DeleteTask.setText(null);
		Play=new JButton(new JFFAddTask(Table,TreeTable,items));
		Play.setText(null);
		DeleteAllTasks=new JButton(new JFFDeleteAllTasks(TreeTable, items));
		DeleteAllTasks.setText(null);
		PauseAllTasks=new JButton(new JFFPauseTasks(items));
		PauseAllTasks.setText(null);
		ResumePlay=new JButton(new JFFStartTasks(items));
		ResumePlay.setText(null);
		
		add(Add);
		add(DeleteFiles);
		add(PlayVLC);
		this.addSeparator();
		
		
		add(DeleteTask);
		add(Play);
		this.addSeparator();
		
		add(DeleteAllTasks);
		add(PauseAllTasks);
		add(ResumePlay);
		add(Box.createHorizontalGlue());
		
		
	}
}
