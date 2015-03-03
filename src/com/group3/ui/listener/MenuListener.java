package com.group3.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
		} else {
			System.out.println(e.getActionCommand());
			JOptionPane.showMessageDialog(null, "This component is still in development.", 
											    "Information", JOptionPane.INFORMATION_MESSAGE);
			//TODO: Handle additional actions
		}
		
	}

}


