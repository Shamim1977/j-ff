package jff.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import jff.gui.JFFMainFrameImpl;
import jff.gui.JFFMainFrameImpl.JFFBundledItems;
import jff.gui.tabbedpane.JFFTabbedPane;
import jff.gui.table.JFFTable;
import jff.gui.table.JFFTableImpl;
import jff.gui.table.JFFTableModel;
import jff.item.FFOptions;
import jff.item.JFFGroupSelectableVideoFile;
import jff.item.JFFSelectableVideoFile;
import jff.item.JFFSelectableVideoFileImpl;
import jff.item.VideoFile;
import jff.item.VideoFileImpl;

@SuppressWarnings("serial")
public class JFFSelectNone extends AbstractAction {

	private JFFTable Table;
	
	public JFFSelectNone(JFFTable t, JFFBundledItems items){
		super(items.S.selectNone(),new ImageIcon("img/selectnone.png"));
		
		Table=t;
		
		
		putValue(SHORT_DESCRIPTION, items.S.selectNoneDescription());  // Will appear as tooltip text.
        
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Table.deselectAllFiles();
	}

}