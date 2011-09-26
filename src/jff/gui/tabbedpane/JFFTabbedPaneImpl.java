package jff.gui.tabbedpane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jff.action.JFFChangeOutputPath;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.item.FFOptions;
import jff.item.JFFSelectableVideoFileImpl;
import jff.translation.JFFStrings;
import jff.utility.JFFTime;

@SuppressWarnings("serial")
public class JFFTabbedPaneImpl extends JTabbedPane implements JFFTabbedPane {

	private FFOptions Options;
	private JFFStrings S;
	
	private JLabel OutputPath;
	private JButton OutputChanger;
	private JComboBox Format;
	private JSpinner Threads;
	private SpinnerModel Model;
	private JCheckBox TwoPasses;
	private JCheckBox Pads;
	private JCheckBox Small;
	private JCheckBox Debug;
	
	
	
	public JFFTabbedPaneImpl(JFFBundledItems items){
		super();
		Options=items.Options;
		S=items.S;
		//getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		JPanel p=new JPanel();
		
		
		p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));

		OutputPath=new JLabel(Options.outputFolder().toString());
		
		p.add(Box.createRigidArea(new Dimension(30,1)));
		p.add(OutputPath);
		p.add(Box.createRigidArea(new Dimension(30,1)));
		OutputChanger=new JButton(new JFFChangeOutputPath(this.getParent(),Options,OutputPath));
		OutputChanger.setText(S.optionsChangeDestinationFolder());
		p.add(OutputChanger);
		
		addTab(S.optionsDestinationFolder(),null,p);
		
		JPanel p2=new JPanel();
		
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		
		
		Vector<String> v=new Vector<String>();
		
		File formatDir = new File("format");
		for (int i=0;i<formatDir.list().length;i++)
			if (!(formatDir.list()[i].startsWith("."))&&!formatDir.list()[i].equals("template.txt"))v.add(formatDir.list()[i].split("\\.")[0]);
		
		//v.add(Options.outputOptions().format());
		
		Format=new JComboBox(v);
		Format.setSelectedItem(Options.outputOptions().format());
		
		Format.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Options.setOutputFormat((String)Format.getSelectedItem());
				
			}});
		
		p2.add(Format);
		p2.add(Box.createRigidArea(new Dimension(30,1)));
		p2.add(new JLabel(S.optionsOutputFormatTag()));
		p2.add(Box.createHorizontalGlue());
		
		addTab(S.optionsOutputFormat(),null,p2);
		
		
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));

		
		Model =
	        new SpinnerNumberModel(Options.threads(),1,99,1);
		
		Threads=new JSpinner(Model);
		
		p3.add(Threads);
		p3.add(Box.createRigidArea(new Dimension(30,1)));
		p3.add(new JLabel(S.optionsThreadsTag()));
		p3.add(Box.createHorizontalGlue());
		
		Threads.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				Options.setThreads((Integer)Model.getValue());
				
			}});
		
		addTab(S.optionsHardware(),null,p3);
		
		
		JPanel p4=new JPanel();
		p4.setLayout(new BoxLayout(p4,BoxLayout.X_AXIS));
		
		TwoPasses=new JCheckBox(S.optionsTwoPassesTag());
		TwoPasses.setSelected(Options.twoPasses());
		p4.add(TwoPasses);
		
		TwoPasses.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				Options.setTwoPasses(TwoPasses.isSelected());
			}});

		Pads=new JCheckBox(S.optionsPadsTag());
		Pads.setSelected(Options.pads());
		p4.add(Pads);

		Pads.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				Options.setPads(Pads.isSelected());
			}});

		
		Small=new JCheckBox(S.optionsSmallTag());
		Small.setSelected(Options.smallFiles());
		p4.add(Small);
		
		Small.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				Options.setSmallFiles(Small.isSelected()); 
			}});

		
		Debug=new JCheckBox(S.optionsDebugTag());
		Debug.setSelected(Options.verbose());
		p4.add(Debug);

		Debug.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				Options.setVerbose(Debug.isSelected());
			}});

		addTab(S.optionsConvertOptions(),null,p4);
		
		
		p4.add(Box.createHorizontalGlue());
		

		
	}

	@Override
	public void reducePane(){
		setVisible(false);
		repaint();
	}

	@Override
	public void extendPane(){

		setVisible(true);
		repaint();
	}

	@Override
	public boolean isReduced() {
		
		return !isVisible();
	}
	
}
