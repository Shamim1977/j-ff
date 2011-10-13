package jff.gui.cellrenderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A cell renderer used to represent the text cells
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
@SuppressWarnings("serial")
public class JFFTextCellRenderer extends DefaultTableCellRenderer {

	/**
	 * Sets the alignment to "SwingConstants.CENTER" and set the tooltip to the same text as value
	 * if the lenght of the text is more than 5 characters
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object	value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	
		setHorizontalAlignment(SwingConstants.CENTER);
	
		if (getText().length()>5)
			setToolTipText(this.getText());
	
		return this;
	}
	
}
