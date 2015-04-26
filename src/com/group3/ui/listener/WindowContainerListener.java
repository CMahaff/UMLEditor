package com.group3.ui.listener;

import java.awt.KeyEventDispatcher;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.group3.ui.ViewManager;

/**
 * @author Connor Mahaffey
 */
public class WindowContainerListener implements WindowListener, KeyEventDispatcher, ComponentListener {
	
	private ViewManager viewManager;
	
	/**
	 * Listens for events in the main window
	 * 
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
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	/**
	 * End the relationship selection event if ESC is pressed
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.viewManager.endRelationshipSelection();
		}
		return false;
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	/**
	 * If the main window is resized, handle expanding the inner
	 * drawable frame, so that it will become scrollable
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		this.viewManager.resizeToFit();
	}

	@Override
	public void componentShown(ComponentEvent e) {}
}
