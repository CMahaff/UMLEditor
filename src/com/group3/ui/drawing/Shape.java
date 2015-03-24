package com.group3.ui.drawing;

import java.awt.Polygon;

@SuppressWarnings("serial")
public class Shape extends Polygon {
	
	private int oldX, oldY;
	
	/**
	 * Instantiate a shape.
	 * @param numPoints the number of x and y coordinates this shape will have, minimum of 2
	 */
	public Shape() {
		super();
		
		oldX = 0;
		oldY = 0;
	}
	
	/**
	 * Sets the exact position of this shape
	 * @param x the x position to set this shape
	 * @param y the y position to set this shape
	 */
	public void translate(int x, int y) {
		int xDiff = x - this.oldX;
		int yDiff = y - this.oldY;
		this.oldX = x;
		this.oldY = y;
		
		super.translate(xDiff, yDiff);
	}
	
	public void switchXAndY() {
		int tempPoints[] = this.xpoints;
		this.xpoints = this.ypoints;
		this.ypoints = tempPoints;
	}
	
}
