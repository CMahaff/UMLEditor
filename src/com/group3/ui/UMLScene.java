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
	private static final int LINE = 0, TRIANGLE = 1, ARROW = 2, DIAMOND = 3;
	
	private DataManager dataManager;
	private Polygon[] polygons = new Polygon[4];
	
	public UMLScene(DataManager dataManager) {
		this.dataManager = dataManager;
		
		Polygon line = new Polygon();
		line.addPoint(0, 0);
		line.addPoint(0, 30);
		this.polygons[UMLScene.LINE] = line;
		
		Polygon triangle = new Polygon();
		triangle.addPoint(10, 0);
		triangle.addPoint(0, 20);
		triangle.addPoint(10, 20);
		triangle.addPoint(10, 30);
		triangle.addPoint(10, 20);
		triangle.addPoint(20, 20);	
		this.polygons[UMLScene.TRIANGLE] = triangle;
		
		Polygon arrow = new Polygon();
		arrow.addPoint(10, 0);
		arrow.addPoint(0, 17);
		arrow.addPoint(10, 0);
		arrow.addPoint(10, 30);
		arrow.addPoint(10, 0);
		arrow.addPoint(20, 17);	
		this.polygons[UMLScene.ARROW] = arrow;
		
		Polygon diamond = new Polygon();
		diamond.addPoint(7, 0);
		diamond.addPoint(0, 10);
		diamond.addPoint(7, 20);
		diamond.addPoint(7, 30);
		diamond.addPoint(7, 20);
		diamond.addPoint(14, 10);
		this.polygons[UMLScene.DIAMOND] = diamond;
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
					drawPolygon(g2d, polygons[UMLScene.LINE], entry.getDestinationClassBox(), 0, false);
					drawConnection(g2d, entry.getSourceClassBox(), entry.getDestinationClassBox());
					break;
				case RelationshipData.DEPENDENCY:
					//TODO: Add dashed line instead of solid line
					drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), 0, false);
					drawPolygon(g2d, polygons[UMLScene.ARROW], entry.getDestinationClassBox(), 0, false);
					drawConnection(g2d, entry.getSourceClassBox(), entry.getDestinationClassBox());
					break;
				case RelationshipData.AGGREGATION:
					drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), 0, false);
					drawPolygon(g2d, polygons[UMLScene.DIAMOND], entry.getDestinationClassBox(), 0, false);
					drawConnection(g2d, entry.getSourceClassBox(), entry.getDestinationClassBox());
					break;
				case RelationshipData.COMPOSITION:
					drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), 0, false);
					drawPolygon(g2d, polygons[UMLScene.DIAMOND], entry.getDestinationClassBox(), 0, true);
					drawConnection(g2d, entry.getSourceClassBox(), entry.getDestinationClassBox());
					break;
				case RelationshipData.GENERALIZATION:
					drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), 0, false);
					drawPolygon(g2d, polygons[UMLScene.TRIANGLE], entry.getDestinationClassBox(), 0, false);
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
		int x = classBox.getX() + classBox.getWidth() / 2;
		int y = classBox.getY() + classBox.getHeight() + polygon.getBounds().height / 2;
		
		//save our old settings to revert when we are done rotating, etc.
		//otherwise ClassBoxes will be upside-down and other nonsense
		AffineTransform old = g.getTransform();
		
		//get a copy of the transform to edit
		//the following steps basically occur in reverse order -  Graphics2D is weird like that
		AffineTransform transform = g.getTransform();
		//translate to our final position
        transform.translate(x, y);
        //rotate our object about its center (see code below)
        transform.rotate(Math.toRadians(rotation));
        //translate our geometry to its center
        transform.translate(-polygon.getBounds().width / 2, -polygon.getBounds().height / 2);
        //apply our transformation to our graphics object
        g.setTransform(transform);
        //draw our transformed polygon
        g.drawPolygon(polygon);
        if(fill) {
        	g.fillPolygon(polygon);
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
		int x1 = c1.getX() + c1.getWidth() / 2;
		int y1 = c1.getY() + c1.getHeight() + HEIGHT;
		
		int x2 = c2.getX() + c2.getWidth() / 2;
		int y2 = c2.getY() + c2.getHeight() + HEIGHT;
		
		g.drawLine(x1, y1, x2, y2);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}
}
