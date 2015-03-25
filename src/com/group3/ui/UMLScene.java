package com.group3.ui;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JDesktopPane;

import com.group3.data.ClassBoxData;
import com.group3.data.DataManager;
import com.group3.data.RelationshipData;


/**
 * @author Connor Mahaffey
 */
@SuppressWarnings("serial")
public class UMLScene extends JDesktopPane {
	
	private static final int HEIGHT = 30;
	private static final int LINE = 0, TRIANGLE = 1;
	
	private DataManager dataManager;
	private Polygon[] polygons = new Polygon[5];
	
	public UMLScene(DataManager dataManager) {
		this.dataManager = dataManager;
		
		Polygon line = new Polygon();
		line.addPoint(0, 0);
		line.addPoint(0, 30);
		this.polygons[UMLScene.LINE] = line;
		
		Polygon triangle = new Polygon();
		triangle.addPoint(10, 0);
		triangle.addPoint(0, 20);
		triangle.addPoint(9, 20);
		triangle.addPoint(9, 30);
		triangle.addPoint(11, 30);
		triangle.addPoint(11, 20);
		triangle.addPoint(20, 20);	
		this.polygons[UMLScene.TRIANGLE] = triangle;
		
		
	}
	
	/**
	 * 
	 * Repaint will be called on this object whenever Class Boxes are moved, resized, etc.
	 * 
	 * Every time, the background must be cleared and the arrows must be redrawn
	 * at their new position.
	 * 
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(2));
		
		for(RelationshipData entry : this.dataManager.getRelationshipData().values()) {
			switch(entry.getType()) {
				case RelationshipData.BASIC:
					drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), 0, false);
					drawPolygon(g2d, polygons[UMLScene.TRIANGLE], entry.getDestinationClassBox(), 0, true);
					drawConnection(g2d, entry.getSourceClassBox(), entry.getDestinationClassBox());
					break;
				default:
					break;
			}
		}
	}
	
	/**
	 * Draws a polygon under a given ClassBox with a given rotation
	 * @param g the Graphics2D object to use
	 * @param polygon the geometry to use
	 * @param classBox the Class Box to draw it relative to
	 * @param rotation the amount to rotate the geometry in degrees
	 * @param fill whether the shape should be filled
	 */
	private void drawPolygon(Graphics2D g, Polygon polygon, ClassBoxData classBox, int rotation, boolean fill) {
		int x = classBox.getX() + classBox.getWidth() / 2 - 10;
		int y = classBox.getY() + classBox.getHeight() + polygon.getBounds().height / 2;
		
		//save our old settings to revert when we are done rotating, etc.
		//otherwise ClassBoxes will be upside-down and other nonsense
		AffineTransform old = g.getTransform();
		
		//create a new transform object
		//the following steps basically occur in reverse order -  Graphics2D is weird like that
		AffineTransform at = new AffineTransform();
		//translate to our final position
        at.translate(x, y);
        //rotate our object about its center (see code below)
        at.rotate(Math.toRadians(rotation));
        //translate our geometry to its center
        at.translate(-polygon.getBounds().width / 2, -polygon.getBounds().height / 2);
        //apply our transformation to our graphics object
        g.setTransform(at);
        //draw our transformed polygon
        if(fill) {
        	g.fillPolygon(polygon);
        } else {
        	g.drawPolygon(polygon);
        }
        
        //Change our transform back to normal
        g.setTransform(old);
	}
	
	/**
	 * Draws the connecting line between two Class Boxes
	 * @param g the Graphics2D object to draw the line with
	 * @param c1 one of the class boxes to draw under
	 * @param c2 the other class box to draw under
	 */
	private void drawConnection(Graphics2D g, ClassBoxData c1, ClassBoxData c2) {
		int x1 = c1.getX() + c1.getWidth() / 2 - 10;
		int y1 = c1.getY() + c1.getHeight() + HEIGHT;
		
		int x2 = c2.getX() + c2.getWidth() / 2 - 10;
		int y2 = c2.getY() + c2.getHeight() + HEIGHT;
		
		g.drawLine(x1, y1, x2, y2);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}
}
