package jff.item;

import java.io.File;
import java.util.List;
import java.util.Vector;

import jff.item.VideoFile.ValueNotFoundException;

public class FFCommandLineImpl implements FFCommandLine {

	private enum OS {LINUX, WINDOWS};
	
	private OS CurrentOS=null;
	
	private VideoFile Input;
	private File Output;
	private FFOptions Options;
	private Vector<String> CommandLine;
	private Vector<String> CommandLine2;
	
	private int OptimizedWidth;
	private int OptimizedHeight;
	private int XPad;
	private int YPad;
	
	public FFCommandLineImpl(VideoFile f, FFOptions options){
	
		CommandLine=new Vector<String>();
		CommandLine2=new Vector<String>();
		
		String s=System.getProperty( "os.name" );
		
		if (s.matches("(.*)(?i)linux(.*)")){
			CurrentOS=OS.LINUX;
		}
		
		if (s.matches("(.*)(?i)windows(.*)")){
			CurrentOS=OS.WINDOWS;
		}
			
		if (CurrentOS==null){
			//throw an exception TODO
		}
		
		Input=f;
		Output=options.outputFolder();
		Options=options;
		
		generate();
		
		System.out.println(toString());

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

	}


	private void setCommand(){
		
		if (CurrentOS==OS.LINUX)
			CommandLine.add("ffmpeg");
		
		if (CurrentOS==OS.WINDOWS)
			CommandLine.add("ffmpeg.exe");
		
		
		CommandLine.add("-y");
	
		if (Options.twoPasses()){
		
			if (CurrentOS==OS.LINUX)
				CommandLine2.add("ffmpeg");
			
			if (CurrentOS==OS.WINDOWS)
				CommandLine2.add("ffmpeg.exe");
			
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

		
		Vector<String> tmp=new Vector<String>();
		
		if(Options.outputOptions().videoBitrateFound()){
			tmp.add("-b");
			tmp.add(Options.outputOptions().videoBitrate()+"k");
		}
		
		if(Options.outputOptions().videoMaxBitrateFound()){
			int doubleMaxBitrate=2*Integer.parseInt(Options.outputOptions().videoMaxBitrate());
						
			tmp.add("-maxrate");
			tmp.add(Options.outputOptions().videoMaxBitrate()+"k");
			tmp.add("-bufsize");
			tmp.add(new Integer(doubleMaxBitrate).toString()+"k");
		}
		
		if(Options.outputOptions().videoFPSFound()){
			tmp.add("-r");
			tmp.add(Options.outputOptions().videoFPS());
		}
		
		if(Options.outputOptions().videoKeyFPSFound()){
			tmp.add("-g");
			tmp.add(Options.outputOptions().videoKeyFPS());
		}

		if (Options.twoPasses())
			CommandLine2.addAll(new Vector<String>(tmp));
		
		CommandLine.addAll(tmp);
				
	}
	
	private void adjustPAR() {
		// TODO Auto-generated method stub
		
		System.out.println("par different from 1:1 ");
		
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
			
			if(Options.outputOptions().videoWidthFound()&&Options.outputOptions().videoHeightFound()){
				
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
			
			//input width and height not found, i decided to try with the output dimensions and aspect, and no pads  
			
			Vector<String> tmp=new Vector<String>();
			
			if(Options.outputOptions().videoWidthFound()&&Options.outputOptions().videoHeightFound()){
		
				tmp.add("-s");
				tmp.add(Options.outputOptions().videoWidth()+"x"+Options.outputOptions().videoHeight());
			}
			
			if(Options.outputOptions().videoAspectFound()){
				
				tmp.add("-aspect");
				tmp.add("3:2");
			}
			
			if (Options.twoPasses())
				CommandLine2.addAll(new Vector<String>(tmp));					
			
			CommandLine.addAll(tmp);
			
		}

	}
	

	private void setVPreset() {
	
		if (Options.outputOptions().videoPresetFound()){
		
			CommandLine.add("-fpre");
			CommandLine.add(new File("preset").getAbsolutePath()+File.separator+Options.outputOptions().videoPreset()+".ffpreset");
		}
		
		if (Options.twoPasses()){
		
			if (Options.outputOptions().videoPresetFound()){
				
				CommandLine2.add("-fpre");
				CommandLine2.add(new File("preset").getAbsolutePath()+File.separator+Options.outputOptions().videoPreset()+".ffpreset");
			}
		}
		
}


	
	private void setVideoParams(){
		
		setWidthAndHeight();
		setBitrateAndFPS();
		setVPreset();
		
	};
	
	private void setAudioParams(){

		//beware, if in 2 passes mode, you haven't got to convert audio in first pass
		
		Vector<String> tmp=new Vector<String>();
	

		if (Options.outputOptions().audioChannelsFound()){
		
			tmp.add("-ac");
			tmp.add(Options.outputOptions().audioChannels());
		}
			
		if (Options.outputOptions().audioSamplingFrequencyFound()){
			
			tmp.add("-ar");
			tmp.add(Options.outputOptions().audioSamplingFrequency());
		}
		
		if (Options.outputOptions().audioBitrateFound()){
			
			tmp.add("-ab");
			tmp.add(Options.outputOptions().audioBitrate()+"k");
		}
		
		
		if (!Options.twoPasses())
			CommandLine.addAll(tmp);
		
		if (Options.twoPasses()){// se i passi sono 2 l'audio non va convertito nel primo
			
			CommandLine.add("-an");
			CommandLine2.addAll(tmp);
		}
	}


	private void setAVCodecs() {
		
		//beware, if in 2 passes mode, you haven't got to convert audio in first pass
		
		Vector<String> tmp=new Vector<String>();
		
		if (Options.outputOptions().videoCodecFound()){
			
			tmp.add("-vcodec");
			tmp.add(Options.outputOptions().videoCodec());
		}

		if (Options.outputOptions().audioCodecFound()){
			
			tmp.add("-acodec");
			tmp.add(Options.outputOptions().audioCodec());
		}
		
		
		if (!Options.twoPasses())	
			CommandLine.addAll(tmp);
		

		if (Options.twoPasses()){//se i passi sono 2 non serve codec audio tanto non lo converte
			
			if (Options.outputOptions().videoCodecFound()){
			
				CommandLine.add("-vcodec");
				CommandLine.add(Options.outputOptions().videoCodec());
			}
			
			CommandLine2.addAll(tmp);
		}
	}
	
	private void setOutput() {
		//stringa con nome meno l'estensione (si suppone che abbia estensione)
		

			if (!Options.twoPasses()){
				CommandLine.add(output().getAbsolutePath());
			}
		
			if (Options.twoPasses()){// the video created in first pass will be directed to /dev/null
			
				CommandLine.add("-f");
				CommandLine.add("rawvideo");
				
				if (CurrentOS==OS.LINUX)
					CommandLine.add("/dev/null");
				
				if (CurrentOS==OS.WINDOWS)
						CommandLine.add("nul");
			
				CommandLine2.add(output().getAbsolutePath());
		}
		
	}

	
	@Override
	public String detailedToString() {
		return "FFCommandLineImpl [" + System.getProperty("line.separator") + System.getProperty("line.separator") +
				"Input=" + Input.detailedToString() + System.getProperty("line.separator") + System.getProperty("line.separator") +
				"Options=" + Options + System.getProperty("line.separator") + System.getProperty("line.separator") +
				"Output=" + Output + System.getProperty("line.separator") + System.getProperty("line.separator") +
				"Commandline =" + CommandLine +
				(Options.twoPasses()?(System.getProperty("line.separator") + System.getProperty("line.separator") + "CommandLine2=" + CommandLine2):"") + System.getProperty("line.separator") + System.getProperty("line.separator") +
				"]";
	}

	@Override
	public String toString(){
		String par;
		String dar;
		String wxh;
		int dur;
		
		try{
			par=Input.PAR();
		}catch (ValueNotFoundException ve){
			par="???";
		}
		
		try{
			dar=Input.DAR();
		}catch (ValueNotFoundException ve){
			dar="???";
		}
		try{
			dur=Input.Duration();
		}catch (ValueNotFoundException ve){
			dur=0;
		}
		
		try{
			wxh=Input.width()+"x"+Input.height();
		}catch (ValueNotFoundException ve){
			wxh="???";
		}
		return "["+(dur==0?"???":dur)+", "+ wxh +">"+this.getOptimizedDimension()+", " + par + " , " + dar +"]";
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
		
		if (Options.outputOptions().outputExtensionFound()){
			String newName=Input.file().getName().split("\\.")[0];
		
			return new File(Output.getAbsolutePath()+File.separator+newName+"."+Options.outputOptions().outputExtension());
		} else
			return new File(Output.getAbsolutePath()+File.separator+Input.file().getName());
	
	}

	@Override
	public Vector<String> getCommandLine(int pass) {
		
		if (pass==1)
			return CommandLine;
		
		if (pass==2)
			return CommandLine2;
		
		return null;
	}
	
	@Override
	public String getOptimizedDimension(){
		return (new Integer(OptimizedWidth).toString())+"x"+(new Integer(OptimizedHeight).toString());
	}
	
}
