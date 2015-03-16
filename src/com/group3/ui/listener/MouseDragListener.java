package com.group3.ui.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.group3.ui.ClassBox;


/**
 * 
 * @author Dave Mengel
 *
 */

public class MouseDragListener extends MouseAdapter implements MouseListener, MouseMotionListener {

	ClassBox box;
	Point framePoint;
	int framePosX, framePosY;

	int posX, posY;
	int screenX,screenY;
	
	/**
	 * A MouseListener and Mouse MotionListener that allows a classBox to have dragging capabilities 
	 * 
	 * @param classBox the current classBox that is being dragged around the UMLScene
	 */
	
	public MouseDragListener(ClassBox classBox) {
		box = classBox;
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 *  @param e the MouseEvent that is used to find the x and y coordinates 
	 *  of the location of the UMLScene and 
	 *  the location of the cursor with respect to a classBox
	 *  
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
		//UMLScene Location
		framePoint = box.getParent().getLocationOnScreen();

		framePosX = framePoint.x;
		framePosY = framePoint.y;
		
		posX = e.getX();
		posY = e.getY();


	}
	/**
	 *  @param e the MouseEvent that is used to move a classBox location within the UMLScene 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
		screenX = e.getXOnScreen();
		screenY = e.getYOnScreen();
		
		//parenthesis' around screenX/Y - framePosX/Y are unnecessary. But are used to clarify the offset necessary 
		//between the cursor location from the system screen and from the UMLScene Container.
		box.setLocation ((screenX - framePosX) - posX, (screenY - framePosY) - posY);

	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}


}
