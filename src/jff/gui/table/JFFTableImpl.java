package jff.gui.table;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.cellrenderer.JFFTextCellRenderer;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFGroupSelectableVideoFileImpl;


@SuppressWarnings("serial")
public class JFFTableImpl extends JTable implements JFFTable {

	private JFFTextCellRenderer Renderer;

	private JFFGroupSelectableVideoFile Files;
	
	public JFFTableImpl(JFFBundledItems items){
		super();
	
		Renderer=new JFFTextCellRenderer();
		Files=items.Files;
		
		setColW();
		
	}
	
	
	private void setColW(){
	
		TableColumn column = null;
		
		
		for (int i = 0; i < this.getColumnCount(); i++) {
		    column = getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(100);
		    } else if (i == 1) {
		        column.setPreferredWidth(1000);
		    } else {
		        column.setPreferredWidth(200);
		    } 
		}
		
		
	}
	
	public void rebuild(){
		
		setModel(new JFFTableModelImpl(Files));
		setColW();
		
		
	}
	
	@Override
	public TableModel createDefaultDataModel(){
		
		return new JFFTableModelImpl(new JFFGroupSelectableVideoFileImpl());
	}
	
	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		
		if ((column!=1)&&(column!=0))
			return Renderer;
		
	
	return super.getCellRenderer(row,column);
	}


	
}
