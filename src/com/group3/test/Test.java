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
		
		String before = dataManager.toString();
		
		dataManager.addClassBoxData(0, 0, 0, 0, "Test");
		assertFalse(before.equals(dataManager.toString()));
	}

	@org.junit.Test
	public void addThenRemove() {
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int id = dataManager.addClassBoxData(0, 0, 0, 0, "");
		dataManager.removeClassBoxData(id);
		assertTrue(before.equals(dataManager.toString()));
	}
	
	@org.junit.Test
	public void addTwoBoxes(){		
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		dataManager.addClassBoxData(0, 0, 0, 0, "Test2");
		assertFalse(before.equals(dataManager.toString()));		
	}

	@org.junit.Test
	public void addTwoBoxesThenRemove(){
		
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int id1 = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int id2 = dataManager.addClassBoxData(0, 0, 0, 0, "Test2");
		dataManager.removeClassBoxData(id1);
		dataManager.removeClassBoxData(id2);
		
		assertTrue(before.equals(dataManager.toString()));
	}
	
	
	@org.junit.Test
	public void addThenMoveAndResizeClassBox(){
		DataManager dataManager = new DataManager();
		
		int id = dataManager.addClassBoxData(0, 0, 0, 0, "Test");
		String beforeMove = dataManager.toString();

		dataManager.updateClassBoxData(id, 20, 20, 50, 50, new String[]{"Test", "Bla"});
		String afterMove = dataManager.toString();
		
		assertFalse(beforeMove.equals(afterMove));
		
		dataManager.updateClassBoxData(id, 0, 0, 0, 0, new String[]{"Test", ""});
		String afterRevert = dataManager.toString();
		
		assertTrue(beforeMove.equals(afterRevert));
		
	}
	
	//Use both types of "addClassBoxData"
	@org.junit.Test
	public void savingAndOpen(){
		File saveFile = new File("test.uml");		
			
		DataManager dataManagerOne = new DataManager();
		
		dataManagerOne.addClassBoxData(0, 0, 0, 0, "Test1");
		dataManagerOne.addClassBoxData(0, 0, 0, 0, new String[]{"Test2", ""});
		
		String dataManagerOneString = dataManagerOne.toString();
		
		dataManagerOne.saveModel(saveFile);
		
		DataManager dataManagerTwo = new DataManager();
				
		String[] classBoxes = dataManagerTwo.loadModel(saveFile);
		for(int i = 0; i < classBoxes.length - 1; ++i) {
			ClassBox classBox = new ClassBox("", null);	
			classBox.loadFromTextData(classBoxes[i]);
			classBox.setSize(0, 0);
			int id = dataManagerTwo.addClassBoxData(classBox.getX(), classBox.getY(), 
													classBox.getWidth(), classBox.getHeight(),
													classBox.getArrayRepresentation());
			classBox.setId(id);
			
		}	
		
		String dataManagerTwoString = dataManagerTwo.toString();
		
		System.out.println(dataManagerOneString);
		System.out.println(dataManagerTwoString);
	
		assertTrue(dataManagerOneString.equals(dataManagerTwoString));
		
		saveFile.delete();
		
	}
	
}