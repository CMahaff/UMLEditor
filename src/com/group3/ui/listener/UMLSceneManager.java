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
	
	public void dragFrame(JComponent boxComp, int newX, int newY) {
		super.dragFrame(boxComp, newX, newY);
		ClassBox classBox = (ClassBox)boxComp;
		this.dataRef.updateClassBoxData(classBox);
		
		this.sceneRef.repaint();
	}
	
	public void setDataRef(DataManager dataRef, UMLScene sceneRef) {
		this.dataRef = dataRef;
		this.sceneRef = sceneRef;
	}

}
