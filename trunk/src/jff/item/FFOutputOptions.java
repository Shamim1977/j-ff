package jff.item;

public interface FFOutputOptions {

	public boolean is(String format);
	public String format();
	public String videoCodec();
	public String videoPreset();
	public String videoWidth();
	public String videoHeight();
	public String videoAspect();
	public String videoBitrate();
	public String videoMaxBitrate();
	public String videoFPS();
	public String videoKeyFPS();
	public String audioCodec();
	public String audioChannels();
	public String audioBitrate();
	public String audioSamplingFrequency();
	public String outputExtension(); 
	
}
