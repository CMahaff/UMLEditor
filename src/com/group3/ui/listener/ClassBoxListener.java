package com.group3.ui.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.group3.data.DataManager;
import com.group3.ui.ClassBox;
import com.group3.ui.ViewManager;

/**
 * @author Connor Mahaffey
 * 		   Dave Mengel
 */
public class ClassBoxListener implements ActionListener, ComponentListener, FocusListener, InternalFrameListener {
	
	private ViewManager viewManager;
	private ClassBox classBox;
	
	/**
	 * @param viewManager reference to the View Manager to perform 
	 * actions when we receive an event
	 */
	public ClassBoxListener(ViewManager viewManager, ClassBox classBox) {
		this.viewManager = viewManager;
		this.classBox = classBox;
	}

	/**
	 * Handles the the right click pop-up menu on a class box.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Delete Class Box")) {
			this.viewManager.removeClassBox(this.classBox);
		} else if(e.getActionCommand().equals("Add Section")) {
			this.classBox.createTextBox();
		} else if(e.getActionCommand().equals("Remove Section")) {
			this.classBox.removeTextBox();
		} else {
			System.out.println(e.getActionCommand());
			JOptionPane.showMessageDialog(null, "This component is still in development.", 
											    "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	/**
	 * When a Class Box is moved, update the data manager with the new
	 * position and redraw the scene.
	 */
	@Override
	public void componentMoved(ComponentEvent e) {
		if(this.viewManager == null) {
			return;
		}
		
		DataManager dm = this.viewManager.getDataManager();
		dm.updateClassBoxDataWindow(this.classBox.getId(), 
									this.classBox.getX(), this.classBox.getY(), 
									this.classBox.getWidth(), this.classBox.getHeight());
		this.viewManager.repaintUML();
	}

	/**
	 * When a Class Box is resized, update the Data Manger
	 * and redrawn the scene.
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		if(this.viewManager == null) {
			return;
		}
		
		DataManager dm = this.viewManager.getDataManager();
		dm.updateClassBoxDataWindow(this.classBox.getId(), 
									this.classBox.getX(), this.classBox.getY(), 
									this.classBox.getWidth(), this.classBox.getHeight());
		this.viewManager.repaintUML();
	}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void focusGained(FocusEvent e) {}

	/**
	 * When focus on a Class Box is lost, update the Data Manager.
	 * 
	 * A loss of focus means that a Class Box could have been typed in before the
	 * switch happened, so the Data Manager should be updated with that Class Box's
	 * data in case it did change.
	 */
	@Override
	public void focusLost(FocusEvent e) {
		this.viewManager.getDataManager().updateClassBoxData(this.classBox.getId(), 
														 	 this.classBox.getX(), this.classBox.getY(), 
														 	 this.classBox.getWidth(), this.classBox.getHeight(), 
														 	 this.classBox.getArrayRepresentation());
	}

	/**
	 * If the Class Box is in the selection mode, make its border blue
	 * when it is clicked.
	 */
	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		if(this.classBox.isSelectable()) {
			this.classBox.setBorderColor(Color.BLUE);
			this.viewManager.getRelationshipSelectionManager().addSelection(classBox);
		}
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {}
	
	@Override
	public void internalFrameClosed(InternalFrameEvent e) {}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {}

}
