package com.group3.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.group3.data.RelationshipData;

@SuppressWarnings("serial")
public class CardinalityDialog extends JFrame implements WindowListener {
	
	private JTextArea sourceAmount, destAmount;
	private RelationshipData relationshipData;
	private ViewManager viewManager;
	
	/**
	 * Creates a window to type cardinality values into.
	 * 
	 * The main window is disabled until this window closes.
	 * 
	 * @param relationshipData the relationship to modify
	 * @param viewManager a reference to the view
	 */
	public CardinalityDialog(RelationshipData relationshipData, ViewManager viewManager) {
		super();
		this.relationshipData = relationshipData;
		this.viewManager = viewManager;
		
		JLabel source = new JLabel("Source Cardinality:");
		this.sourceAmount = new JTextArea();
		this.sourceAmount.setPreferredSize(new Dimension(290, 65));
		this.sourceAmount.setMargin(new Insets(5, 5, 5, 5));
		this.sourceAmount.setText(relationshipData.getAmountSource());
		
		JLabel dest = new JLabel("Destination Cardinality:");
		this.destAmount = new JTextArea();
		this.destAmount.setPreferredSize(new Dimension(290, 65));
		this.destAmount.setMargin(new Insets(5, 5, 5, 5));
		this.destAmount.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.destAmount.setText(relationshipData.getAmountDestination());
		
		
		this.setLayout(new FlowLayout());
		
		this.add(source);
		this.add(sourceAmount);
		this.add(dest);
		this.add(destAmount);
		
		this.setResizable(false);
		this.setPreferredSize(new Dimension(300, 200));
		this.setLocationRelativeTo(null);
		this.setTitle("Cardinality Editor");
		this.setAlwaysOnTop(true);
		
		this.viewManager.setEnabled(false);
		
		this.addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	/**
	 * Set the changes to the relationship data object, repaint, and re-enable
	 * the main window as this window closes.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		this.relationshipData.setAmountSource(this.sourceAmount.getText());
		this.relationshipData.setAmountDestination(this.destAmount.getText());
		
		this.viewManager.setEnabled(true);
		this.viewManager.getUMLScene().repaint();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {	}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

}
