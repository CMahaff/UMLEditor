package com.group3.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JDesktopPane;

import com.group3.data.ClassBoxData;
import com.group3.data.DataManager;


/**
 * @author Connor Mahaffey
 */
@SuppressWarnings("serial")
public class UMLScene extends JDesktopPane {
	
	private DataManager dataManager;
	
	public UMLScene(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	/**
	 * 
	 * Repaint will be called on this object whenever Class Boxes are moved.
	 * 
	 * Every time, the background must be cleared and the arrows must be redrawn
	 * at their new position.
	 * 
	 * TODO: Draw actual arrows and connectors
	 * 
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		for(ClassBoxData entry : this.dataManager.getClassBoxData().values()) {
			int posX = entry.getX() + entry.getWidth() / 2 - 10;
			int posY = entry.getY() + entry.getHeight();
			
			g.drawRect(posX, posY, 20, 20);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}
}
