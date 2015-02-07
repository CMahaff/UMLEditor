package com.group3.ui;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

/**
 * @author Connor Mahaffey
 */
@SuppressWarnings("serial")
public class ClassBox extends JInternalFrame {
	
	private int id = 0;
	
	/**
	 * A view representing a Class Box
	 * 
	 * @param title title of the Class Box, listed in its window and its first field
	 * @param id integer id used as a reference to this boxes data component
	 */
	public ClassBox(String title) {
		super(title, true, true); //JInternalFrame title, resizability, closability
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
	/**
	 * Set the unique id of this Class Box, which is uses to communicate
	 * with the Data Manager.
	 * 
	 * This can only be set once, afterwards it will not change.
	 * 
	 * @param id unique id to give this object
	 */
	public void setId(int id) {
		if(this.id == 0 && id != 0) {
			this.id = id;
		}
	}

}
