package jff.gui.treetable;


import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.swing.JProgressBar;
import javax.swing.event.TreeModelListener;

import jff.gui.treetable.structure.AbstractTreeTableModel;
import jff.task.FFGroupTask;
import jff.task.FFMultipleGroupTask;
import jff.task.FFSingleTask;

/**
 * FileSystemModel is a TreeTableModel representing a hierarchical file 
 * system. Nodes in the FileSystemModel are FileNodes which, when they 
 * are directory nodes, cache their children to avoid repeatedly querying 
 * the real file system. 
 * 
 * @version %I% %G%
 *
 * @author Philip Milne
 * @author Scott Violet
 */

public class JFFTreeTableModelImpl extends AbstractTreeTableModel implements JFFTreeTableModel {

    // Names of the columns.
    static protected String[]  columnNames = {"Processi", "Files", "Progresso", "Info"};

    // Types of the columns.
    static protected Class[]  cTypes = {JFFTreeTableModel.class, Integer.class, JProgressBar.class, String.class};

    public JFFTreeTableModelImpl(FFMultipleGroupTask t) { 
    	super(t);
    }

    //
    // Some convenience methods. 
    //


    protected Vector<Object> getChildren(Object node) {
	
    	if (node instanceof FFMultipleGroupTask )
    		return new Vector<Object>(((FFMultipleGroupTask)node).groupTasks());
    	
    	if (node instanceof FFGroupTask )
    		return new Vector<Object>(((FFGroupTask)node).tasks());
    	
    	if (node instanceof FFSingleTask ){
    	
    		Vector<Object> obv=new Vector<Object>();
    		obv.add(((FFSingleTask)node).commandLine().input());
    		return obv;
    	}
    	
		return new Vector<Object>();
	 
    }

    //
    // The TreeModel interface
    //

    @Override
    public int getChildCount(Object node) { 
	 
    	return getChildren(node).size();
    }

    @Override
    public Object getChild(Object node, int i) { 
	
    	return getChildren(node).get(i); 
    }


    //
    //  The TreeTableNode interface. 
    //

    @Override
    public int getColumnCount() {

    	return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
	
    	return columnNames[column];
    }

    @Override
    public Class getColumnClass(int column) {
	
    	return cTypes[column];//TODO
    }
 
    @Override
    public Object getValueAt(Object node, int column) {
	
    	if (node instanceof FFMultipleGroupTask ){
    	
    		if (column==1)
    	   		return ((FFMultipleGroupTask)node).sizeInFiles();
    		else if (column==2)
    	   		return ((FFMultipleGroupTask)node).runningTasks()+" in esecuzione, "+
    	   			   ((FFMultipleGroupTask)node).pausedTasks()+" in pausa";
    		else if (column==3)
    	   		return null;//info
    	}
    	
    	
    	
    	if (node instanceof FFGroupTask ){
    	
    		if (column==1)
    	   		return ((FFGroupTask)node).size();
    		else if (column==2)
    	   		return ((FFGroupTask)node).progressPercent();
    		else if (column==3)
    	   		return null;//info
    	}
    	
    	
    	if (node instanceof FFSingleTask ){
    	
    		if (column==1)
    	   		return ((FFSingleTask)node).isDone()?"ok":null;
    		else if (column==2)
    	   		return ((FFSingleTask)node).progressPercentOfTheTask();
    		else if (column==3)
    	   		return null;//info
    	}
    	
    	
	   	return null;
    }
    
    
    	
}
