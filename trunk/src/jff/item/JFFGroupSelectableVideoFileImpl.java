package jff.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import jff.task.FFMultipleGroupTaskImpl;
import jff.utility.JFFParser;
import jff.utility.JFFParserImpl;

public class JFFGroupSelectableVideoFileImpl implements JFFGroupSelectableVideoFile {

	private Vector<JFFSelectableVideoFile> Files;
	

	public JFFGroupSelectableVideoFileImpl(){
		Files=new Vector<JFFSelectableVideoFile>();
	}

	public JFFGroupSelectableVideoFileImpl(BufferedReader b) {
		
		Files=new Vector<JFFSelectableVideoFile>();
		
		JFFParser p;
		boolean loadFile=false;
		
		do{
		
		try {
			p=new JFFParserImpl(b.readLine());
		} catch (IOException e) {
			
			p=new JFFParserImpl(null);
			e.printStackTrace();
		}
		
		if (p.find("videofiles"))
			loadFile=p.getBoolean();
		
		} while (!p.isEmpty());
		
		if (loadFile)
			try {
				
				FileInputStream fis = new FileInputStream(new File("videofiles"));
				ObjectInputStream in = new ObjectInputStream(fis);
				
				JFFGroupSelectableVideoFileImpl tmp=((JFFGroupSelectableVideoFileImpl)in.readObject());
				
				for (int i=0;i<tmp.size();i++)
					if (tmp.get(i).file().isFile()){
						//trusting old parameters it should be Files.add(tmp.get(i));
						Files.add(new JFFSelectableVideoFileImpl(tmp.get(i).file()));
					}
					
				
				
				in.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
			catch(ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
		
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

	@Override
	public void selectAll() {
		
		for (int i=0;i<Files.size();i++)
				Files.get(i).select();
			
	}

	@Override
	public void deselectAll() {
		
		for (int i=0;i<Files.size();i++)
			Files.get(i).deselect();
	
	}

	@Override
	public void invertSelection() {
		
		for (int i=0;i<Files.size();i++)
			Files.get(i).invertSelection();
	
	}
	
}
