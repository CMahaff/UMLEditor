package com.group3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import com.group3.data.DataManager;
import com.group3.ui.ClassBox;

public class Test {
	
	@org.junit.Test
	public void add() {
		DataManager dataManager = new DataManager();
		
		ClassBox classBox = new ClassBox("Test", null);
		String before = dataManager.toString();
		
		dataManager.addClassBoxData(classBox);
		assertFalse(before.equals(dataManager.toString()));
	}

	@org.junit.Test
	public void addThenRemove() {
		DataManager dataManager = new DataManager();
		
		ClassBox classBox = new ClassBox("Test", null);
		
		String before = dataManager.toString();
		
		int id = dataManager.addClassBoxData(classBox);
		dataManager.removeClassBoxData(id);
		assertTrue(before.equals(dataManager.toString()));
	}
	
	@org.junit.Test
	public void addTwoBoxes(){		
		DataManager dataManager = new DataManager();
		ClassBox classBox = new ClassBox("Test1", null);
		ClassBox classBox2 = new ClassBox("Test2", null);
		
		String before = dataManager.toString();
		
		dataManager.addClassBoxData(classBox);
		dataManager.addClassBoxData(classBox2);
		assertFalse(before.equals(dataManager.toString()));		
	}

	@org.junit.Test
	public void addTwoBoxesThenRemove(){
		
		DataManager dataManager = new DataManager();
		ClassBox classBox = new ClassBox("Test1", null);
		ClassBox classBox2 = new ClassBox("Test2", null);
		
		String before = dataManager.toString();
		
		int id1 = dataManager.addClassBoxData(classBox);
		int id2 = dataManager.addClassBoxData(classBox2);
		dataManager.removeClassBoxData(id1);
		dataManager.removeClassBoxData(id2);
		
		assertTrue(before.equals(dataManager.toString()));
	}
	
	
	@org.junit.Test
	public void addThenMoveAndResizeClassBox(){
		DataManager dataManager = new DataManager();
		ClassBox classBox = new ClassBox("Test", null);
		
		dataManager.addClassBoxData(classBox);
		String beforeMove = dataManager.toString();

		classBox.setBounds(classBox.getX()+10, classBox.getY()+10, classBox.getWidth()+20, classBox.getHeight()+20);
		dataManager.addClassBoxData(classBox);
		String afterMove = dataManager.toString();
		
		assertFalse(beforeMove.equals(afterMove));
		
	}
	
	@org.junit.Test
	public void savingAndOpen(){
		File saveFile = new File("test.uml");		
			
		DataManager dataManagerOne = new DataManager();
		ClassBox classBox = new ClassBox("Test1", null);
		ClassBox classBox2 = new ClassBox("Test2", null);
		
		dataManagerOne.addClassBoxData(classBox);
		dataManagerOne.addClassBoxData(classBox2);
		
		String dataManagerOneString = dataManagerOne.toString();
		
		dataManagerOne.saveModel(saveFile);
		
		DataManager dataManagerTwo = new DataManager();
				
		String[] classBoxes = dataManagerTwo.loadModel(saveFile);
		for(int i = 0; i < classBoxes.length - 1; ++i) {
			ClassBox classBox1 = new ClassBox("", null);		
			int id = dataManagerTwo.addClassBoxData(classBox1);
			classBox1.setId(id);
			
		}	
		
		String dataManagerTwoString = dataManagerTwo.toString();
	
		assertTrue(dataManagerOneString.equals(dataManagerTwoString));		
		
	}
	
}