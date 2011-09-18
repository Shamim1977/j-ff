package jff.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class JFFGroupSelectableVideoFileImpl implements JFFGroupSelectableVideoFile {

	private Vector<JFFSelectableVideoFile> Files;
	

	public JFFGroupSelectableVideoFileImpl(){
		Files=new Vector<JFFSelectableVideoFile>();
	}

	public JFFGroupSelectableVideoFileImpl(BufferedReader b) {
		
		Files=new Vector<JFFSelectableVideoFile>();
		
		String line=null;
		
		do{
		
		try {
			line=b.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (line!=null&&line.split(":")[0].equals("file")){
			
			File f=new File(line.split(":")[1].trim());
			
			if (f.isFile())
				Files.add(new JFFSelectableVideoFileImpl(f,true));
		}
		
		} while (line!=null);
		

		
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
