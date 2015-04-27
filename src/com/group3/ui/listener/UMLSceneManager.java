package com.group3.ui.listener;

import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;

import com.group3.data.DataManager;

/**
 * 
 * @author Connor Mahaffey
 *
 */
@SuppressWarnings("serial")
public class UMLSceneManager extends DefaultDesktopManager {
	
	/**
	 * Placeholder class for undo operations.
	 * 
	 * @param dataRef a reference to the Data Manager
	 */
	public UMLSceneManager(DataManager dataRef) {
		super();
		//this.dataRef = dataRef;
	}
	
	public void endDraggingFrame(JComponent boxComp) {
		//TODO: put undo hook here?
	}
	
	public void endResizingFrame(JComponent boxComp) {
		//TODO: put undo hook here?
	}
}
