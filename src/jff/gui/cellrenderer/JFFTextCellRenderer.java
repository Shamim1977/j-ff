package jff.gui.cellrenderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class JFFTextCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object
	value, boolean isSelected,
	boolean hasFocus, int
	row, int column) {
	super.getTableCellRendererComponent(table, value, isSelected,
	hasFocus, row, column);
	setHorizontalAlignment(SwingConstants.CENTER);
	if (this.getText().length()>5)
		this.setToolTipText(this.getText());
	return this;
	}
	}