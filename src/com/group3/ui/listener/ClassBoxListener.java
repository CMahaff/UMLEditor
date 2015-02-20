package com.group3.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.group3.ui.ClassBox;
import com.group3.ui.ViewManager;

/**
 * @author Connor Mahaffey
 * 		   David Mengel
 */
public class ClassBoxListener implements ActionListener, ComponentListener {
	
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

}
