package com.group3.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.group3.data.RelationshipData;
import com.group3.ui.CardinalityDialog;
import com.group3.ui.ViewManager;

/**
 * @author Connor Mahaffey
 */
public class MenuListener implements ActionListener {
	
	private ViewManager viewManager;
	
	/**
	 * @param viewManager reference to the View Manager to perform 
	 * actions when we receive an event
	 */
	public MenuListener(ViewManager viewManager) {
		this.viewManager = viewManager;
	}

	/**
	 * Handle all window menu options for the main frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals("New")) {
			this.viewManager.newUML();
		} else if(e.getActionCommand().equals("Open")) {
			this.viewManager.open();
		} else if(e.getActionCommand().equals("Save")) {
			this.viewManager.updateSelectedClassBoxChanges();
			this.viewManager.save();
		} else if(e.getActionCommand().equals("Save As")) {
			this.viewManager.updateSelectedClassBoxChanges();
			this.viewManager.saveAs();
		} else if(e.getActionCommand().equals("Exit")) {
			this.viewManager.doExit();
		} else if(e.getActionCommand().equals("Class Box")) {
			this.viewManager.addClassBox();
		} else if(e.getActionCommand().equals("Association")) {
			this.viewManager.startRelationshipSelection(RelationshipData.BASIC);
		} else if(e.getActionCommand().equals("Dependency")) {
			this.viewManager.startRelationshipSelection(RelationshipData.DEPENDENCY);
		} else if(e.getActionCommand().equals("Aggregation")) {
			this.viewManager.startRelationshipSelection(RelationshipData.AGGREGATION);
		} else if(e.getActionCommand().equals("Composition")) {
			this.viewManager.startRelationshipSelection(RelationshipData.COMPOSITION);
		} else if(e.getActionCommand().equals("Generalization")) {
			this.viewManager.startRelationshipSelection(RelationshipData.GENERALIZATION);
		} else if(e.getActionCommand().equals("Delete Relationship")) {
			this.viewManager.getUMLScene().removeEditableRelationship();
			this.viewManager.getUMLScene().repaint();
		} else if(e.getActionCommand().equals("Modify Cardinality")) {
			RelationshipData rel = this.viewManager.getUMLScene().getEditableRelationship();
			CardinalityDialog cardinalityDialog = new CardinalityDialog(rel, this.viewManager);
			cardinalityDialog.pack();
			cardinalityDialog.setVisible(true);
		} else if(e.getActionCommand().equals("Increase Window Size")) {
			this.viewManager.resizeWindow(100);
		} else if(e.getActionCommand().equals("Decrease Window Size")) {
			this.viewManager.resizeWindow(-100);
		} else {
			System.out.println("\"" + e.getActionCommand() + "\" is not implemented.");
			JOptionPane.showMessageDialog(null, "This component is still in development.", 
											    "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}


