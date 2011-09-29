package jff.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jff.item.FFCommandLine;
import jff.item.VideoFile.ValueNotFoundException;
import jff.utility.JFFTime;

public class FFSingleTaskImpl implements FFSingleTask, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FFCommandLine CommandLine;
	
	private transient boolean Running=false;
	private boolean Converted=false;	
	private transient int CurrentPass=1;
	private float ConvertedTime=0;

	private boolean Verbose=false;

	private transient BufferedWriter DebugFile=null;
	
	public FFSingleTaskImpl(FFCommandLine c){
		
		CommandLine=c;
		
	}

	
	@Override
	public String toString() {
		return CommandLine.input().file().getName();//+" "+CommandLine;
	}


	@Override
	public float progressPercentOfTheFirstPass() {
		
		if(Converted)//already finished
			return 100;
		
		
		if (!Converted&&!Running)//waiting for start the task
			return 0;

		if (Running&&CommandLine.options().twoPasses()&&CurrentPass==2)//first pass finished and second pass in course
			return 100;
		
		
		if (Running&&CurrentPass==1)
			try {
			
				return ConvertedTime/CommandLine.input().Duration()*100;
			} catch (ValueNotFoundException ve){
			
				return 0; // if the duration of the input file is unknown it will return 0 (awful solution) 
			}
			
		return 0;//it hopefully never happens
		
	}

	@Override
	public float progressPercentOfTheSecondPass() {
		
		if(Converted)//already finished
			return 100;
		
		
		if (!Converted&&!Running)//waiting for start the task
			return 0;

		if (Running&&CurrentPass==1)//first pass started but already in course
			return 0;
		
		if (Running&&CommandLine.options().twoPasses()&&(CurrentPass==2))//second pass time
			try {
		
				return ConvertedTime/CommandLine.input().Duration()*100;
			} catch (ValueNotFoundException ve){
		
				return 0; // if the duration of the input file is unknown it will return 0 (awful solution) 
			}
		
		return 0;//it hopefully never happens
			
	}
	
	
	@Override
	public float progressPercentOfTheTask() {
		
		if (CommandLine.options().twoPasses())
			return (progressPercentOfTheFirstPass()+progressPercentOfTheSecondPass())/2;
		else
			return progressPercentOfTheFirstPass();
	}

	private void updateStats(String l) {
		
		String parsedL="0.0";
		
		for (int i=0;i<l.split(" ").length;i++)
			if(l.split(" ")[i].contains("time="))
				parsedL=l.split(" ")[i].split("=")[1];
		
		if (parsedL.matches("(\\d)*:(\\d)*:(\\d)*.(\\d)*")) {//  nn:nn:nn.nn   nn== a number
			
			String[] hoursMinsSecs=parsedL.split(":");
    	
			ConvertedTime=Integer.parseInt(hoursMinsSecs[0])*3600+
    				  Integer.parseInt(hoursMinsSecs[1])*60+
    				  Float.parseFloat(hoursMinsSecs[2]);
		
		}
	}
	
	
	@Override
	public void run() {
		/*
		Execute the command line for the conversion
		*/
		
		if (!Running&&!Converted&&!Thread.currentThread().isInterrupted()){
		
			Running=true;
			
			if (Verbose) try {//task debug
				
				DebugFile.write(T+"** File "+CommandLine.input().file().getAbsolutePath()+" **");
				DebugFile.newLine();
				DebugFile.write(T+"[.....]");
				DebugFile.newLine();

				DebugFile.write(T+"Started at "+JFFTime.now());
				DebugFile.newLine();
			  
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			BufferedWriter out=null;
			
			if (CommandLine.options().verbose()) try {// conversion debug
			
				FileWriter fstream = new FileWriter(CommandLine.output()+".txt");
				out= new BufferedWriter(fstream);
				out.write(CommandLine.detailedToString());
				out.newLine();
				out.newLine();
				out.write("FFmpeg started at "+JFFTime.now());
				out.newLine();
				out.write("FFmpeg output:");
				out.newLine();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		try {
			
			ProcessBuilder pb = new ProcessBuilder(CommandLine.getCommandLine(1));
			pb.redirectErrorStream(true);
		    
			CurrentPass=1;
		    ConvertedTime=0;
			Process p=pb.start();
		    
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));  

		    String line =in.readLine();

					            
		    while (line!=null&&!Thread.currentThread().isInterrupted() ) { 
		    	
		    	if (CommandLine.options().verbose()) try {// conversion debug
					
					out.write(line);
					out.newLine();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	
		    	updateStats(line);		                        
		    	line = in.readLine();
		    }

		    if (Thread.currentThread().isInterrupted()){

		    	p.destroy();
		    	updateStats("time=00:00:00.00");

		    }
			
		
		    
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		
		
		
		if (CommandLine.options().twoPasses()&&!Thread.currentThread().isInterrupted())
			try {
				
				if (CommandLine.options().verbose()) try {// conversion debug
					
					out.newLine();
					out.write("FFmpeg second pass started at "+JFFTime.now());
					out.newLine();
					out.write("FFmpeg output:");
					out.newLine();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				ProcessBuilder pb = new ProcessBuilder(CommandLine.getCommandLine(2));
				pb.redirectErrorStream(true);
			    
				CurrentPass=2;
			    ConvertedTime=0;
			    
				Process p=pb.start();
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));  

			    String line =in.readLine();
			            

			    while (line!=null&&!Thread.currentThread().isInterrupted() ) { 
			           
			    	if (CommandLine.options().verbose()) try {// conversion debug
						
						out.write(line);
						out.newLine();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
			    	
			    	updateStats(line);		                        
			    	line = in.readLine();
			    }
			
			    if (Thread.currentThread().isInterrupted()){
			    
			    	p.destroy();
			    	updateStats("time=00:00:00.00");
			  
			    }
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
			if (!Thread.currentThread().isInterrupted())
				Converted=true;
			else {
				
				if (Verbose) try {
					
					DebugFile.write(T+"Interrupted at "+JFFTime.now());
				    DebugFile.newLine();
				    DebugFile.newLine();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				

		    	if (CommandLine.options().verbose()) try {// conversion debug
					
		    		out.newLine();
					out.write("Interrupted at "+JFFTime.now());
					out.newLine();
					out.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	
		    	
				
			}
			
			
			Running=false;
			
			if (Verbose&&!Thread.currentThread().isInterrupted()) try {
				
				DebugFile.write(T+"Ended at "+JFFTime.now());
			    DebugFile.newLine();
			    DebugFile.newLine();
			    
			} catch (IOException e) {
				e.printStackTrace();
			}
			

	    	if (CommandLine.options().verbose()&&!Thread.currentThread().isInterrupted()) try {// conversion debug
				
	    		out.newLine();
				out.write("Ended at "+JFFTime.now());
				out.newLine();
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	
		}
	}


	@Override
	public FFCommandLine commandLine() {
		
		return CommandLine;
	}


	@Override
	public boolean isDone() {
	
		return Converted;
	}


	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return Running;
	}
	
	@Override
	public void setOutputDebugInfo(BufferedWriter bw) {
		
		Verbose=true;
		DebugFile=bw;
	}
}
