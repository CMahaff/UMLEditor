package com.group3.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		if(e.getActionCommand().equals("Exit")) {
			this.viewManager.doExit();
		} else if(e.getActionCommand().equals("Class Box")) {
			this.viewManager.addClassBox();
		} else {
			System.out.println(e.getActionCommand());
			//TODO: Handle additional actions
		}
		
	}

}
