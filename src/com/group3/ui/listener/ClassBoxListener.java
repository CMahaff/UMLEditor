package com.group3.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;

import com.group3.ui.ClassBox;
import com.group3.ui.ViewManager;

/**
 * @author Connor Mahaffey
 * 		   David Mengel
 */
public class ClassBoxListener implements ActionListener, ComponentListener, FocusListener {
	
	private ViewManager viewManager;
	private ClassBox classBox;
	
	/**
	 * @param viewManager reference to the View Manager to perform 
	 * actions when we receive an event
	 */
	public ClassBoxListener(ViewManager viewManager, ClassBox classBox) {
		this.viewManager = viewManager;
		this.classBox = classBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Delete Class Box")) {
			this.viewManager.removeClassBox(this.classBox);
		} else if(e.getActionCommand().equals("Add Section")) {
			this.classBox.createTextBox();
		} else if(e.getActionCommand().equals("Remove Section")) {
			this.classBox.removeTextBox();
		} else {
			System.out.println(e.getActionCommand());
			JOptionPane.showMessageDialog(null, "This component is still in development.", 
											    "Information", JOptionPane.INFORMATION_MESSAGE);
			//TODO: Handle additional actions
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {
		this.viewManager.getDataManager().updateClassBoxData(this.classBox);
	}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent arg0) {
		this.viewManager.getDataManager().updateClassBoxData(this.classBox);
		//TODO: On shortcut key press, focus not lost, so text not saved to latest version?
	}

}
