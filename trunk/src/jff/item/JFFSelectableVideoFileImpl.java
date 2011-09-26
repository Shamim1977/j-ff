package jff.item;

import java.io.File;

public class JFFSelectableVideoFileImpl extends VideoFileImpl implements JFFSelectableVideoFile {
	
    private boolean Checked=true;
    	
    public JFFSelectableVideoFileImpl(File f){
    	super(f);
    }
    
    @Override
    public boolean isSelected(){
    	return Checked;
    }
    
    @Override
    public void select(){
    	Checked=true;
    }
    
    @Override
    public void deselect(){
    	Checked=false;
    }

	@Override
	public void invertSelection() {		
		Checked=!Checked;
	}
    
}
