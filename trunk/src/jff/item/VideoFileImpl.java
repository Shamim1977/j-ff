package jff.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;




public class VideoFileImpl implements VideoFile {

	private enum OS {LINUX, WINDOWS};
	private OS CurrentOS=null;
	
	
	private File Path;
	
	private int Width;
	private int Height;
	private boolean GotWidthAndHeight=false;
	
	private String PAR;
	private String DAR;
	private boolean GotPARAndDAR=false;
	
	private int Duration;
	private boolean GotDuration=false;
	
	
	public VideoFileImpl(File f){
		
		Path=f;
		
		String s=System.getProperty( "os.name" );
		
		if (s.matches("(.*)(?i)linux(.*)")){
			CurrentOS=OS.LINUX;
		}
		
		if (s.matches("(.*)(?i)windows(.*)")){
			System.out.println("windows!!!");
			CurrentOS=OS.WINDOWS;
		}
			
		if (CurrentOS==null){
			//throw an exception TODO
		}
		
		checkData();
	}
	
	

	
	@Override
	public void checkData() {
		
		try {
			
			String s=null;
			
			if (CurrentOS==OS.LINUX)
				s="ffmpeg";
			
			if (CurrentOS==OS.WINDOWS)
				s="ffmpeg.exe";
			
			
			ProcessBuilder pb = new ProcessBuilder(s,"-i",Path.getAbsolutePath());
			pb.redirectErrorStream(true);
		    
			Process p=pb.start();
		    
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));  

		    String line =in.readLine();  
		            
		    while (line!=null ) { 
		        
		    	checkWidthHeightPARDAR(line);
		    	checkDuration(line);
		                        
		    	line = in.readLine();
		    } 
		            
		    		            
		} catch (IOException e) {  
			e.printStackTrace();  
		}  	
				
	}
	
	
	

	@Override
	public String toString() {
		return "["+Duration+" , "+Width+"x"+Height+" , " + PAR + " , " + DAR+"]";
	}
	
	@Override
	public String detailedToString() {
		return "VideoFileImpl [" + System.getProperty("line.separator") +
				"Path=" + Path + System.getProperty("line.separator") +
				"Width=" + Width + System.getProperty("line.separator") +
				"Height=" + Height + System.getProperty("line.separator") +
				"PAR=" + PAR + System.getProperty("line.separator") +
				"DAR=" + DAR + System.getProperty("line.separator") +
				"Duration=" + Duration + System.getProperty("line.separator") +
				"]";
	}

	private void checkDuration(String line){
		
		if (line.contains("Duration:")){// line where width and height are stored
        	
        	String hMS=line.split(",")[0].trim().split(" ")[1]; //taglio nel formato h:m:s
        	
        	String[] hoursMinsSecs=hMS.split(":");
        	Duration=Integer.parseInt(hoursMinsSecs[0])*3600+
        	Integer.parseInt(hoursMinsSecs[1])*60+
        	((int)Float.parseFloat(hoursMinsSecs[2]));
        	
			GotDuration=true;
				
        }
        
	}
	
	private void checkWidthHeightPARDAR(String line){
		
        if (line.contains("Video:")){// line where width and height are stored
        	
        	String[] videoParams=line.split(",");
        	
        	for (int i=0;i<videoParams.length;i++){
        		
        		if (videoParams[i].matches("\\s(\\d)*x(\\d)*(.)*")){//spazio+numero+carattere x+numero+qualsiasi altra cosa

        			/*
        			2 formati possibili:
        			
        			 spazio+numero+x+numero
        			 
        			 spazio+numero+x+numero+spazio+[PAR+spazio+numero:numero+spazio+DAR+spazio+numero:numero+]
        			 
        			*/
        			
        			String [] tokens=videoParams[i].split(" ");
        			
        			String [] widthAndHeight=tokens[1].split("x");//prendo la prima sequenza compresa tra 2 spazi (nxn) e la divido per x
        			
        			
        			Width=Integer.parseInt(widthAndHeight[0]);
        			Height=Integer.parseInt(widthAndHeight[1]);
        			
        			                			                			
        			GotWidthAndHeight=true;
                	
        			
        			if (tokens.length>2){// se siamo nel caso del secondo formato prendiamo anche par e dar
        			
        				PAR=tokens[3];
        				DAR=tokens[5].split("]")[0];// tolgo la parentesi quadra alla fine
        			
            			GotPARAndDAR=true;
        	
                    	
        			}
        		
        		}               		
        	}                	
        	
        }
		
	}

	@Override
	public File file() {
		
		return Path;
	}
	
	
	@Override
	public String DAR() throws ValueNotFoundException {
		if (GotPARAndDAR)
			return DAR;
		else
			throw new ValueNotFoundException();
	}

	@Override
	public int Duration() throws ValueNotFoundException {
		if (GotDuration)
			return Duration;
		else
			throw new ValueNotFoundException();
	}

	@Override
	public int height() throws ValueNotFoundException {
		if (GotWidthAndHeight)
			return Height;
		else
			throw new ValueNotFoundException();
	}

	@Override
	public String PAR() throws ValueNotFoundException {
		if (GotPARAndDAR)
			return PAR;
		else
			throw new ValueNotFoundException();
	}

	@Override
	public int width() throws ValueNotFoundException {
		if (GotWidthAndHeight)
			return Width;
		else
			throw new ValueNotFoundException();
	}
}
