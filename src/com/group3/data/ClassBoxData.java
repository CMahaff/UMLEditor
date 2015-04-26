package com.group3.data;


/**
 * @author Connor Mahaffey
 *         Brady Landis
 */
public class ClassBoxData {
	
	private int posX, posY, width, height;
	private String[] boxes;
	private String colorCode;
	
	/**
	 * Hold Class Box data for saving, reloading, etc.
	 * 
	 * @param id unique id representing this object
	 * @param posX x position of the ClassBox view
	 * @param posY y position of the ClassBox view
	 * @param width width of the ClassBox view
	 * @param height height of the ClassBox view
	 * @param boxes array of strings representing the text sections of a class box
	 * @param bgColor the integer representation of the background color of the Class Box view 
	 */
	public ClassBoxData(int posX, int posY, int width, int height, String[] boxes, String bgColorCode) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.boxes = boxes;
		this.colorCode = bgColorCode;
	}
	
	/**
	 * Returns the sections of the Class Box as a String array, where index 0 represents
	 * the title of the window, and all subsequent entries represent different
	 * sections of the Class Box.
	 * 
	 * @return a String array representing the text in different sections of the Class Box
	 */
	public String[] getBoxes() {
		return this.boxes;
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
	 * @return the background color string integer representation
	 */
	public String getBackgroundColorCode() {
		return this.colorCode;
	}
	
	/**
	 * @param data a string array representing the sections of the Class Box
	 */
	public void setBoxes(String[] data) {
		this.boxes = data;
	}
	
	/**
	 * @param height the new height of the Class Box
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @param width the new width of the Class Box
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * @param x the new x position of the Class Box
	 */
	public void setX(int x) {
		this.posX = x;
	}
	
	/**
	 * @param y the new y position of the Class Box
	 */
	public void setY(int y) {
		this.posY = y;
	}
	
	/**
	 * @param color the new background color code for the view
	 */
	public void setBackgroundColorCode(String bgColorCode) {
		this.colorCode = bgColorCode;
	}
	
	/**
	 * Prints some debugging information about the Class Box
	 */
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
