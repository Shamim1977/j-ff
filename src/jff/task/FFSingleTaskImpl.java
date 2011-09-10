package jff.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jff.item.FFCommandLine;
import jff.item.VideoFile.ValueNotFoundException;

public class FFSingleTaskImpl implements FFSingleTask, Runnable {

	private FFCommandLine CommandLine;
	
	private boolean Running=false;
	private boolean Converted=false;	
	private int CurrentPass=1;
	private float ConvertedTime=0;
	
	public FFSingleTaskImpl(FFCommandLine c){
		
		CommandLine=c;
		
	}

	
	@Override
	public String toString() {
		return CommandLine.input().file().getName();
	}


	@Override
	public float progressPercentOfTheFirstPass() {
		
		if (CurrentPass==1)
			try {
				return ConvertedTime/CommandLine.input().Duration()*100;
			} catch (ValueNotFoundException ve){
				return 0; // if the duration of the input file is unknown it will return 0 (awful solution) 
			}
			else {
				if (CommandLine.options().twoPasses())
					return 100;
				else
					return 0;//it never happens ("only one pass option" and "executing pass 2" )
		}
	}

	@Override
	public float progressPercentOfTheSecondPass() {
		if (CommandLine.options().twoPasses()&&(CurrentPass==2))
			try {
				return ConvertedTime/CommandLine.input().Duration()*100;
			} catch (ValueNotFoundException ve){
				return 0; // if the duration of the input file is unknown it will return 0 (awful solution) 
			}
			else{ 	
				if (Converted==true)
					return 100;
				else
					return 0;
			
		}
	}
	
	
	@Override
	public float progressPercentOfTheTask() {
		
		if (CommandLine.options().twoPasses())
			return (progressPercentOfTheFirstPass()+progressPercentOfTheSecondPass())/2;
		else
			return progressPercentOfTheFirstPass();
	}

	private void updateStats(String l) {
		
		System.out.println(l);
		System.out.println(this.progressPercentOfTheFirstPass());
		System.out.println(this.progressPercentOfTheSecondPass());
		System.out.println(this.progressPercentOfTheTask());
		
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
		try {
			
			ProcessBuilder pb = new ProcessBuilder(CommandLine.getCommandLine(1));
			pb.redirectErrorStream(true);
		    
			CurrentPass=1;
		    ConvertedTime=0;
			Process p=pb.start();
		    
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));  

		    String line =in.readLine();

					            
		    while (line!=null&&!Thread.currentThread().isInterrupted() ) { 
		            	
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
				
				ProcessBuilder pb = new ProcessBuilder(CommandLine.getCommandLine(2));
				pb.redirectErrorStream(true);
			    
				CurrentPass=2;
			    ConvertedTime=0;
			    
				Process p=pb.start();
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));  

			    String line =in.readLine();
			            

			    while (line!=null&&!Thread.currentThread().isInterrupted() ) { 
			            	
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
			
			Running=false;
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
}
