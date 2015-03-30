package com.group3.ui.listener;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.group3.ui.ViewManager;

/**
 * @author Connor Mahaffey
 */
public class WindowContainerListener implements WindowListener, KeyEventDispatcher {
	
	private ViewManager viewManager;
	
	/**
	 * Listens for a window close message, and tells the view manager to do a save exit.
	 * @param viewManager View Manager reference to call
	 */
	public WindowContainerListener(ViewManager viewManager) {
		this.viewManager = viewManager;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		this.viewManager.doExit();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.viewManager.endRelationshipSelection();
		}
		return false;
	}
}
