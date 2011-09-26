package jff.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import jff.translation.JFFStringsImpl;
import jff.utility.JFFParser;
import jff.utility.JFFParserImpl;

public class FFOutputOptionsImpl implements FFOutputOptions {

		private String Format;

		private String VideoCodec;
		private String VideoPreset;
		private String VideoWidth;
		private String VideoHeight;
		private String VideoAspect;
		private String VideoBitrate;
		private String VideoMaxBitrate;
		private String VideoFPS;
		private String VideoKeyFPS;
		private String AudioCodec;
		private String AudioChannels;
		private String AudioBitrate;
		private String AudioSamplingFrequency;
		private String OutputExtension; 
		
		/* ********* old constructor
		public FFOutputOptionsImpl(String format, boolean isVerbose){
			
			Format=format;
			
			if (format.equals("iPhone4")){// set IPOD data
				
				VideoCodec="libx264";
				VideoPreset="iphone";
				VideoWidth="960";
				VideoHeight="640";
				VideoAspect="3:2";
				VideoBitrate="768";
				VideoMaxBitrate="2000";
				//Frame Rate - for 29.97 use 30000/1001 and for 23.976 use 24000/1001
				VideoFPS="30000/1001";
				VideoKeyFPS="30";
				AudioCodec="libfaac";// useless
				AudioChannels="2";
				AudioBitrate="128";
				AudioSamplingFrequency="44100";
				OutputExtension="mp4";
			
			}
			
			if (isVerbose)
				System.out.println(toString());
		}
		************************** */
		
		public FFOutputOptionsImpl(String format){
		
			Format=format;
			
			try {
				
				FileReader fstream=new FileReader(new File("format"+File.separator+format+".txt"));
				BufferedReader b=new BufferedReader(fstream);
			
				JFFParser p;
				
				do{
				
					try {
						p=new JFFParserImpl(b.readLine());
					} catch (IOException e) {
					
						p=new JFFParserImpl(null);
						e.printStackTrace();
					}
				
					if (p.find("videocodec"))
						VideoCodec=p.getString();
					
					else if (p.find("videopreset"))
						VideoPreset=p.getString();
				
					else if (p.find("videowidth"))
						VideoWidth=p.getString();
				
					else if (p.find("videoheight"))
						VideoHeight=p.getString();
				
					else if (p.find("videoaspect"))
						VideoAspect=p.getStringWithSeparators();//i espect to find a value with a ":" inside
				
					else if (p.find("videobitrate"))
						VideoBitrate=p.getString();
				
					else if (p.find("videomaxbitrate"))
						VideoMaxBitrate=p.getString();
				
					else if (p.find("videofps"))
						VideoFPS=p.getString();
				
					else if (p.find("videokeyfps"))
						VideoKeyFPS=p.getString();
				
					else if (p.find("audiocodec"))
						AudioCodec=p.getString();
				
					else if (p.find("audiochannels"))
						AudioChannels=p.getString();
				
					else if (p.find("audiobitrate"))
						AudioBitrate=p.getString();
				
					else if (p.find("audiosamplingfrequency"))
						AudioSamplingFrequency=p.getString();
				
					else if (p.find("outputextension"))
						OutputExtension=p.getString();
					
					
				
				} while (!p.isEmpty());
			
				b.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		@Override
		public boolean is(String format){
			
			return Format==format;
		}
		
		@Override
		public String toString() {
			return "FFOutputOptionsImpl [" + System.getProperty("line.separator") +
					"Format=" + Format + System.getProperty("line.separator") +
					"VideoCodec=" + VideoCodec + System.getProperty("line.separator") +
					"VideoPreset=" + VideoPreset + System.getProperty("line.separator") +
					"VideoWidth=" + VideoWidth + System.getProperty("line.separator") +
					"VideoHeight=" + VideoHeight + System.getProperty("line.separator") +
					"VideoAspect=" + VideoAspect + System.getProperty("line.separator") +
					"VideoBitrate=" + VideoBitrate + System.getProperty("line.separator") +
					"VideoMaxBitrate=" + VideoMaxBitrate + System.getProperty("line.separator") +
					"VideoFPS=" + VideoFPS + System.getProperty("line.separator") +
					"VideoKeyFPS=" + VideoKeyFPS + System.getProperty("line.separator") +
					"AudioCodec=" + AudioCodec + System.getProperty("line.separator") +
					"AudioChannels=" + AudioChannels + System.getProperty("line.separator") +
					"AudioBitrate=" + AudioBitrate + System.getProperty("line.separator") +
					"AudioSamplingFrequency=" + AudioSamplingFrequency + System.getProperty("line.separator") +
					"OutputExtension=" + OutputExtension + System.getProperty("line.separator") +
					"]";

		}

		@Override
		public String format() {
			
			return Format;
		}
		
		@Override
		public String audioBitrate() {
			
			return AudioBitrate;
		}

		@Override
		public String audioChannels() {
			
			return AudioChannels;
		}

		@Override
		public String audioCodec() {
			
			return AudioCodec;
		}

		@Override
		public String audioSamplingFrequency() {
			
			return AudioSamplingFrequency;
		}

		@Override
		public String outputExtension() {
			
			return OutputExtension;
		}

		@Override
		public String videoAspect() {
			
			return VideoAspect;
		}

		@Override
		public String videoBitrate() {
			
			return VideoBitrate;
		}

		@Override
		public String videoCodec() {
			
			return VideoCodec;
		}

		@Override
		public String videoFPS() {
			
			return VideoFPS;
		}

		@Override
		public String videoHeight() {

			return VideoHeight;
		}

		@Override
		public String videoKeyFPS() {

			return VideoKeyFPS;
		}

		@Override
		public String videoMaxBitrate() {

			return VideoMaxBitrate;
		}

		@Override
		public String videoPreset() {
			
			return VideoPreset;
		}

		@Override
		public String videoWidth() {
		
			return VideoWidth;
		}


		@Override
		public boolean videoCodecFound() {
			
			return !(VideoCodec==null||VideoCodec.isEmpty());
		}


		@Override
		public boolean videoPresetFound() {
			
			return !(VideoPreset==null||VideoPreset.isEmpty());
		}


		@Override
		public boolean videoWidthFound() {

			return !(VideoWidth==null||VideoWidth.isEmpty());
		}


		@Override
		public boolean videoHeightFound() {
			
			return !(VideoHeight==null||VideoHeight.isEmpty());
		}


		@Override
		public boolean videoAspectFound() {
			
			return !(VideoAspect==null||VideoAspect.isEmpty());
		}


		@Override
		public boolean videoBitrateFound() {
			
			return !(VideoBitrate==null||VideoBitrate.isEmpty());
		}


		@Override
		public boolean videoMaxBitrateFound() {
			
			return !(VideoMaxBitrate==null||VideoMaxBitrate.isEmpty());
		}


		@Override
		public boolean videoFPSFound() {
			
			return !(VideoFPS==null||VideoFPS.isEmpty());
		}


		@Override
		public boolean videoKeyFPSFound() {
			
			return !(VideoKeyFPS==null||VideoKeyFPS.isEmpty());
		}


		@Override
		public boolean audioCodecFound() {
			
			return !(AudioCodec==null||AudioCodec.isEmpty());
		}


		@Override
		public boolean audioChannelsFound() {
			
			return !(AudioChannels==null||AudioChannels.isEmpty());
		}


		@Override
		public boolean audioBitrateFound() {
			
			return !(AudioBitrate==null||AudioBitrate.isEmpty());
		}


		@Override
		public boolean audioSamplingFrequencyFound() {
			
			return !(AudioSamplingFrequency==null||AudioSamplingFrequency.isEmpty());
		}


		@Override
		public boolean outputExtensionFound() {
			
			return !(OutputExtension==null||OutputExtension.isEmpty());
		}
}
	

