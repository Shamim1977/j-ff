package jff.item;

import java.io.File;

public class JFFSelectableVideoFileImpl extends VideoFileImpl implements JFFSelectableVideoFile {
	
    private boolean Checked=true;
    	
    public JFFSelectableVideoFileImpl(File f, boolean isVerbose){
    	super(f,isVerbose);
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
    
}
