package com.group3.ui.drawing;

import java.awt.Graphics;

public class Shape {
	
	private int currentIndex, xPos, yPos, nPoints;
	private int[] xCoords, yCoords;
	
	/**
	 * Instantiate a shape.
	 * @param numPoints the number of x and y coordinates this shape will have, minimum of 2
	 */
	public Shape(int numPoints) {
		if(numPoints < 2) {
			numPoints = 2;
		}
		nPoints = numPoints;
		xCoords = new int[numPoints];
		yCoords = new int[numPoints];
		currentIndex = 0;
	}
	
	/**
	 * Add a coordinate pair to the list of points
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void addPoint(int x, int y) {
		if(currentIndex < xCoords.length) {
			xCoords[currentIndex] = x;
			yCoords[currentIndex] = y;
			
			currentIndex++;
		}
	}
	
	/**
	 * Set how this shape is offset from its geometry
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void setOffset(int x, int y) {
		int xDiff = x -this.xPos;
		int yDiff = y - this.yPos;
		this.xPos = x;
		this.yPos = y;
		
		for(int i = 0; i < xCoords.length; ++i) {
			xCoords[i] = xCoords[i] + xDiff;
		}
		
		for(int i = 0; i < yCoords.length; ++i) {
			yCoords[i] = yCoords[i] + yDiff;
		}
	}
	
	/**
	 * Draw the outline of this shape
	 * @param g graphics object to use
	 */
	public void drawOutline(Graphics g) {
		g.drawPolygon(xCoords, yCoords, nPoints);
	}
	
	/**
	 * Draw a filled version of this shape
	 * @param g graphics object to use
	 */
	public void drawFill(Graphics g) {
		g.fillPolygon(xCoords, yCoords, nPoints);
		System.out.println(xCoords[0]);
	}
	
	public String toString() {
		String output = "";
		
		for(int i = 0; i < xCoords.length; ++i) {
			output += xCoords[i] + " " + yCoords[i] + "\n";
		}
		
		return output;
	}

}
