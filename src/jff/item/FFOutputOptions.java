package jff.item;

import java.io.Serializable;

public interface FFOutputOptions extends Serializable {

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
	
	public boolean videoCodecFound();
	public boolean videoPresetFound();
	public boolean videoWidthFound();
	public boolean videoHeightFound();
	public boolean videoAspectFound();
	public boolean videoBitrateFound();
	public boolean videoMaxBitrateFound();
	public boolean videoFPSFound();
	public boolean videoKeyFPSFound();
	public boolean audioCodecFound();
	public boolean audioChannelsFound();
	public boolean audioBitrateFound();
	public boolean audioSamplingFrequencyFound();
	public boolean outputExtensionFound();
	
}
