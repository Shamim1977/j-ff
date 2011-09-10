package jff.gui.cellrenderer;

import java.awt.Component;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;

import jff.gui.treetable.JFFTreeTable;
import jff.gui.treetable.JFFTreeTableImpl;
import jff.task.FFGroupTask;
import jff.task.FFMultipleGroupTask;
import jff.task.FFSingleTask;

//
// The renderer used to display the tree nodes, a JTree.  
//

@SuppressWarnings("serial")
public class JFFTreeTableCellRenderer extends JTree implements TableCellRenderer {

	protected int visibleRow;
	private JFFTreeTable TreeTable;

	public JFFTreeTableCellRenderer(JFFTreeTable tt, TreeModel model) { 
		super(model);
    
		((DefaultTreeCellRenderer)this.getCellRenderer()).setLeafIcon(new ImageIcon("img/leaf.png"));
		((DefaultTreeCellRenderer)this.getCellRenderer()).setOpenIcon(new ImageIcon("img/task.png"));
		((DefaultTreeCellRenderer)this.getCellRenderer()).setClosedIcon(new ImageIcon("img/task.png"));

		TreeTable=tt;
	}


	public void setBounds(int x, int y, int w, int h) {
	
		super.setBounds(x, 0, w, ((JFFTreeTableImpl) TreeTable).getHeight());
	}

	public void paint(Graphics g) {
	
		g.translate(0, -visibleRow * getRowHeight());
		super.paint(g);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		if(isSelected)
			setBackground(table.getSelectionBackground());
		else
			setBackground(table.getBackground());
   
		visibleRow = row;
		return this;
	}

}
