package jff.gui.treetable;


import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.swing.JProgressBar;
import javax.swing.event.TreeModelListener;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.treetable.structure.AbstractTreeTableModel;
import jff.item.FFCommandLine;
import jff.task.FFGroupTask;
import jff.task.FFMultipleGroupTask;
import jff.task.FFSingleTask;
import jff.translation.JFFStrings;

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


    private JFFStrings S;
    
    // Types of the columns.
    static protected Class<?>[]  cTypes = {JFFTreeTableModel.class, Integer.class, JProgressBar.class, String.class};

    public JFFTreeTableModelImpl(JFFBundledItems items) { 
    	super(items.Tasks);
    	
    	S=items.S;
    }

    //
    // Some convenience methods. 
    //


    protected Vector<Object> getChildren(Object node) {
	
    	if (node instanceof FFMultipleGroupTask )
    		return new Vector<Object>(((FFMultipleGroupTask)node).groupTasks());
    	
    	if (node instanceof FFGroupTask )
    		return new Vector<Object>(((FFGroupTask)node).tasks());
    	
    	//if (node instanceof FFSingleTask ){
    	
    		//Vector<Object> obv=new Vector<Object>();
    		//obv.add(((FFSingleTask)node).commandLine());
    		//return obv;
    	//}
    	
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

    	return 4;
    }
    
    @Override
    public String getColumnName(int col) {
	
    	if (S!=null)
    		switch (col){
    		
    		case 0: return S.treeTableProcesses();
    		case 1: return S.treeTableFiles();
    		case 2: return S.treeTableProgress();
    		case 3: return S.treeTableInfo();
    	
    		default: return "???";
    		
    		}
    	
    	return "???";
    }

    @Override
    public Class<?> getColumnClass(int column) {
	
    	return cTypes[column];//TODO
    }
 
    @Override
    public Object getValueAt(Object node, int column) {
	
    	if (node instanceof FFMultipleGroupTask ){
    	
    		if (column==1)
    	   		return ((FFMultipleGroupTask)node).sizeInFiles();
    		else if (column==2)
    	   		return ((FFMultipleGroupTask)node).doneTasks()+" "+S.ok()+", "+
    	   			((FFMultipleGroupTask)node).runningTasks()+" "+S.executing()+", "+
	   			   ((FFMultipleGroupTask)node).waitingTasks()+" "+S.inPause();
    		else if (column==3)
    	   		return null;//info
    	}
    	
    	
    	
    	else if (node instanceof FFGroupTask ){
    	
    		if (column==1)
    	   		return ((FFGroupTask)node).size();
    		else if (column==2)
    	   		return ((FFGroupTask)node).progressPercent();
    		else if (column==3)
    	   		return ((FFGroupTask)node).doneTasks()+" "+S.ok()+", "+
    	   				((FFGroupTask)node).runningTasks()+" "+S.executing()+", "+
	   			   ((FFGroupTask)node).waitingTasks()+" "+S.inPause();
    	}
    	
    	
    	else if (node instanceof FFSingleTask ){
    	
    		if (column==1)
    	   		return ((FFSingleTask)node).isDone()?S.ok():null;
    		else if (column==2)
    	   		return ((FFSingleTask)node).progressPercentOfTheTask();
    		else if (column==3){
    			
    	   		String tmp=(((FFSingleTask)node).commandLine().options().twoPasses()?S.optionsTwoPassesTag()+", ":"") +
    	   				(((FFSingleTask)node).commandLine().options().pads()?S.optionsPadsTag()+", ":"") +
    	   				(((FFSingleTask)node).commandLine().options().smallFiles()?S.optionsSmallTag()+", ":"") +
    	   				(((FFSingleTask)node).commandLine().options().verbose()?S.optionsDebugTag()+", ":"");
    	   		
    		if (tmp.equals(""))
    			return "["+((FFSingleTask)node).commandLine().getOptimizedDimension()+"]";
    		else
    			return tmp.substring(0,tmp.length()-2)+" "+((FFSingleTask)node).commandLine();
    		
    		}
 
    	}
    	
    	//else if (node instanceof FFCommandLine ) {
    		
    		//if (column==3)
    	   		//return "["+((FFCommandLine)node).getOptimizedDimension()+"]";
    		
    		
    		
    	//}
    	
	   	return null;
    }
    
    
    	
}
