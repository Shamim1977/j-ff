package jff.item;

import java.util.Vector;

public class JFFGroupSelectableVideoFileImpl implements JFFGroupSelectableVideoFile {

	private Vector<JFFSelectableVideoFile> Files;
	

	public JFFGroupSelectableVideoFileImpl(){
		Files=new Vector<JFFSelectableVideoFile>();
	}

	@Override
	public void add(JFFSelectableVideoFile t) {
		
		Files.add(t);
	}

	@Override
	public JFFSelectableVideoFile get(int i) {
		
		return Files.get(i);
	}

	@Override
	public boolean isEmpty() {
		
		return Files.isEmpty();
	}

	
	@Override
	public int size() {
		
		return Files.size();
	}

	@Override
	public void deselect(int i) {
		
		Files.get(i).deselect();
		
	}

	@Override
	public boolean isSelected(int i) {
		
		return Files.get(i).isSelected();
	}

	@Override
	public void select(int i) {
		
		Files.get(i).select();
	}

	@Override
	public Vector<VideoFile> getSelected() {
		
		Vector<VideoFile> tmp=new Vector<VideoFile>();
		
		for (int i=0;i<Files.size();i++)
			if (Files.get(i).isSelected())
				tmp.add(Files.get(i));
		
		return tmp;
	}

	@Override
	public Vector<VideoFile> getUnselected() {

		Vector<VideoFile> tmp=new Vector<VideoFile>();
		
			for (int i=0;i<Files.size();i++)
				if (!Files.get(i).isSelected())
					tmp.add(Files.get(i));
		
			return tmp;
	}

	@Override
	public void remove(int i) {
		Files.remove(i);
		
	}

	@Override
	public void removeSelected() {
		
		for (int i=0;i<Files.size();i++)
			if (Files.get(i).isSelected()){
				Files.remove(i);
				i--;
			}
	}

	@Override
	public void removeUnselected() {
		
		for (int i=0;i<Files.size();i++)
			if (!Files.get(i).isSelected()){
				Files.remove(i);
				i--;
			}
		
	}
	
}
