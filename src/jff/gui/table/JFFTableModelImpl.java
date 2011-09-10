package jff.gui.table;

import java.io.File;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import jff.item.JFFGroupSelectableVideoFile;
import jff.item.VideoFile.ValueNotFoundException;

@SuppressWarnings("serial")
public class JFFTableModelImpl extends AbstractTableModel implements JFFTableModel {
    static private String[] columnNames = {"Sel","Nome","Durata","Formato","PAR","DAR"};
    
    private JFFGroupSelectableVideoFile SelectedFiles;
    
    public JFFTableModelImpl(JFFGroupSelectableVideoFile f){
    	super();
		SelectedFiles=f;
		
		//SelectedFiles.add(new VideoFileS(new File("/home/francesco/Scaricati/input 1.mkv"),true));
		//SelectedFiles.add(new VideoFileS(new VideoFileImpl(new File("/home/francesco/Scaricati/input B2.3gp"),true)));
		//SelectedFiles.add(new VideoFileS(new VideoFileImpl(new File("/home/francesco/Scaricati/input R.mp4"),true)));
    	
    };
    
    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return SelectedFiles.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	
    	try {
    	
    	if (col==0)
    		return SelectedFiles.isSelected(row);
    	
    	if (col==1)
    		return SelectedFiles.get(row).file().getName();
    	
    	if (col==2)
    		return new Integer(SelectedFiles.get(row).Duration()).toString();
    
    	if (col==3)
    		return SelectedFiles.get(row).width()+"x"+SelectedFiles.get(row).height();
    	
    	if (col==4)
    		return SelectedFiles.get(row).PAR();
    	
    	if (col==5)
    		return SelectedFiles.get(row).DAR();
    	
    	} catch (ValueNotFoundException vf){//TODO
    		System.out.println("sdf");
    		}
    	
    	return "???";
    	
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        
    	if (col==0)
    		return true;
    	
    	return false;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        
    	if (SelectedFiles.get(row).isSelected())
    		SelectedFiles.get(row).deselect();
    	else
    		SelectedFiles.get(row).select();
    	// TODO
        fireTableCellUpdated(row, col);
    }
   
}