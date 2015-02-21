package com.group3.ui.listener;

import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;

import com.group3.data.DataManager;
import com.group3.ui.ClassBox;

@SuppressWarnings("serial")
public class UMLSceneManager extends DefaultDesktopManager {
	
	private DataManager dataRef;
	
	public void dragFrame(JComponent boxComp, int newX, int newY) {
		super.dragFrame(boxComp, newX, newY);
		ClassBox classBox = (ClassBox)boxComp;
		this.dataRef.updateClassBoxData(classBox);
	}
	
	public void setDataRef(DataManager dataRef) {
		this.dataRef = dataRef;
	}

}
