package jff.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import jff.utility.JFFParser;
import jff.utility.JFFParserImpl;



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

		
		JFFParser p;
		
		do{
		
			try {
				p=new JFFParserImpl(b.readLine());
			} catch (IOException e) {
			
				p=new JFFParserImpl(null);
				e.printStackTrace();
			}
		
		
			if (p.find("outputformat"))
				OutputOptions=new FFOutputOptionsImpl(p.getString(),Verbose);
			else if (p.find("outputdir")){
			
				File f=new File(p.getString());
			
				if (f.isDirectory())
					Output=f;
				else
					Output=new File(System.getProperty("user.dir"));
				
				}
			else if (p.find("optionthreads")){
			
				try {
			
					setThreads(p.getInt());	
			
				} catch (NumberFormatException e){}
			}
			else if (p.find("optiontwopasses"))
				setTwoPasses(p.getBoolean());
			
			else if (p.find("optionpads"))
				setPads(p.getBoolean());
			
			else if (p.find("optionsmall"))
				setSmallFiles(p.getBoolean());
		
			else if (p.find("optiondebug"))
				setVerbose(p.getBoolean());
		
		} while (!p.isEmpty());
		
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
