package com.group3.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import com.group3.ui.ClassBox;

public class MouseEventListener extends MouseAdapter {
	
	JPopupMenu popup;
	ClassBox box;
	JFrame frame;
/**
 * A MouseEventListener that initiates the right click menu
 * @param popupMenu  the menu being generated
 */
    public MouseEventListener(JPopupMenu popupMenu) {
        popup = popupMenu;
    }
/**
 * @param e The MouseEvent that happens when user right clicks. 
 * Systems signal popup menus differently, therefore must be called in both
 * pressed and released.
 */
    public void mousePressed(MouseEvent e) {
        showPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        showPopup(e);
    }
/**
 * 
 * @param e MouseEvent that initiates the popup menu
 */
    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
  
}
