package com.group3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.group3.data.DataManager;

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
	
	
	
}