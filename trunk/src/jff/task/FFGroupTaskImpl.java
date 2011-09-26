package jff.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import jff.translation.JFFStrings;
import jff.utility.JFFTime;

public class FFGroupTaskImpl implements FFGroupTask, Runnable {
	
	private Vector<FFSingleTask> Tasks;
	private boolean Running=false;
	private boolean Converted=false;	
	private int ConvertedFiles=0;
	private String Name="";
	private boolean Verbose=false;
	private BufferedWriter DebugFile=null;
	private JFFStrings S;
	
	public FFGroupTaskImpl(JFFStrings strings, String n){
		Tasks=new Vector<FFSingleTask>();
		Name=n;
		S=strings;
	}
	
	@Override
	public String toString() {
		
		if ((!Tasks.isEmpty())&&Name.isEmpty())
			Name=Tasks.get(0).commandLine().output().getParent();
			
		return Name+" ["+Tasks.size()+"] ["+(Running?S.executing():S.inPause())+"]";
	}

	public FFGroupTaskImpl(JFFStrings strings){
		Tasks=new Vector<FFSingleTask>();
		S=strings;
	}

	@Override
	public void add(FFSingleTask t) {
		
		Tasks.add(t);
	}

	@Override
	public FFSingleTask get(int i) {
		
		return Tasks.get(i);
	}

	@Override
	public boolean isEmpty() {
		
		return Tasks.isEmpty();
	}

	@Override
	public float progressPercent() {
		if (ConvertedFiles<Tasks.size())
			return (((float)ConvertedFiles)/Tasks.size())*100+Tasks.get(ConvertedFiles).progressPercentOfTheTask()/Tasks.size();
		else
			return 100;
	}

	@Override
	public int size() {
		
		return Tasks.size();
	}

	@Override
	public void run() {
		
		if (!Running&&!Converted){
			
			Running=true;
			Thread t;
			
			if (Verbose) try {
				
				DebugFile.write(T+"-- Task "+Name+" --");
				DebugFile.newLine();
				DebugFile.write(T+"[number of Files: "+Tasks.size()+" ]");
				DebugFile.newLine();

				DebugFile.write(T+"Started at "+JFFTime.now());
				DebugFile.newLine();
			    DebugFile.newLine();
			  
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			for (int i=0;i<Tasks.size()&&!Thread.currentThread().isInterrupted();i++){
				
				if (!Tasks.get(i).isDone()){
				
					if (Verbose)
						Tasks.get(i).setOutputDebugInfo(DebugFile);
					
					t=new Thread(Tasks.get(i)); 
					t.start();
				
					try {
						t.join();
					} catch (InterruptedException e) {
					
						t.interrupt();

						ConvertedFiles--;
						
						if (Verbose) try {
							
							try {
								t.join();
							} catch (InterruptedException e2) {}	
							
							DebugFile.write(T+"Interrupted at "+JFFTime.now());
							DebugFile.newLine();
							DebugFile.newLine();
							
						
						} catch (IOException e1) {
								// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						Thread.currentThread().interrupt();

					}
				
					ConvertedFiles++;
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
			
			if (Tasks.size()==ConvertedFiles)
				Converted=true;
		}
		
	}

	@Override
	public Vector<FFSingleTask> tasks() {
		
		return Tasks;
	}

	@Override
	public boolean isRunning() {
		
		return Running;
	}

	@Override
	public void remove(FFSingleTask r) {
		
		if (!r.isRunning())
			Tasks.remove(r);
		
	}

	@Override
	public void setOutputDebugInfo(BufferedWriter bw) {
		
		Verbose=true;
		DebugFile=bw;
	}
	
	
}
