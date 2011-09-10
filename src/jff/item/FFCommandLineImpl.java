package jff.item;

import java.io.File;
import java.util.List;
import java.util.Vector;

import jff.item.VideoFile.ValueNotFoundException;

public class FFCommandLineImpl implements FFCommandLine {

	private VideoFile Input;
	private File Output;
	private FFOptions Options;
	private Vector<String> CommandLine;
	private Vector<String> CommandLine2;
	private boolean Verbose;
	
	private int OptimizedWidth;
	private int OptimizedHeight;
	private int XPad;
	private int YPad;
	
	public FFCommandLineImpl(VideoFile f, FFOptions options, boolean isVerbose){
	
		CommandLine=new Vector<String>();
		CommandLine2=new Vector<String>();
		
		Input=f;
		Output=options.outputFolder();
		Options=options;
		Verbose=isVerbose;
		
		generate();

	}

	public void generate() {
		
		setCommand();
		setInput();
		setPasses();
		setThreads();
		setVideoParams();
		setAudioParams();
		setAVCodecs();
		setOutput();

		
		if (Verbose){

			System.out.println("Generated command line:");
			System.out.println(CommandLine);
			
			if (Options.twoPasses()){
				System.out.println("Generated command line for second pass:");
				System.out.println(CommandLine2);
			}
		}
	}


	private void setCommand(){
		
		CommandLine.add("ffmpeg");
		CommandLine.add("-y");
	
		if (Options.twoPasses()){
		
			CommandLine2.add("ffmpeg");
			CommandLine2.add("-y");
		}
		
	}
	
	
	private void setInput(){
		
		CommandLine.add("-i");
		CommandLine.add(Input.file().getAbsolutePath());
		
		if (Options.twoPasses()){
			CommandLine2.add("-i");
			CommandLine2.add(Input.file().getAbsolutePath());
			
		}
		
	}
	
	private void setPasses(){
		
		if (Options.twoPasses()){
			
			CommandLine.add("-pass");
			CommandLine.add("1");
			
			CommandLine2.add("-pass");
			CommandLine2.add("2");
				
		}
		
	};
	
	private void setThreads(){
		
		CommandLine.add("-threads");
		CommandLine.add(new Integer(Options.threads()).toString());
		
		if (Options.twoPasses()){
			
			CommandLine2.add("-threads");
			CommandLine2.add(new Integer(Options.threads()).toString());
			
		}
			
	};

	
	private void setBitrateAndFPS() {

		int doubleMaxBitrate=2*Integer.parseInt(Options.outputOptions().videoMaxBitrate());
		
		CommandLine.add("-b");
		CommandLine.add(Options.outputOptions().videoBitrate()+"k");
		CommandLine.add("-maxrate");
		CommandLine.add(Options.outputOptions().videoMaxBitrate()+"k");
		CommandLine.add("-bufsize");
		CommandLine.add(new Integer(doubleMaxBitrate).toString()+"k");
		CommandLine.add("-r");
		CommandLine.add(Options.outputOptions().videoFPS());
		CommandLine.add("-g");
		CommandLine.add(Options.outputOptions().videoKeyFPS());
		
		if (Options.twoPasses()){

			CommandLine2.add("-b");
			CommandLine2.add(Options.outputOptions().videoBitrate()+"k");
			CommandLine2.add("-maxrate");
			CommandLine2.add(Options.outputOptions().videoMaxBitrate()+"k");
			CommandLine2.add("-bufsize");
			CommandLine2.add(new Integer(doubleMaxBitrate).toString()+"k");
			CommandLine2.add("-r");
			CommandLine2.add(Options.outputOptions().videoFPS());
			CommandLine2.add("-g");
			CommandLine2.add(Options.outputOptions().videoKeyFPS());

		}
		
	}
	
	private void adjustPAR() {
		// TODO Auto-generated method stub
		
		System.out.println("par pecca");
		
	}
	
	private void adjustDimensions() throws ValueNotFoundException {
		
		int w=Integer.parseInt(Options.outputOptions().videoWidth());
		int h=Integer.parseInt(Options.outputOptions().videoHeight());
		
		boolean smallFilesImpossible=Options.smallFiles()&&((Input.width()>w)||(Input.height()>h));
			//true when smallfiles is set but the files are too big
		
		if (!Options.smallFiles()||smallFilesImpossible) {
		
			float outputAspect;//calculated output aspect (o myOption.myOutputFormat.VideoAspect oppure width/Height)
			float inputAspect;
		
			OptimizedWidth=w;
			OptimizedHeight=h;
		
		
			outputAspect=OptimizedWidth/(float)OptimizedHeight;
			inputAspect=Input.width()/((float)Input.height());
		
			if (inputAspect>outputAspect){//horizontal pads
		
				int oldHeight=OptimizedHeight;//tmp var with old height
			
				OptimizedHeight=(int) (Input.height()*(OptimizedWidth/(float)Input.width()));
		
				YPad=(oldHeight-OptimizedHeight)/2;

			}
		
			if (inputAspect<outputAspect){//vertical pads
			
				int oldWidth=OptimizedWidth;//tmp var with old width
			
				OptimizedWidth=(int) (Input.width()*(OptimizedHeight/(float)Input.height()));
		
				XPad=(oldWidth-OptimizedWidth)/2;

			}
		
			//calculated values need to be even
		
			OptimizedWidth-=OptimizedWidth%2;
			OptimizedHeight-=OptimizedHeight%2;
			XPad-=XPad%2;
			YPad-=YPad%2;
		
			if(Verbose){
				System.out.println("Calculated optimal Width for the Output: "+OptimizedWidth);
				System.out.println("Calculated optimal Height for the Output: "+OptimizedHeight);
    		
				if (Options.pads()){
					System.out.println("Calculated x pad for the Output: "+XPad);
					System.out.println("Calculated y pad for the Output: "+YPad);
				}
			}
		
		} else{
			
			OptimizedWidth=Input.width()-Input.width()%2;
			OptimizedHeight=Input.height()-Input.height()%2;
			
		}
			
	}


	private void setWidthAndHeight() {
		
		try {
			if (!Input.PAR().equals("1:1")){
				
				adjustPAR();
			}
		} catch (ValueNotFoundException v){
			//input par not found, it's to decide what to do in this case
		}
				
			
			/* ***********************
			This code dont work for me because i have no filters installed in ffmpeg 
			 
			
			CommandLine+="-s "+CalculatedWidth+"x"+CalculatedHeight+" -vf pad="+
			myOption.myOutputFormat.VideoWidth+":"+
			myOption.myOutputFormat.VideoHeight+":"+
			CalculatedXPad+":"+
			CalculatedYPad+" ";
			
			if (myOption.Passes==2){
				CommandLine2+="-s "+CalculatedWidth+"x"+CalculatedHeight+" -vf pad="+
				myOption.myOutputFormat.VideoWidth+":"+
				myOption.myOutputFormat.VideoHeight+":"+
				CalculatedXPad+":"+
				CalculatedYPad+" ";
			}
			
			
			/*********** with no filters installed in ffmpeg ********************* */
		
		try {
			
			Input.width();
			Input.height();//i call these methods so if there isn't data on width and height
						   //the exception will be called now
			
			adjustDimensions();
			
			
			CommandLine.add("-s");
			CommandLine.add(new Integer(OptimizedWidth).toString()+"x"+new Integer(OptimizedHeight).toString());
			

			if (Options.twoPasses()){
				
				CommandLine2.add("-s");
				CommandLine2.add(new Integer(OptimizedWidth).toString()+"x"+new Integer(OptimizedHeight).toString());
			}
			
			
			if (Options.pads()&&((XPad!=0)||(YPad!=0))){// pads smallfiles===no pads because it will always be XPad==0 and YPad==0
				
				CommandLine.add("-vf"); //-vf pad=totalW:totalH:xpad:ypad   (you can add ":color" but here default color= black is enough)
				CommandLine.add("pad="+new Integer(Options.outputOptions().videoWidth()).toString()+":"+new Integer(Options.outputOptions().videoHeight()).toString()+":"+new Integer(XPad).toString()+":"+new Integer(YPad).toString());
					
				if (Options.twoPasses()){
						
					CommandLine2.add("-vf");
					CommandLine2.add("pad="+new Integer(Options.outputOptions().videoWidth()).toString()+":"+new Integer(Options.outputOptions().videoHeight()).toString()+":"+new Integer(XPad).toString()+":"+new Integer(YPad).toString());
					}
				
			}
			
			
					
			/* ************************ filters not installed 
			if (Options.pads()&&(XPad!=0)){// X pad
					
				CommandLine.add("-padleft");
				CommandLine.add(new Integer(XPad).toString());
				CommandLine.add("-padright");
				CommandLine.add(new Integer(XPad).toString());
					
				if (Options.twoPasses()){
						
					CommandLine2.add("-padleft");
					CommandLine2.add(new Integer(XPad).toString());
					CommandLine2.add("-padright");
					CommandLine2.add(new Integer(XPad).toString());
				}
				
			}
			
			
			if (Options.pads()&&(YPad!=0)){// Y pad
					
				CommandLine.add("-padtop");
				CommandLine.add(new Integer(YPad).toString());
				CommandLine.add("-padbottom");
				CommandLine.add(new Integer(YPad).toString());
				
					if (Options.twoPasses()){
						
						CommandLine.add("-padtop");
						CommandLine.add(new Integer(YPad).toString());
						CommandLine.add("-padbottom");
						CommandLine.add(new Integer(YPad).toString());
					}
				
				}
				
			                 ****************************** */
		} catch (ValueNotFoundException v){
			
			//input width and height not found, it's to decide what to do in this case 
			
			//a sad conclusion should be:
			CommandLine.add("-s");
			CommandLine.add("960x640");
			CommandLine.add("-aspect");
			CommandLine.add("3:2");
			
			if (Options.twoPasses()){
				
				CommandLine2.add("-s");
				CommandLine2.add("960x640");
				CommandLine2.add("-aspect");
				CommandLine2.add("3:2");
			}
			
		}

	}
	

	private void setVPreset() {
	
		CommandLine.add("-vpre");
		CommandLine.add(Options.outputOptions().videoPreset());
		
		if (Options.twoPasses()){
			
			CommandLine2.add("-vpre");
			CommandLine2.add(Options.outputOptions().videoPreset());
		}
		
}


	
	private void setVideoParams(){
		
		setWidthAndHeight();
		setBitrateAndFPS();
		setVPreset();
		
	};
	
	private void setAudioParams(){

		//beware, if in 2 passes mode, you haven't got to convert audio in first pass
		
		if (!Options.twoPasses()){
			
			CommandLine.add("-ac");
			CommandLine.add(Options.outputOptions().audioChannels());
			CommandLine.add("-ar");
			CommandLine.add(Options.outputOptions().audioSamplingFrequency());
			CommandLine.add("-ab");
			CommandLine.add(Options.outputOptions().audioBitrate()+"k");
			

		}
		
		if (Options.twoPasses()){// se i passi sono 2 l'audio non va convertito nel primo
			
			CommandLine.add("-an");

			CommandLine2.add("-ac");
			CommandLine2.add(Options.outputOptions().audioChannels());
			CommandLine2.add("-ar");
			CommandLine2.add(Options.outputOptions().audioSamplingFrequency());
			CommandLine2.add("-ab");
			CommandLine2.add(Options.outputOptions().audioBitrate()+"k");

		}
	}


	private void setAVCodecs() {
		
		//beware, if in 2 passes mode, you haven't got to convert audio in first pass
		
		if (!Options.twoPasses()){
			
			CommandLine.add("-vcodec");
			CommandLine.add(Options.outputOptions().videoCodec());
			CommandLine.add("-acodec");
			CommandLine.add(Options.outputOptions().audioCodec());
		}

		if (Options.twoPasses()){//se i passi sono 2 non serve codec audio tanto non lo converte
			
			CommandLine.add("-vcodec");
			CommandLine.add(Options.outputOptions().videoCodec());
			
			CommandLine2.add("-vcodec");
			CommandLine2.add(Options.outputOptions().videoCodec());
			CommandLine2.add("-acodec");
			CommandLine2.add(Options.outputOptions().audioCodec());
		}
	}
	
	private void setOutput() {
		//stringa coò nome meno l'estensione (si suppone che abbia estensione)
		
		System.out.println(Input.file().getName());
		

			if (!Options.twoPasses()){
				CommandLine.add(output().getAbsolutePath());
			}
		
			if (Options.twoPasses()){// the video created in first pass will be directed to /dev/null
			
				CommandLine.add("-f");
				CommandLine.add("rawvideo");
				CommandLine.add("/dev/null");
			
				CommandLine2.add(output().getAbsolutePath());
		}
		
	}

	
	@Override
	public String toString() {
		return "FFCommandLineImpl [Input=" + Input + ", Options=" + Options
				+ ", Output=" + Output + "]";
	}

	@Override
	public VideoFile input() {
		
		return Input;
	}

	@Override
	public FFOptions options() {
		
		return Options;
	}

	@Override
	public File output() {
		String newName=Input.file().getName().split("\\.")[0];
		
		return new File(Output.getAbsolutePath()+File.separator+newName+"."+Options.outputOptions().outputExtension());
	}

	@Override
	public Vector<String> getCommandLine(int pass) {
		
		if (pass==1)
			return CommandLine;
		
		if (pass==2)
			return CommandLine2;
		
		return null;
	}
	
	
}
