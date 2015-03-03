package com.group3.test;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Assert;
import org.junit.Test;

import com.group3.data.DataManager;
import com.group3.ui.ViewManager;


public class RobotTest {
	
	public static final String version = "0.1 Alpha";
	
	/**
	 * 
	 * Sets up the data and view manager classes and gives them a reference so
	 * This is invoked in a runnable to make the repainting function work
	 * correctly.
	 * 
	 * @param args arguments passed to pain (accepts none)
	 
	@Test
	public void main() throws AWTException{
	
		new RobotTest();
		testKeyBinds
		
		
	}
*/
	
	@Test
	public void testKeyBinds(){
		DataManager dataManager = new DataManager();
		new ViewManager(dataManager);

		Robot robot;
		try {
			robot = new Robot();
			
			String before = dataManager.toString();
			createClassBox(48, robot);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			String after = dataManager.toString();
			Assert.assertTrue(before.equals(after));
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	//Methods to create the individual UI elements needed for testing.
	
	public static void closeTheProgram(Robot robot){
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_4);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_4);
	}
	
	public static void saveAFile(Robot robot, String save){
		
		if(save == "Save"){
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_2);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_2);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}else if(save == "Save As"){
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_3);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_3);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}
		
	}	
	public static void openAFile(Robot robot){
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}	
	public static void createClassBox(int i, Robot robot){
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		//robot.keyPress(i);
		//robot.keyRelease(i);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		//robot.keyPress(KeyEvent.VK_ENTER);
		//robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void testLaunch(Robot robot, ViewManager viewManager){
		
	}
	
	public static void testMenuBar(Robot robot, ViewManager viewManager){
		
	}
	
	public static void testClassBox(Robot robot, ViewManager viewManager){
		
	}

	
}