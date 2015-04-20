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
	
	//private DataManager dataRef;
	
	/**
	 * 
	 * @param dataRef
	 */
	public UMLSceneManager(DataManager dataRef) {
		super();
		//this.dataRef = dataRef;
	}
	
	public void dragFrame(JComponent boxComp, int newX, int newY) {
		super.dragFrame(boxComp, newX, newY);
		/* TODO: Remove?
		ClassBox classBox = (ClassBox)boxComp;
		this.dataRef.updateClassBoxDataWindow(classBox.getId(), classBox.getX(), classBox.getY(), 
											  classBox.getWidth(), classBox.getHeight());
											  */
	}
	
	public void endDraggingFrame(JComponent boxComp) {
		//TODO: put undo hook here?
	}
	
	public void endResizingFrame(JComponent boxComp) {
		//TODO: put undo hook here?
	}
}
