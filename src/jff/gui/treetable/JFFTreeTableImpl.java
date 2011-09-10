package jff.gui.treetable;


import com.sun.java.swing.*;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.cellrenderer.JFFProgressBarCellRenderer;
import jff.gui.cellrenderer.JFFTextCellRenderer;
import jff.gui.cellrenderer.JFFTreeTableCellRenderer;
import jff.gui.treetable.structure.AbstractCellEditor;
import jff.gui.treetable.structure.TreeTableModelAdapter;
import jff.task.FFMultipleGroupTask;

/**
 * This example shows how to create a simple JTreeTable component, 
 * by using a JTree as a renderer (and editor) for the cells in a 
 * particular column in the JTable.  
 *
 * @version %I% %G%
 *
 * @author Philip Milne
 * @author Scott Violet
 */

@SuppressWarnings("serial")
public class JFFTreeTableImpl extends JTable implements JFFTreeTable {
	private JFFTreeTableCellRenderer TreeCellRenderer;
	private JFFProgressBarCellRenderer ProgressBarCellRenderer;
	private JFFTextCellRenderer TextCellRenderer;

    private FFMultipleGroupTask Tasks;
    
    public JFFTreeTableImpl(JFFBundledItems items) {
    	super();
	
    	Tasks=items.Tasks;

    	TextCellRenderer=new JFFTextCellRenderer();
    	ProgressBarCellRenderer=new JFFProgressBarCellRenderer();
	
    	rebuild();
    }

    private void setColW(){
    	
		TableColumn column = null;
		
		
		for (int i = 0; i < this.getColumnCount(); i++) {
		    column = getColumnModel().getColumn(i);
		    if (i == 1) {
		        column.setPreferredWidth(100);
		    } else {
		        column.setPreferredWidth(700);
		    } 
		}
		
		
	}
    
    public void rebuild(){
    	
    	JFFTreeTableModel treeTableModel=new JFFTreeTableModelImpl(Tasks);
    	// Create the tree. It will be used as a renderer and editor. 
    	TreeCellRenderer = new JFFTreeTableCellRenderer(this,treeTableModel); 

    	
    	
    	// Install a tableModel representing the visible rows in the tree. 
    	super.setModel(new TreeTableModelAdapter(treeTableModel, TreeCellRenderer));

    	
    	
    	
    	// Force the JTable and JTree to share their row selection models. 
    	TreeCellRenderer.setSelectionModel(new DefaultTreeSelectionModel() { 
    	    // Extend the implementation of the constructor, as if: 
    	 /* public this() */ {
    		 
    		setSelectionModel(listSelectionModel); 
    	    } 
    	}); 
    	// Make the tree and table row heights the same. 
    	setColW();
    	setRowHeight(25);
    	TreeCellRenderer.setRowHeight(getRowHeight());

    	
    	// Install the tree editor renderer and editor. 
    	setDefaultRenderer(JFFTreeTableModel.class, TreeCellRenderer);
    	setDefaultEditor(JFFTreeTableModel.class, new TreeTableCellEditor());  

    	setShowGrid(false);
    	setIntercellSpacing(new Dimension(0, 0)); 	
    	

    	
    }
    
    /* Workaround for BasicTableUI anomaly. Make sure the UI never tries to 
     * paint the editor. The UI currently uses different techniques to 
     * paint the renderers and editors and overriding setBounds() below 
     * is not the right thing to do for an editor. Returning -1 for the 
     * editing row in this case, ensures the editor is never painted. 
     */
    public int getEditingRow() {
        return (getColumnClass(editingColumn) == JFFTreeTableModel.class) ? -1 : editingRow;  
    }

    
    // 
    // The editor used to interact with tree nodes, a JTree.  
    //

    public class TreeTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	public Component getTableCellEditorComponent(JTable table, Object value,
						     boolean isSelected, int r, int c) {
	    return TreeCellRenderer;
	}
    }

	@Override
	public JTree tree() {
		
		return TreeCellRenderer;
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		
		if (column==2&&row!=0)
			return ProgressBarCellRenderer;
			
		if ((column!=0))
			return TextCellRenderer;
		
		
	
	return super.getCellRenderer(row,column);
	}
}

