package com.group3.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JPopupMenu;

import com.group3.ui.UMLScene;

public class RelationshipMouseEventListener extends MouseAdapter {
	
	JPopupMenu popup;
	UMLScene window;
	
	/**
	 * A MouseEventListener that initiates the right click menu
	 * @param popupMenu  the menu being generated
	 */
    public RelationshipMouseEventListener(JPopupMenu popupMenu, UMLScene umlScene) {
        popup = popupMenu;
        window = umlScene;
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
        //showPopup(e);
    }
	/**
	 * 
	 * @param e MouseEvent that initiates the popup menu
	 */
    private void showPopup(MouseEvent e) {
    	
    	if(window.selectEditableRelationship(e.getX(), e.getY())) {
    		popup.show(e.getComponent(),
    				e.getX(), e.getY());
    	}
    	
    }
  
}
