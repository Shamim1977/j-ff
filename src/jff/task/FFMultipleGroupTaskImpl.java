package jff.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import jff.item.JFFSelectableVideoFileImpl;
import jff.translation.JFFStrings;
import jff.utility.JFFParser;
import jff.utility.JFFParserImpl;
import jff.utility.JFFTime;

public class FFMultipleGroupTaskImpl implements FFMultipleGroupTask, Runnable {

	private static final String DEBUGINFOTASKS="debug_info_tasks.txt";
	
	private Vector<FFGroupTask> Tasks;
	
	private boolean Running=false;
	private JFFStrings S;
	private boolean Verbose=false;
	
	
	public FFMultipleGroupTaskImpl(JFFStrings strings){
		Tasks=new Vector<FFGroupTask>();
		S=strings;
	}

	public FFMultipleGroupTaskImpl(JFFStrings strings, boolean isVerbose){
		Tasks=new Vector<FFGroupTask>();
		Verbose=isVerbose;
		S=strings;
	}

	
	public FFMultipleGroupTaskImpl(JFFStrings strings, BufferedReader b) {
		Tasks=new Vector<FFGroupTask>();
		S=strings;
		
		JFFParser p;
		
		do{
		
		try {
			p=new JFFParserImpl(b.readLine());
		} catch (IOException e) {
			
			p=new JFFParserImpl(null);
			e.printStackTrace();
		}
		
		if (p.find("appdebug"))
			Verbose=p.getBoolean();
		
		} while (!p.isEmpty());
		

	}

	@Override
	public String toString() {
		return S.allProcesses()+" ["+Tasks.size()+"]"+" ["+(Running?S.executing():S.inPause())+"]";
	}



	@Override
	public void add(FFGroupTask t) {
		
		Tasks.add(t);
	}

	@Override
	public FFGroupTask get(int i) {
		
		return Tasks.get(i);
	}

	@Override
	public boolean isEmpty() {
		
		return Tasks.isEmpty();
	}


	@Override
	public int sizeInTasks() {
		
		return Tasks.size();
	}
	
	@Override
	public int sizeInFiles() {
		
		int tmp=0;
		
		for (int i=0;i<Tasks.size();i++)
			tmp+=Tasks.get(i).size();
			
		return tmp;
	}

	@Override
	public void run() {
		
		if (!Running){
			Running=true;
		
			Thread t;
			BufferedWriter out=null;
			
			if (Verbose) try {
			
				FileWriter fstream = new FileWriter(DEBUGINFOTASKS,true);
				out= new BufferedWriter(fstream);
				out.write("== All Tasks ==");
				out.newLine();
				out.write("[number of Tasks: "+sizeInTasks()+" ]");
				out.newLine();
				out.write("[number of Files: "+sizeInFiles()+" ]");
				out.newLine();
				out.write("Started at "+JFFTime.now());
				out.newLine();
				out.newLine();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for (int i=0;i<Tasks.size()&&!Thread.currentThread().isInterrupted();i++){
					
				if (Verbose)
					Tasks.get(i).setOutputDebugInfo(out);
				
				t=new Thread(Tasks.get(i));
				t.start();
				
				try {
					t.join();
				} catch (InterruptedException e) {
										
					t.interrupt();
					
					if (out!=null) try {
						
						try {
							t.join();
						} catch (InterruptedException e2) {}
				
						out.write("Interrupted at "+JFFTime.now());
						out.newLine();
						out.newLine();
						out.close();
					
					} catch (IOException e1) {
							// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					Thread.currentThread().interrupt();
					
						
					}
				
			}
		
			
			Running=false;
			if (out!=null&&!Thread.currentThread().isInterrupted()) try {
				
				out.write("Ended at "+JFFTime.now());
				out.newLine();
				out.newLine();
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}

	@Override
	public Vector<FFGroupTask> groupTasks() {
		return Tasks;
	}


	@Override
	public int runningTasks() {

		int tmp=0;
		
		for (int i=0;i<Tasks.size();i++)
			if (Tasks.get(i).isRunning())
				tmp++;
			
		return tmp;
		
		
	}


	@Override
	public int pausedTasks() {
int tmp=0;
		
		for (int i=0;i<Tasks.size();i++)
			if (!Tasks.get(i).isRunning())
				tmp++;
			
		return tmp;
		
		
	}


	@Override
	public boolean isRunning() {
		
		return Running;
	}


	@Override
	public void remove(FFGroupTask r) {
		
		if (!r.isRunning())
			Tasks.remove(r);
	}


	@Override
	public void removeAll() {
		
		if (!Running)
			Tasks.removeAllElements();
		
	}
	
	
}
