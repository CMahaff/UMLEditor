package com.group3.ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
	private static final double ROT_0 = 0, 
								ROT_90 = Math.toRadians(90), 
								ROT_180 = Math.toRadians(180), 
								ROT_270 = Math.toRadians(270);
	
	private DataManager dataManager;
	private Polygon[] polygons = new Polygon[4];
	private boolean remove;
	private boolean removeTrue;
	private RelationshipData relationshipData;


	
	/**
	 * Draws the relationship connections to the scene
	 * 
	 * @param dataManager a reference to the Data Manager
	 */
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
		
		g2d.setBackground(this.getBackground());
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(1));
		
		for(RelationshipData entry : this.dataManager.getRelationshipData().values()) {
			double rotS = getRotation(entry.getSourceClassBox(), entry.getDestinationClassBox());
			double rotD = getRotation(entry.getDestinationClassBox(), entry.getSourceClassBox());
			int[] p1, p2;
			switch(entry.getType()) {
				case RelationshipData.BASIC:
					p1 = drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), rotS, false);
					p2 = drawPolygon(g2d, polygons[UMLScene.LINE], entry.getDestinationClassBox(), rotD, false);
					drawConnection(g2d, p1, p2, rotS, rotD);
					removeMenu(p1, p2, rotS, rotD, entry);

					break;
				case RelationshipData.DEPENDENCY:
					p1 = drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), rotS, false);
					p2 = drawPolygon(g2d, polygons[UMLScene.ARROW], entry.getDestinationClassBox(), rotD, false);
					drawDottedConnection(g2d, p1, p2, rotS, rotD);
					removeMenu(p1, p2, rotS, rotD, entry);

					break;
				case RelationshipData.AGGREGATION:
					p1 = drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), rotS, false);
					p2 = drawPolygon(g2d, polygons[UMLScene.DIAMOND], entry.getDestinationClassBox(), rotD, false);
					drawConnection(g2d, p1, p2, rotS, rotD);
					removeMenu(p1, p2, rotS, rotD, entry);

					break;
				case RelationshipData.COMPOSITION:
					p1 = drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), rotS, false);
					p2 = drawPolygon(g2d, polygons[UMLScene.DIAMOND], entry.getDestinationClassBox(), rotD, true);
					drawConnection(g2d, p1, p2, rotS, rotD);
					removeMenu(p1, p2, rotS, rotD, entry);
					
					break;
				case RelationshipData.GENERALIZATION:
					p1 = drawPolygon(g2d, polygons[UMLScene.LINE], entry.getSourceClassBox(), rotS, false);
					p2 = drawPolygon(g2d, polygons[UMLScene.TRIANGLE], entry.getDestinationClassBox(), rotD, false);
					drawConnection(g2d, p1, p2, rotS, rotD);
					removeMenu(p1, p2, rotS, rotD, entry);

					break;
				default:
					break;
			}
		}
		
	}
	/**
	 * returns the classBoxData for relationship removal
	 * @return returns an array of 2 classbox data
	 */
	public ClassBoxData[] getClassBoxData() {
			if(remove == true) {
				ClassBoxData[] boxes = new ClassBoxData[2];
		
				boxes[0] = relationshipData.getSourceClassBox();
				boxes[1] = relationshipData.getDestinationClassBox();
				return boxes;
			}	
			
			return null;
			
	}
/**
 * sets the private variable remove to the boolean value triggerPopup returns 
 * @param startCoord the starting coordinates of the drawn polygon
 * @param endCoord the ending coordinates of the drawn polygon
 * @param rotSource the rotation of the source polygon
 * @param rotDest the rotation of the destination polygon
 * @param entry the relationship data containing the data of the two classboxes
 */
	private void removeMenu(int[] startCoord, int[] endCoord, 
			double rotSource, double rotDest, RelationshipData entry) 
	{	
		
		if (removeTrue == true) {
			
			remove = triggerPopup(startCoord, endCoord,rotSource, rotDest);	
			relationshipData = entry;

		}
	}
	
	
	/**
	 * Draws a polygon next to a ClassBox with a given rotation
	 * 
	 * The rotation will affect where on the ClassBox it is placed
	 * 
	 * @param g the Graphics2D object to use
	 * @param polygon the geometry to use
	 * @param classBox the Class Box to draw it relative to
	 * @param rotation the amount to rotate the geometry in radians
	 * @param fill whether the shape should be filled
	 * 
	 * @return a size 2 integer array, representing the x and y coordinates where the polygon was actually drawn
	 */
	private int[] drawPolygon(Graphics2D g, Polygon polygon, ClassBoxData classBox, double rotation, boolean fill) {
		int x, y;
		
		if(rotation == UMLScene.ROT_90) {
			x = classBox.getX() - polygon.getBounds().height / 2;
			y = classBox.getY() + classBox.getHeight() / 2 + polygon.getBounds().width / 2;
		} else if(rotation == UMLScene.ROT_180) {
			x = classBox.getX() + classBox.getWidth() / 2;
			y = classBox.getY() - polygon.getBounds().height / 2;
		} else if(rotation == UMLScene.ROT_270) {
			x = classBox.getX() + classBox.getWidth() + polygon.getBounds().height / 2;
			y = classBox.getY() + classBox.getHeight() / 2 + polygon.getBounds().width / 2;
		} else {
			x = classBox.getX() + classBox.getWidth() / 2;
			y = classBox.getY() + classBox.getHeight() + polygon.getBounds().height / 2;
		}
		
		//save our old settings to revert when we are done rotating, etc.
		//otherwise ClassBoxes will be upside-down and other nonsense
		AffineTransform old = g.getTransform();
		
		//get a copy of the transform to edit
		//the following steps basically occur in reverse order -  Graphics2D is weird like that
		AffineTransform transform = g.getTransform();
		//translate to our final position
        transform.translate(x, y);
        //rotate our object about its center (see code below)
        transform.rotate(rotation);
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
        
        return new int[]{x, y};
	}
	
	/**
	 * Draws the dotted connecting line between 2 Class Boxes, 
	 * adjusting their location based on the starting rotation.
	 * 
	 * This class should be used in conjunction with drawPolygon.
	 * 
	 * @param g the Graphics2D object to use
	 * @param startCoord the starting coordinates of the drawn polygon
	 * @param endCoord the ending coordinates of the drawn polygon
	 * @param rotSource the rotation of the source polygon
	 * @param rotDest the rotation of the destination polygon
	 */
	private void drawConnection(Graphics2D g, int[] startCoord, int[] endCoord, double rotSource, double rotDest) {
		if(rotSource == UMLScene.ROT_90) {
			startCoord[0] -= HEIGHT / 2;
		} else if(rotSource == UMLScene.ROT_180) {
			startCoord[1] -= HEIGHT / 2;
		} else if(rotSource == UMLScene.ROT_270) {
			startCoord[0] += HEIGHT / 2;
		} else {
			startCoord[1] += HEIGHT / 2;
		}
		
		if(rotDest == UMLScene.ROT_90) {
			endCoord[0] -= HEIGHT / 2;
		} else if(rotDest == UMLScene.ROT_180) {
			endCoord[1] -= HEIGHT / 2;
		} else if(rotDest == UMLScene.ROT_270) {
			endCoord[0] += HEIGHT / 2;
		} else {
			endCoord[1] += HEIGHT / 2;
		}
		g.drawLine(startCoord[0], startCoord[1], endCoord[0], endCoord[1]);

	}
	

	
	/**
	 * Draws the connecting line between 2 Class Boxes, adjusting their location based on the starting rotation.
	 * 
	 * This class should be used in conjunction with drawPolygon.
	 * 
	 * @param g the Graphics2D object to use
	 * @param startCoord the starting coordinates of the drawn polygon
	 * @param endCoord the ending coordinates of the drawn polygon
	 * @param rotSource the rotation of the source polygon
	 * @param rotDest the rotation of the destination polygon
	 */
	private void drawDottedConnection(Graphics2D g, int[] startCoord, int[] endCoord, double rotSource, double rotDest) {
		if(rotSource == UMLScene.ROT_90) {
			startCoord[0] -= HEIGHT / 2;
		} else if(rotSource == UMLScene.ROT_180) {
			startCoord[1] -= HEIGHT / 2;
		} else if(rotSource == UMLScene.ROT_270) {
			startCoord[0] += HEIGHT / 2;
		} else {
			startCoord[1] += HEIGHT / 2;
		}
		
		if(rotDest == UMLScene.ROT_90) {
			endCoord[0] -= HEIGHT / 2;
		} else if(rotDest == UMLScene.ROT_180) {
			endCoord[1] -= HEIGHT / 2;
		} else if(rotDest == UMLScene.ROT_270) {
			endCoord[0] += HEIGHT / 2;
		} else {
			endCoord[1] += HEIGHT / 2;
		}
	
		
		double distance = getDistance(startCoord, endCoord);
		double numOfSegments = Math.ceil(distance / 10);
		double xChangePerIt = (endCoord[0] - startCoord[0]) / numOfSegments;
		double yChangePerIt = (endCoord[1] - startCoord[1]) / numOfSegments;
		
		double xLoc = startCoord[0];
		double yLoc = startCoord[1];
		for(int i = 0; i < numOfSegments; ++i) {
			double newX = xLoc + xChangePerIt;
			double newY = yLoc + yChangePerIt;
			if(i % 2 == 0) {
				g.drawLine((int)xLoc, (int)yLoc, (int)newX, (int)newY);
			}
			xLoc = newX;
			yLoc = newY;
		}
	}
	
	/**
	 * Gets the appropriate rotation for a given connection based on where
	 * two class boxes are located.
	 * 
	 * @param source source class box
	 * @param dest destination class box
	 * @return a rotation constant, which holds the rotation in radians
	 */
	private double getRotation(ClassBoxData source, ClassBoxData dest) {
		int diffX = source.getX() - dest.getX();
		int diffY = source.getY() - dest.getY();
		
		
		if(source.getX() + source.getWidth() > dest.getX() && source.getX() < dest.getX() + dest.getWidth()) {
			if(diffY > 0) {
				return UMLScene.ROT_180;
			} else {
				return UMLScene.ROT_0;
			}
		} else {
			if(diffX > 0) {
				return UMLScene.ROT_90;
			} else {
				return UMLScene.ROT_270;
			}
		}
	}
	
	/**
	 * Applies the distance formula to a set of coordinates
	 * 
	 * @param startCoord the starting coordinates, (x1, y1)
	 * @param endCoord the ending coordinates, (x2, y2)
	 * @return the distance (pixels) between these locations
	 */
	private double getDistance(int[] startCoord, int[] endCoord) {
		double x1 = startCoord[0];
		double x2 = endCoord[0];
		double y1 = startCoord[1];
		double y2 = endCoord[1];
		
		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2- y1), 2));
	}
	
	/**
	 * Triggers remove to the value true only if the right click is occurring within 10 pixels
	 * of a line  
	 * @param startCoord the starting coordinates of the drawn polygon
	 * @param endCoord the ending coordinates of the drawn polygon
	 * @param rotSource the rotation of the source polygon
	 * @param rotDest the rotation of the destination polygon
	 * @return true if mouse is within 10 pixels of a line
	 */
	private boolean triggerPopup (int[] startCoord, int[] endCoord, double rotSource, double rotDest) {
		if(rotSource == UMLScene.ROT_90) {
			startCoord[0] -= HEIGHT / 2;
		} else if(rotSource == UMLScene.ROT_180) {
			startCoord[1] -= HEIGHT / 2;
		} else if(rotSource == UMLScene.ROT_270) {
			startCoord[0] += HEIGHT / 2;
		} else {
			startCoord[1] += HEIGHT / 2;
		}
		
		if(rotDest == UMLScene.ROT_90) {
			endCoord[0] -= HEIGHT / 2;
		} else if(rotDest == UMLScene.ROT_180) {
			endCoord[1] -= HEIGHT / 2;
		} else if(rotDest == UMLScene.ROT_270) {
			endCoord[0] += HEIGHT / 2;
		} else {
			endCoord[1] += HEIGHT / 2;
		}
	
		
		double distance = getDistance(startCoord, endCoord);
		double numOfSegments = Math.ceil(distance / 10);
		double xChangePerIt = (endCoord[0] - startCoord[0]) / numOfSegments;
		double yChangePerIt = (endCoord[1] - startCoord[1]) / numOfSegments;
		
		double xLoc = startCoord[0];
		double yLoc = startCoord[1];
		for(int i = 0; i < numOfSegments; ++i) {
			double newX = xLoc + xChangePerIt;
			double newY = yLoc + yChangePerIt;
			if(i % 2 == 0) { 
				int[] location = {(int) newX, (int) newY};
				int[] mouseLocation = {this.getMousePosition().x, this.getMousePosition().y};
				int mouseDistance = (int) getDistance(location,mouseLocation);
				if(mouseDistance <= 10) {
					return remove = true;
				}
				
			}
			xLoc = newX;
			yLoc = newY;
		}
		return remove = false;
	}
	/**
	 * 
	 * @return
	 */
	public boolean removePopup(){
		return remove;
	}
	/**
	 * is true only when right click has been initialized
	 * @param removing is true only when right click has been initialized
	 */
	public void removeTrue(boolean removing) {
		removeTrue = removing;
	}

}
