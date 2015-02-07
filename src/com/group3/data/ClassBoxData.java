package com.group3.data;

import java.util.ArrayList;

/**
 * @author Connor Mahaffey
 */
public class ClassBoxData {
	
	private int posX, posY, width, height;
	private ArrayList<String> boxes;
	
	/**
	 * Hold Class Box data for saving, reloading, etc.
	 * TODO: Have ViewManager update at internals.
	 * 
	 * @param id unique id representing this object
	 * @param posX x position of the ClassBox view
	 * @param posY y position of the ClassBox view
	 * @param width width of the ClassBox view
	 * @param height height of the ClassBox view
	 */
	public ClassBoxData(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.boxes = new ArrayList<String>();
	}
	
	/**
	 * Add another box to the Class Box
	 */
	public void addBox() {
		this.boxes.add("");
	}
	
	/**
	 * @return height of the Class Box
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * @return width of the Class Box
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @return x position
	 */
	public int getX() {
		return this.posX;
	}
	
	/**
	 * @return y position
	 */
	public int getY() {
		return this.posY;
	}
	
	/**
	 * Update position of a given Class Box
	 * @param x x position
	 * @param y y position
	 */
	public void updatePosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	/**
	 * Update the size of a given Class Box
	 * @param width width of the box
	 * @param height height of the box
	 */
	public void updateSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Update the contents of a given box (with what was typed in the UI)
	 * @param index index to change
	 * @param content content to set it to
	 */
	public void setBox(int index, String content) {
		this.boxes.set(index, content);
	}
	
	public String toString() {
		String output = "Pos X: " + this.posX + ", Pos Y: " + this.posY +
						", Width: " + this.width + ", Height: " + this.height + "\n";
		for(String s : this.boxes) {
			output += s;
			output += "\n----------------\n";
		}
		
		return output;
	}
}
