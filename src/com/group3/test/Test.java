package com.group3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.group3.data.DataManager;
import com.group3.ui.ClassBox;

public class Test {
	
	@org.junit.Test
	public void add() {
		DataManager dataManager = new DataManager();
		
		ClassBox classBox = new ClassBox("Test", null);
		
		String before = dataManager.toString();
		
		int id = dataManager.addClassBoxData(classBox);
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

}