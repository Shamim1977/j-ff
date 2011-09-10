package jff.item;

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
		
		public FFOutputOptionsImpl(String format, boolean isVerbose){
			
			Format=format;
			
			if (format=="IPOD"){// set IPOD data
				
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
				AudioCodec="libfaac";
				AudioChannels="2";
				AudioBitrate="128";
				AudioSamplingFrequency="44100";
				OutputExtension="mp4";
			
			}
			
			if (isVerbose)
				System.out.println(toString());
		}
		
		@Override
		public boolean is(String format){
			
			return Format==format;
		}
		
		@Override
		public String toString() {
			return "FFOutputOptionsImpl [Format=" + Format + ", VideoCodec="
					+ VideoCodec + ", VideoPreset=" + VideoPreset
					+ ", VideoWidth=" + VideoWidth + ", VideoHeight="
					+ VideoHeight + ", VideoAspect=" + VideoAspect
					+ ", VideoBitrate=" + VideoBitrate
					+ ", VideoMaxBitrate=" + VideoMaxBitrate
					+ ", VideoFPS=" + VideoFPS + ", VideoKeyFPS="
					+ VideoKeyFPS + ", AudioCodec=" + AudioCodec
					+ ", AudioChannels=" + AudioChannels
					+ ", AudioBitrate=" + AudioBitrate
					+ ", AudioSamplingFrequency=" + AudioSamplingFrequency
					+ ", OutputExtension=" + OutputExtension + "]";
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
}
	

