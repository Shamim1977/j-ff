package jff.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;



public class FFOptionsImpl implements FFOptions {
		
		private FFOutputOptions OutputOptions;
		private File Output;
		private boolean TwoPasses=true;
		private int Threads=4;
		private boolean AddPads=false;
		private boolean SmallFiles=true;
		private Boolean Verbose=false;
		
		
		private void constructorInfo(){
			
			if (Verbose){
				
				System.out.println("Options:");
				System.out.println("-Verbose "+Verbose);
				System.out.println("-Passes "+TwoPasses);
				
				if (AddPads)
					System.out.println("-Pads ON");
				else
					System.out.println("-Pads OFF");
				
				System.out.println("-Output "+OutputOptions.format());
				
				System.out.println();
			}
		}
		
		
		public FFOptionsImpl(String outputFormat, File outputFolder, boolean isVerbose){
			
			Verbose=isVerbose;
			
			OutputOptions=new FFOutputOptionsImpl(outputFormat,Verbose);
			
			Output=outputFolder;
			
			constructorInfo();
			
			}

	public FFOptionsImpl(BufferedReader b) {

		
		String line=null;
		
		do{
		
		try {
			line=b.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (line!=null&&line.split(":")[0].equals("outputformat")){
			
			
			OutputOptions=new FFOutputOptionsImpl(line.split(":")[1].trim(),Verbose);
		}
		
		else if (line!=null&&line.split(":")[0].equals("outputdir")){
			
			File f=new File(line.split(":")[1].trim());
			
			
			if (f.isDirectory())
				Output=f;
			else
				Output=new File(System.getProperty("user.dir"));
				
		}

		else if (line!=null&&line.split(":")[0].equals("optionthreads")){
			
			try {
				int t=Integer.parseInt(line.split(":")[1].trim());
				setThreads(t);
				
			} catch (NumberFormatException e){}
		}

		else if (line!=null&&line.split(":")[0].equals("optiontwopasses")){
			
			if (line.split(":")[1].trim().equals("true"))
				setTwoPasses(true);
			else if (line.split(":")[1].trim().equals("false"))
					setTwoPasses(false);
			
		}

		else if (line!=null&&line.split(":")[0].equals("optionpads")){
			
			if (line.split(":")[1].trim().equals("true"))
				setPads(true);
			else if (line.split(":")[1].trim().equals("false"))
					setPads(false);
			
		}

		else if (line!=null&&line.split(":")[0].equals("optionsmall")){
			
			if (line.split(":")[1].trim().equals("true"))
				setSmallFiles(true);
			else if (line.split(":")[1].trim().equals("false"))
					setSmallFiles(false);
		}

		else if (line!=null&&line.split(":")[0].equals("optiondebug")){
			
			if (line.split(":")[1].trim().equals("true"))
				setVerbose(true);
			else if (line.split(":")[1].trim().equals("false"))
					setVerbose(false);
		}

		} while (line!=null);
		
		constructorInfo();
		
		}

	public void setOutPutFormat(String s){
		
		OutputOptions=new FFOutputOptionsImpl(s,Verbose);
	}

	@Override
	public String toString() {
		return "FFOptionsImpl [AddPads=" + AddPads + ", OutputOptions="
				+ OutputOptions + ", Passes=" + TwoPasses + ", Verbose=" + Verbose
				+ "]";
	}

	@Override
	public FFOutputOptions outputOptions() {
		
		return OutputOptions;
	}

	@Override
	public boolean pads() {
		
		return AddPads;
	}

	@Override
	public boolean twoPasses() {
		
		return TwoPasses;
	}
	
	@Override
	public int threads() {
		
		return Threads;
	}
	
	@Override
	public boolean smallFiles() {
		
		return SmallFiles;
	}
	

	@Override
	public void setPads(boolean b) {
		AddPads=b;
		
	}

	@Override
	public void setThreads(int t) {
		Threads=t;
		
	}

	@Override
	public void setTwoPasses(boolean b) {
		TwoPasses=b;
		
	}

	@Override
	public File outputFolder() {
		
		return Output;
	}

	@Override
	public void setOutputFile(File f) {
		Output=f;
	}

	@Override
	public void setVerbose(boolean b) {
		
		Verbose=b;
	}

	@Override
	public boolean verbose() {
		
		return Verbose;
	}

	@Override
	public FFOptions createDuplicate() {
		
		FFOptions tmp=new FFOptionsImpl(OutputOptions.format(),new File(Output.getAbsolutePath()),Verbose);
		
		tmp.setThreads(threads());
		tmp.setTwoPasses(twoPasses());
		tmp.setPads(pads());
		tmp.setSmallFiles(smallFiles());
		
		return tmp;
	}

	@Override
	public void setSmallFiles(boolean b) {
		
		SmallFiles=b;
	}
	
	
}
