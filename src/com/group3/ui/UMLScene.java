package com.group3.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JDesktopPane;

import com.group3.data.ClassBoxData;
import com.group3.data.DataManager;
import com.group3.data.RelationshipData;
import com.group3.ui.drawing.Shape;


/**
 * @author Connor Mahaffey
 */
@SuppressWarnings("serial")
public class UMLScene extends JDesktopPane {
	
	private static final int TRIANGLE = 0;
	
	private DataManager dataManager;
	private Shape[] shapes = new Shape[5];
	
	public UMLScene(DataManager dataManager) {
		this.dataManager = dataManager;
		
		Shape triangle = new Shape();
		triangle.addPoint(10, 0);
		triangle.addPoint(0, 20);
		triangle.addPoint(20, 20);
		this.shapes[UMLScene.TRIANGLE] = triangle;
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
		
		/*
		for(ClassBoxData entry : this.dataManager.getClassBoxData().values()) {
			
			int posX = entry.getX() + entry.getWidth() / 2 - 10;
			int posY = entry.getY() + entry.getHeight();
			
			//g.drawRect(posX, posY, 20, 20);
			 
		}
		*/
		
		for(RelationshipData entry : this.dataManager.getRelationshipData().values()) {
			Shape shape;
			switch(entry.getType()) {
				case RelationshipData.BASIC:
					shape = this.shapes[UMLScene.TRIANGLE];
					calculateShapeOffset(shape, entry.getSourceClassBox());
					g.drawPolygon(shape);
					
					calculateShapeOffset(shape, entry.getDestinationClassBox());
					g.drawPolygon(shape);
					break;
				default:
					break;
			}
		}
	}
	
	/**
	 * Calculates where the shape should be drawn relative to the class box
	 * @param shape the geometry to modify
	 * @param classBox the classbox to draw it under
	 */
	private void calculateShapeOffset(Shape shape, ClassBoxData classBox) {
		int x = classBox.getX() + classBox.getWidth() / 2 - 10;
		int y = classBox.getY() + classBox.getHeight();
		
		shape.translate(x, y);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}
}
