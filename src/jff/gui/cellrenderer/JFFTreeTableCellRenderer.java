package jff.gui.cellrenderer;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;

import jff.gui.treetable.JFFTreeTable;


/**
 * 
 * A cell renderer used to represent the tree cells
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
@SuppressWarnings("serial")
public class JFFTreeTableCellRenderer extends JTree implements TableCellRenderer {

	
	/**
	 * the row to be displayed
	 */
	protected int VisibleRow;
	
	/**
	 * the table to display
	 */
	private JFFTreeTable TreeTable;

	/**
	 * Constructs the cell renderer with the icons IMAGEPATH/leaf.png e IMAGEPATH/task.png 
	 * 
	 * @param tt the table with the tree
	 * @param model the model of the tree
	 */
	public JFFTreeTableCellRenderer(JFFTreeTable tt, TreeModel model) { 
		super(model);
    
		((DefaultTreeCellRenderer)this.getCellRenderer()).setLeafIcon(new ImageIcon("img/leaf.png"));
		((DefaultTreeCellRenderer)this.getCellRenderer()).setOpenIcon(new ImageIcon("img/task.png"));
		((DefaultTreeCellRenderer)this.getCellRenderer()).setClosedIcon(new ImageIcon("img/task.png"));

		TreeTable=tt;
	}


	@Override
	public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x,0,w,TreeTable.getHeight());
	}

	@Override
	public void paint(Graphics g) {
	
		g.translate(0,-VisibleRow*getRowHeight());
		super.paint(g);
	}

	/**
	 * sets the cell background color to the same color as the table line
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		if (isSelected)
			setBackground(table.getSelectionBackground());
		else
			setBackground(table.getBackground());
   
		VisibleRow=row;
		return this;
	}

}
