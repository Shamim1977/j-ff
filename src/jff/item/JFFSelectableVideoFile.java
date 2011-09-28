package jff.item;

import java.io.Serializable;

public interface JFFSelectableVideoFile extends VideoFile, Serializable {

	public boolean isSelected();

	public void select();

	public void deselect();
	
	public void invertSelection();

}
