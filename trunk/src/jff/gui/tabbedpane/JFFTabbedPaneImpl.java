package jff.gui.tabbedpane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import jff.translation.JFFStrings;

/**
 * An implementation of JFFTabbedPane with 4 subpanels, checkboxes, spinner and combobox connected to their relative elements in the Options
 *   
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
@SuppressWarnings("serial")
public class JFFTabbedPaneImpl extends JTabbedPane implements JFFTabbedPane {

	/**
	 * the Options to display and modify
	 */
	private FFOptions Options;
	
	/**
	 * the translation strings
	 */
	private JFFStrings S;
	
	/**
	 * a label to display the output path 
	 */
	private JLabel OutputPath;
	
	/**
	 * a button to open a file chooser and change the output path 
	 */
	private JButton OutputChanger;
	
	/**
	 * a combo box to select the output format 
	 */
	private JComboBox Format;
	
	/**
	 * a spinner to select the number of threads 
	 */
	private JSpinner Threads;
	
	/**
	 * the model of the spinner (goes from 0 to 99)
	 */
	private SpinnerModel Model;
	
	
	/**
	 * a checkbox representing the option two passes
	 */
	private JCheckBox TwoPasses;
	
	/**
	 * a checkbox representing the option pads
	 */
	private JCheckBox Pads;
	
	/**
	 * a checkbox representing the option small
	 */
	private JCheckBox Small;
	
	/**
	 * a checkbox representing the option debug
	 */
	private JCheckBox Debug;
	
	
	
	/**
	 * Create the Tabbed Pane and connect it with the appropriate listeners that will modify the Options
	 * 
	 * @param items the items in the applications (only items.Options and items.S will be used)
	 */
	public JFFTabbedPaneImpl(JFFBundledItems items){
		super();
		
		Options=items.Options;
		S=items.S;
		
		
		//destination folder panel
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
		
		

		//output format panel
		JPanel p2=new JPanel();
		
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		
		
		Vector<String> v=new Vector<String>();
		
		File formatDir = new File("format");
		for (int i=0;i<formatDir.list().length;i++)
			if (!(formatDir.list()[i].startsWith("."))&&!formatDir.list()[i].equals("template.txt"))v.add(formatDir.list()[i].split("\\.")[0]);
		
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
		

		//thread panel
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));

		
		Model =new SpinnerNumberModel(Options.threads(),1,99,1);
		
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
		

		//options panel
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
