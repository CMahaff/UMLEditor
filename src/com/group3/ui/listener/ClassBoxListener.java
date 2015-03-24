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
			//TODO: Handle additional actions
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	/**
 	* check this.. 
 	*/
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
	public void focusGained(FocusEvent e) {
		if(this.classBox.isSelectable()) {
			this.viewManager.getRelationshipSelectionManager().addSelection(classBox);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.viewManager.getDataManager().updateClassBoxData(this.classBox.getId(), 
														 	 this.classBox.getX(), this.classBox.getY(), 
														 	 this.classBox.getWidth(), this.classBox.getHeight(), 
														 	 this.classBox.getArrayRepresentation());
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		this.classBox.setForeground(Color.BLACK);
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		this.classBox.setForeground(Color.GRAY);
	}
	
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
