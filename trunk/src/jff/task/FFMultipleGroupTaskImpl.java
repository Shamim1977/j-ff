package jff.task;

import java.util.Vector;

public class FFMultipleGroupTaskImpl implements FFMultipleGroupTask, Runnable {

	private Vector<FFGroupTask> Tasks;
	
	private boolean Running=false;
	private String Name="";
	
	
	
	public FFMultipleGroupTaskImpl(String n){
		Tasks=new Vector<FFGroupTask>();
		Name=n;
	}

	
	@Override
	public String toString() {
		return Name+" ["+Tasks.size()+"]";
	}


	public FFMultipleGroupTaskImpl(){
		Tasks=new Vector<FFGroupTask>();
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

			
			for (int i=0;i<Tasks.size()&&!Thread.currentThread().isInterrupted();i++){
					
				t=new Thread(Tasks.get(i)); 
				t.start();
				
				try {
					t.join();
				} catch (InterruptedException e) {
					System.out.println("interruppppppppppppppp");
					t.interrupt();
					Thread.currentThread().interrupt();
					
					
				}
				
			}
		
			System.out.println("finittttttttttttt");
			
			
			Running=false;

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
