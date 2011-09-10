package jff.task;

import java.util.Vector;

public class FFGroupTaskImpl implements FFGroupTask, Runnable {

	private Vector<FFSingleTask> Tasks;
	
	private boolean Running=false;
	private boolean Converted=false;	
	private int ConvertedFiles=0;
	private String Name="";
	
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
		
			for (int i=0;i<Tasks.size()&&!Thread.currentThread().isInterrupted();i++){
				
				if (!Tasks.get(i).isDone()){
				
					t=new Thread(Tasks.get(i)); 
					t.start();
				
					try {
						t.join();
					} catch (InterruptedException e) {
					
						t.interrupt();

						Thread.currentThread().interrupt();
						ConvertedFiles--;
					}
				
					ConvertedFiles++;
				}
			}
		
			Running=false;
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
	
	
}
