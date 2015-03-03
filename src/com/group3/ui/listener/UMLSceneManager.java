package com.group3.ui.listener;

import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;

import com.group3.data.DataManager;
import com.group3.ui.ClassBox;
import com.group3.ui.UMLScene;

@SuppressWarnings("serial")
public class UMLSceneManager extends DefaultDesktopManager {
	
	private DataManager dataRef;
	private UMLScene sceneRef;
	
	public UMLSceneManager(DataManager dataRef, UMLScene sceneRef) {
		super();
		this.dataRef = dataRef;
		this.sceneRef = sceneRef;
	}
	
	public void dragFrame(JComponent boxComp, int newX, int newY) {
		super.dragFrame(boxComp, newX, newY);
		ClassBox classBox = (ClassBox)boxComp;
		this.dataRef.updateClassBoxDataWindow(classBox.getId(), classBox.getX(), classBox.getY(), 
											  classBox.getWidth(), classBox.getHeight());
		
		this.sceneRef.repaint();
	}
}
