package com.group3.ui;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.group3.ui.listener.ClassBoxListener;
import com.group3.ui.listener.PopupListener;


/**
 * @author Connor Mahaffey
 * 		   David Mengel
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
	public ClassBox(String title, ViewManager viewRef) {
		super(title, true); //JInternalFrame title, resizability
		
		createPopupMenu(viewRef);
	}
	
	private void createPopupMenu(ViewManager viewRef) {
        JMenuItem menuItem;
        ClassBoxListener classBoxListener = new ClassBoxListener(viewRef, this);
        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Delete Class Box");
        menuItem.addActionListener(classBoxListener);
        menuItem.addComponentListener(classBoxListener);
        popup.add(menuItem);
 
        MouseListener popupListener = new PopupListener(popup);
        this.addMouseListener(popupListener);
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
	
	/**
	 * @return the unique id that ties this object to its data member
	 */
	public int getId() {
		return this.id;
	}

}
