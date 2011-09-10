package jff.main;

import java.io.File;

import jff.item.FFCommandLine;

import jff.item.FFCommandLineImpl;
import jff.item.FFOptionsImpl;
import jff.item.VideoFile;
import jff.item.VideoFileImpl;
import jff.task.FFGroupTask;
import jff.task.FFGroupTaskImpl;
import jff.task.FFSingleTask;
import jff.task.FFSingleTaskImpl;

public class JFF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FFCommandLine f1=new FFCommandLineImpl(
				new VideoFileImpl(new File("/home/francesco/Scaricati/input R.flv"),true),
				new FFOptionsImpl("IPOD", new File("/home/francesco/Scaricati"), true),
				true
				);
	
		FFCommandLine f2=new FFCommandLineImpl(
				new VideoFileImpl(new File("/home/francesco/Scaricati/input B1.mp4"),true),
				new FFOptionsImpl("IPOD",new File("/home/francesco"),
						true),
				true
				);
		
		FFCommandLine f3=new FFCommandLineImpl (
						new VideoFileImpl(new File("/home/francesco/Scaricati/input B2.3gp"),true),
						new FFOptionsImpl("IPOD",new File("/home/francesco/Scaricati"),
								true),
						true
						);
	
		
		FFSingleTask ff1=new FFSingleTaskImpl(f1);
		FFSingleTask ff2=new FFSingleTaskImpl(f2);
		FFSingleTask ff3=new FFSingleTaskImpl(f3);
		
		FFGroupTask fff=new FFGroupTaskImpl();
		
		fff.add(ff1);
		fff.add(ff2);
		fff.add(ff3);
		
		
		new Thread(fff).start();
		while (true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("TOTAL="+fff.progressPercent());
		}
	}

}
