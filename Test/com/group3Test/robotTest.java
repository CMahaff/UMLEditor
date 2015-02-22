package com.group3Test;


import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import com.group3Test.data.DataManager;
import com.group3Test.ui.ClassBox;
import com.group3Test.ui.ViewManager; 

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class robotTest{

	
	public static final String version = "0.1 Alpha";
	
	/**
	 * 
	 * Sets up the data and view manager classes and gives them a reference so
	 * This is invoked in a runnable to make the repainting function work
	 * correctly.
	 * 
	 * @param args arguments passed to pain (accepts none)
	 */
	public static void main(String[] args) throws AWTException{
	
		new robotTest();
		
	}

	public robotTest() throws AWTException{
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DataManager dataManager = new DataManager();
				ViewManager viewManager = new ViewManager();
				
				dataManager.setViewManager(viewManager);
				viewManager.setDataManager(dataManager);
				
			}
		});		
	}
	
	
	public void testKeyBinds(ViewManager viewManager, Robot robot){
			
		createClassBox(48, robot);
		System.out.println(viewManager.getAddClassBoxBool());

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
		robot.keyPress(i);
		robot.keyRelease(i);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void testLaunch(Robot robot, ViewManager viewManager){
		
	}
	
	public static void testMenuBar(Robot robot, ViewManager viewManager){
		
	}
	
	public static void testClassBox(Robot robot, ViewManager viewManager){
		
	}

	
}
