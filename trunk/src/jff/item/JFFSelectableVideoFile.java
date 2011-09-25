package jff.item;

public interface JFFSelectableVideoFile extends VideoFile {

	public boolean isSelected();

	public void select();

	public void deselect();
	
	public void invertSelection();

}
