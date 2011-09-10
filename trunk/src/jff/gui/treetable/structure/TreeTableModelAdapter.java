package jff.gui.treetable.structure;


import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;

import jff.gui.treetable.JFFTreeTableModel;

/**
 * This is a wrapper class takes a TreeTableModel and implements 
 * the table model interface. The implementation is trivial, with 
 * all of the event dispatching support provided by the superclass: 
 * the AbstractTableModel. 
 *
 * @version %I% %G%
 *
 * @author Philip Milne
 * @author Scott Violet
 */


@SuppressWarnings("serial")
public class TreeTableModelAdapter extends AbstractTableModel
{
    JTree Tree;
    JFFTreeTableModel TreeTableModel;

    public TreeTableModelAdapter(JFFTreeTableModel ttm, JTree t) {
        Tree = t;
        TreeTableModel = ttm;

    
        
	Tree.addTreeExpansionListener(new TreeExpansionListener() {
	    // Don't use fireTableRowsInserted() here; 
	    // the selection model would get  updated twice. 
	    @Override
		public void treeExpanded(TreeExpansionEvent event) {  
	      fireTableDataChanged(); 
	    }
        
	    @Override
	    public void treeCollapsed(TreeExpansionEvent event) {  
	      fireTableDataChanged(); 
	    }
	});
	
	
    }

    // Wrappers, implementing TableModel interface. 

    @Override
    public int getColumnCount() {
    	
    	return TreeTableModel.getColumnCount();
    }

    @Override
    public String getColumnName(int column) {
	
    	return TreeTableModel.getColumnName(column);
    }
    
    @Override
    public Class<?> getColumnClass(int column) {
	
    	return TreeTableModel.getColumnClass(column);
    }

    @Override
    public int getRowCount() {
	
    	return Tree.getRowCount();
    }

    
    protected Object nodeForRow(int row) {
    	TreePath treePath = Tree.getPathForRow(row);
	
    	return treePath.getLastPathComponent();         
    }

    @Override
    public Object getValueAt(int row, int column) {
    	
    	return TreeTableModel.getValueAt(nodeForRow(row), column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {

    	return TreeTableModel.isCellEditable(nodeForRow(row), column); 
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
	
    	TreeTableModel.setValueAt(value, nodeForRow(row), column);
    }

}


