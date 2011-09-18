package jff.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class FFGroupTaskImpl implements FFGroupTask, Runnable {
	
	private Vector<FFSingleTask> Tasks;
	private boolean Running=false;
	private boolean Converted=false;	
	private int ConvertedFiles=0;
	private String Name="";
	private boolean Verbose=false;
	private BufferedWriter DebugFile=null;
	
	public FFGroupTaskImpl(String n){
		Tasks=new Vector<FFSingleTask>();
		Name=n;
	}
	
	@Override
	public String toString() {
		
		if ((!Tasks.isEmpty())&&Name.isEmpty())
			Name=Tasks.get(0).commandLine().output().getParent();
			
		return Name+" ["+Tasks.size()+"] "+(Running?">":"||");
	}

	public FFGroupTaskImpl(){
		Tasks=new Vector<FFSingleTask>();
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
				
				DebugFile.write(S+"-- Task "+Name+" --");
				DebugFile.newLine();
				DebugFile.write(S+"[number of Files: "+Tasks.size()+" ]");
				DebugFile.newLine();

				Calendar cal = Calendar.getInstance();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    DebugFile.write(S+"Started at "+sdf.format(cal.getTime()));
				DebugFile.newLine();
			    DebugFile.newLine();
			  
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			for (int i=0;i<Tasks.size()&&!Thread.currentThread().isInterrupted();i++){
				
				if (!Tasks.get(i).isDone()){
				
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
							
							Calendar cal = Calendar.getInstance();
						    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						    
						    DebugFile.write(S+"Interrupted at "+sdf.format(cal.getTime()));
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
				
				Calendar cal = Calendar.getInstance();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    DebugFile.write(S+"Ended at "+sdf.format(cal.getTime()));
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
