package com.group3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import com.group3.data.DataManager;
import com.group3.ui.ClassBox;
import com.group3.ui.ViewManager;

public class Test {
	
	public static void main(String[] args){
		
		saving();
		
	}
	
	@org.junit.Test
	public void add() {
		DataManager dataManager = new DataManager();
		
		ClassBox classBox = new ClassBox("Test", null);
		
		
		String before = dataManager.toString();
		
		//This was "int id = dataManager.addClassBoxData(classBox);"
		
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
		ViewManager viewManager = new ViewManager();
		ClassBox classBox = new ClassBox("Test", null);
		
		dataManager.addClassBoxData(classBox);
		String beforeMove = dataManager.toString();

		classBox.setBounds(classBox.getX()+10, classBox.getY()+10, classBox.getWidth()+20, classBox.getHeight()+20);
		dataManager.addClassBoxData(classBox);
		String afterMove = dataManager.toString();
		
		assertFalse(beforeMove.equals(afterMove));
		
	}
	
	@org.junit.Test
	public static void saving(){
		File saveFile = null;
		DataManager dataManager = new DataManager();
		ClassBox classBox = new ClassBox("Test1", null);
		ClassBox classBox2 = new ClassBox("Test2", null);
		
		dataManager.addClassBoxData(classBox);
		dataManager.addClassBoxData(classBox2);
		
		//Error here
		//Says that saveFile is null, which is true but I am not sure why that is an issue
		//If the user is saving to a new file that file would also be null correct?
		dataManager.saveModel(saveFile);
		
		String fileString = saveFile.toString();
		
		assertTrue(fileString.equals(dataManager.toString()));
		
		
		
	}
	
	@org.junit.Test
	public void something2(){
		
		
	}
	
	@org.junit.Test
	public void something3(){
		
		
	}
	
	@org.junit.Test
	public void something4(){
		
		
	}
}