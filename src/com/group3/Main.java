package com.group3;


import javax.swing.SwingUtilities;

import com.group3.data.DataManager;
import com.group3.ui.ViewManager;



/**
 * @author Connor Mahaffey
 */
public class Main {
	
	public static final String version = "0.1 Alpha";

	/**
	 * 
	 * Sets up the data and view manager classes and gives them a reference so
	 * they can "talk" to each other.
	 * 
	 * This is invoked in a runnable to make the repainting function work
	 * correctly.
	 * 
	 * @param args arguments passed to pain (accepts none)
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DataManager dataManager = new DataManager();
				ViewManager viewManager = new ViewManager();
				
				dataManager.setViewManager(viewManager);
				viewManager.setDataManager(dataManager);

			}
		});
	}

}
