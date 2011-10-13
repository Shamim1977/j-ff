package jff.gui.cellrenderer;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A cell renderer used to represent the progress bar cells
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
@SuppressWarnings("serial")
public class JFFProgressBarCellRenderer extends DefaultTableCellRenderer {

	/**
	 * The progress bar object
	 */
	private JProgressBar PB=new JProgressBar();
	
	/**
	 * If value is not null sets the progressBar value to "value"
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object	value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	
		if (value!=null){
		
			PB.setValue(((Float)value).intValue());
			PB.setStringPainted(true);
			PB.setBorder(BorderFactory.createEmptyBorder());
			return PB;
		
		} else
			return this;
	}
}
