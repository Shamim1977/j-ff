package jff.item;

import java.io.Serializable;
import java.util.Vector;

public interface JFFGroupSelectableVideoFile extends Serializable {

	public void add(JFFSelectableVideoFile t);
	public JFFSelectableVideoFile get(int i);
	public void remove(int i);
	public boolean isEmpty();
	public int size();
	
	
	public Vector <VideoFile> getSelected();
	public Vector <VideoFile> getUnselected();
	
	public void removeSelected();
	public void removeUnselected();
	
	
	public void select(int i);
	public void deselect(int i);
	public boolean isSelected(int i);
	
	public void selectAll();
	public void deselectAll();
	public void invertSelection();
	
	

}
