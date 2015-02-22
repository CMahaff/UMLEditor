package com.group3Test.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.group3Test.ui.ViewManager;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Open")) {
			this.viewManager.open();
		} else if(e.getActionCommand().equals("Save")) {
			this.viewManager.save();
		} else if(e.getActionCommand().equals("Save As")) {
			this.viewManager.saveAs();
		} else if(e.getActionCommand().equals("Exit")) {
			this.viewManager.doExit();
		} else if(e.getActionCommand().equals("Class Box")) {
			this.viewManager.addClassBox();
		} else if(e.getActionCommand().equals("Test Class Box")) {
			this.viewManager.setAddClassBoxBool();
			this.viewManager.testClassBox();
			this.viewManager.testCases(1);
		}else if(e.getActionCommand().equals("Test Opening")) {
			this.viewManager.setFileOpenBool();
			this.viewManager.testOpenFile();
			this.viewManager.testCases(2);
		}else if(e.getActionCommand().equals("Test Saving")) {
			this.viewManager.setFileSaveBool();
			this.viewManager.testSaveFile("Save");
			this.viewManager.testCases(3);
		}else if(e.getActionCommand().equals("Test Save As")) {
			this.viewManager.setFileSaveAsBool();
			this.viewManager.testSaveFile("Save As");
			this.viewManager.testCases(4);
		} else if(e.getActionCommand().equals("Test Closing")) {
			this.viewManager.setFileExitBool();
			this.viewManager.testCloseProgram();
			this.viewManager.testCases(5);
		} else {
			
			System.out.println(e.getActionCommand());
			JOptionPane.showMessageDialog(null, "This component is still in development.", 
											    "Information", JOptionPane.INFORMATION_MESSAGE);
			//TODO: Handle additional actions
		}
		
	}

}


