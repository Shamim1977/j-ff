package jff.gui.cellrenderer;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import jff.task.FFGroupTask;
import jff.task.FFSingleTask;

@SuppressWarnings("serial")
public class JFFProgressBarCellRenderer extends DefaultTableCellRenderer {

	private JProgressBar PB=new JProgressBar();
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object
	value, boolean isSelected,
	boolean hasFocus, int
	row, int column) {
	super.getTableCellRendererComponent(table, value, isSelected,
	hasFocus, row, column);
	
	if (value!=null){
		
		PB.setValue(((Float)value).intValue());
		PB.setStringPainted(true);
	
		return PB;
		
		} else
			return this;
	}
	}